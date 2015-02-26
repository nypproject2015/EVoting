package sg.edu.nyp.evoting.service;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.crypto.SecretKeyFactory;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.security.AlgorithmParameters;
import javax.crypto.spec.IvParameterSpec;
import java.security.MessageDigest;
import sg.edu.nyp.evoting.beans.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sg.edu.nyp.evoting.persistence.HibernateUtil;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.codec.binary.Base64;

public class EVotingService {

	private static SecretKey secret = null;
	private static SecretKey hashKeySecret = null;
	private static byte[] IV = null;
	private static byte[] hashIV = null;
	private static MessageDigest md = null;
	private static String encryptKeyPassword1 = null;
	private static String encryptKeyPassword2 = null;
	private static String encryptKeyPassword3 = null;

	public EVotingService() {
	
		try {
		
			secret = getSecretKeyInstance();
			hashKeySecret = getHashSecretKeyInstance();		
			
			byte[] _iv ={0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,0x09,0x0A,0x0B,0x0C,0x0D,0x0E,0x0F,0x1F};
			IV = _iv;
			
			byte[] _hashIV ={0x00,0x02,0x01,0x05,0x04,0x07,0x06,0x09,0x08,0x0B,0x0A,0x0D,0x0C,0x0F,0x0E,0x1E};
			hashIV = _hashIV;
			
			md = MessageDigest.getInstance("SHA-1");
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public EVotingService(String password1, String password2, String password3) {		
				//Initialize password to generate secret keys
				encryptKeyPassword1 = password1;
				encryptKeyPassword2 = password2;
				encryptKeyPassword3 = password3;	
	}
	
	// Fetch Candidates for the given Constituency Id
    public ConstituencyPartyMappingBean fetchCandidates(String constituencyId) {	
	
		try {
							
			ConstituencyPartyMappingHome constituencyPartyMappingDAO = new ConstituencyPartyMappingHome();
			ConstituencyPartyMappingId constituencyPartyMappingId = new ConstituencyPartyMappingId();
			constituencyPartyMappingId.setConstituencyId(constituencyId);
			ConstituencyPartyMapping constituencyPartyMappingCriteria = new ConstituencyPartyMapping(constituencyPartyMappingId);			
						
			//Query the Constituency Party Mapping table
			constituencyPartyMappingDAO.openCurrentSession();
			List<ConstituencyPartyMapping> constituencyPartyMappingList = constituencyPartyMappingDAO.findByExample(constituencyPartyMappingCriteria);

			int len = 0;
			for (ConstituencyPartyMapping constituencyPartyMapping : constituencyPartyMappingList) {
				if(constituencyPartyMapping.getId().getConstituencyId().equals(constituencyId)) {
					++len;
				}
			}
			
			ConstituencyPartyMapping constituencyPartyMapping1 = null;
			PartyBean[] partyBean = new PartyBean[len];
			int i = 0;
			
			//Load the Party Information from Party Registry
			for (ConstituencyPartyMapping constituencyPartyMapping : constituencyPartyMappingList) {
				if(constituencyPartyMapping.getId().getConstituencyId().equals(constituencyId)) {
					partyBean[i++] = new PartyBean(constituencyPartyMapping.getId().getConstituencyPartyMappingid(), constituencyPartyMapping.getPartyRegistry().getPartyId(), constituencyPartyMapping.getPartyRegistry().getPartyName(), constituencyPartyMapping.getPartyRegistry().getPartySymbol(), constituencyPartyMapping.getPartyRegistry().getDateOfInception(), constituencyPartyMapping.getPartyRegistry().getStatus(), false);

					constituencyPartyMapping1 = constituencyPartyMapping;
				}
			}
			
			//Construct the Constituency Party Mapping Bean to return to clients
			ConstituencyPartyMappingBean constituencyPartyMappingBean = new ConstituencyPartyMappingBean(constituencyPartyMapping1.getId().getConstituencyId(), constituencyPartyMapping1.getConstituencyMaster().getConstituencyName(), partyBean);						
			
			constituencyPartyMappingDAO.closeCurrentSession();

			return constituencyPartyMappingBean;
			
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}		
    }
	
	//Record Vote
    //public void recordVote(String constituencyId) {
	public void recordVote(ConstituencyPartyMappingBean constituencyPartyMappingBean) {
	
		try {	
		
			//Check how many parties have been selected
			VoterLogbook voterLogbook = null;
			VoterLogbookId voterLogbookId = null;
			VoterLogbookHome voterLogbookHome = null;	
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dt = new Date();
			
			int selCounter = 0;
			if(constituencyPartyMappingBean.getPartyBean()!=null && constituencyPartyMappingBean.getPartyBean().length > 0) {				
				for(int j=0; j< constituencyPartyMappingBean.getPartyBean().length; j++) {
					if(constituencyPartyMappingBean.getPartyBean()[j].getSelected() == true) {
						++selCounter;
					}
					
				}			
			}
			
			//Initialize Cipher to encrypt the data
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			Cipher hashCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");	
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKeyInstance(), new IvParameterSpec(IV));
			hashCipher.init(Cipher.ENCRYPT_MODE, getHashSecretKeyInstance(), new IvParameterSpec(hashIV));
			
			int counter = 1;
			char isValid = 'Y';
			if(constituencyPartyMappingBean.getPartyBean()!=null && constituencyPartyMappingBean.getPartyBean().length > 0) {				
				voterLogbookHome = new VoterLogbookHome();			
				for(int j=0; j< constituencyPartyMappingBean.getPartyBean().length; j++) {
				
					//If multiple parties have been selected, the vote is set as invalid
					if(constituencyPartyMappingBean.getPartyBean()[j].getSelected() == true) {
						if(selCounter > 1) {							
							counter = 0;
							isValid = 'N';
						} else {					
							counter = 1;
							isValid = 'Y';
						}
							
						//Compute Hash of ConstituencyPartyMappingid, Counter IsValid, Voted Date
						StringBuffer hashStr = new StringBuffer(constituencyPartyMappingBean.getPartyBean()[j].getConstituencyPartyMappingid().toString());
						hashStr.append(new Integer(counter).toString());
						hashStr.append(new Character(isValid).toString());
						hashStr.append(sdf.format(dt));
						md.update(hashStr.toString().getBytes()); 
						byte[] digest = md.digest();
						
						//Encrypt ConstituencyPartyMappingid, Counter IsValid, Voted Date with one key and the hash with another key
						voterLogbookId = new VoterLogbookId(new String(Base64.encodeBase64(cipher.doFinal(constituencyPartyMappingBean.getPartyBean()[j].getConstituencyPartyMappingid().toString().getBytes()))), new String(Base64.encodeBase64(cipher.doFinal(new Integer(counter).toString().getBytes()))), new String(Base64.encodeBase64(cipher.doFinal(new Character(isValid).toString().getBytes()))), new String(Base64.encodeBase64(cipher.doFinal(sdf.format(dt).getBytes()))), new String(Base64.encodeBase64(hashCipher.doFinal(new String(digest).getBytes()))));					
						//Store the record in table
						voterLogbook = new VoterLogbook();
						voterLogbook.setId(voterLogbookId);
						voterLogbookHome.persist(voterLogbook);						
					}					
				}			
			}			
		} catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occured while connecting to database: "+e);
        }
    }
	
	//Count Vote
	protected List<ElectionReportItem> countVote() throws Exception {
	
			List<ElectionReportItem> electionReportList = null;
			try {
			VoterLogbookHome voterLogbookHome = new VoterLogbookHome();				
			voterLogbookHome.openCurrentSession();

			ConstituencyPartyMappingHome constituencyPartyMappingDAO = new ConstituencyPartyMappingHome();
			constituencyPartyMappingDAO.openCurrentSession();
			
			//Initialize Cipher to decrypt the data
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			Cipher hashCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");	
			cipher.init(Cipher.DECRYPT_MODE, getSecretKeyInstance(), new IvParameterSpec(IV));
			hashCipher.init(Cipher.DECRYPT_MODE, getHashSecretKeyInstance(), new IvParameterSpec(hashIV));
		
			VoterLogbookId voterLogbookId = new VoterLogbookId();
			VoterLogbook voterLogbookCriteria = new VoterLogbook();
			
			//Query the Voter LogBook
			List<VoterLogbook> voterLogbookList = voterLogbookHome.findByExample(voterLogbookCriteria);
			
			String constituencyPartyMappingid = null;
			String counter = null;
			String isvalid = null;
			String dateOfVoting = null;
			String checksum = null;
			
			HashMap<String, HashMap<String, ElectionResultsBean>> constituencyPartyMap = new HashMap();
			HashMap<String, Integer> partyMap = new HashMap();
			HashMap<String, ElectionResultsBean> constituencyPartyMapDetails = null;
			Integer partyCounter = new Integer(0);
			Integer constituencyPartyCounter = new Integer(0);
			String constituencyId = null;
			String partyId = null;
			Integer value = null;
			ElectionResultsBean electionResultsBean = null;
			
			//Decrypt the ConstituencyPartyMappingid, Counter IsValid, Voted Date and checksum
			for (VoterLogbook voterLogbook : voterLogbookList) {
				constituencyPartyMappingid = new String(cipher.doFinal(Base64.decodeBase64(voterLogbook.getId().getConstituencyPartyMappingid().getBytes())));
				counter = new String(cipher.doFinal(Base64.decodeBase64(voterLogbook.getId().getCounter().getBytes())));
				isvalid = new String(cipher.doFinal(Base64.decodeBase64(voterLogbook.getId().getIsvalid().getBytes())));
				dateOfVoting = new String(cipher.doFinal(Base64.decodeBase64(voterLogbook.getId().getDateOfVoting().getBytes())));
				checksum = new String(hashCipher.doFinal(Base64.decodeBase64(voterLogbook.getId().getChecksum().getBytes())));
				
				//Computer Hash of ConstituencyPartyMappingid, Counter IsValid, Voted Date 
				StringBuffer hashStr = new StringBuffer(constituencyPartyMappingid);
				hashStr.append(counter);
				hashStr.append(isvalid);
				hashStr.append(dateOfVoting);
				md.update(hashStr.toString().getBytes()); 
				byte[] digest = md.digest();
				
				//Compare the checksum with the computed hash for data integrity
				if(new String(digest).equals(checksum)) {
					System.out.println("Data is safe");
				}
								
				ConstituencyPartyMappingId constituencyPartyMappingIdCriteria = new ConstituencyPartyMappingId();
				constituencyPartyMappingIdCriteria.setConstituencyPartyMappingid(Integer.parseInt(constituencyPartyMappingid));
				ConstituencyPartyMapping constituencyPartyMappingCriteria = new ConstituencyPartyMapping(constituencyPartyMappingIdCriteria);			
				
				//Query the ConstituencyPartyMapping table
				List<ConstituencyPartyMapping> constituencyPartyMappingList = constituencyPartyMappingDAO.findByExample(constituencyPartyMappingCriteria);

				//Count the votes and store them in the HashMap<ConstituencyId, HashMap<PartyId, ElectionResultsBean>>
				for (ConstituencyPartyMapping constituencyPartyMapping : constituencyPartyMappingList) {
					if(constituencyPartyMapping.getId().getConstituencyPartyMappingid().toString().equals(constituencyPartyMappingid) && counter.equals("1") && isvalid.equals("Y")) {						
						constituencyId = constituencyPartyMapping.getId().getConstituencyId();
						partyId = constituencyPartyMapping.getId().getPartyId();
						if(constituencyPartyMap.get(constituencyId)==null) {							
							constituencyPartyMapDetails = new HashMap();
							electionResultsBean = new ElectionResultsBean(constituencyPartyMapping.getConstituencyMaster().getConstituencyName(), constituencyPartyMapping.getPartyRegistry().getPartyName(), Integer.valueOf(constituencyPartyCounter.intValue()+1));
							constituencyPartyMapDetails.put(partyId, electionResultsBean);
							constituencyPartyMap.put(constituencyId, constituencyPartyMapDetails);
						} else {
							constituencyPartyMapDetails = constituencyPartyMap.get(constituencyId);
							if(constituencyPartyMapDetails.get(partyId)==null) {
								electionResultsBean = new ElectionResultsBean(constituencyPartyMapping.getConstituencyMaster().getConstituencyName(), constituencyPartyMapping.getPartyRegistry().getPartyName(), Integer.valueOf(constituencyPartyCounter.intValue()+1));
							} else {
								electionResultsBean = constituencyPartyMapDetails.get(partyId);
								electionResultsBean.setCounter(Integer.valueOf(electionResultsBean.getCounter().intValue()+1));
							}
							constituencyPartyMapDetails.put(partyId, electionResultsBean);
						}
						
						//Count the each party votes and store them in the HashMap<PartyId, NumberOfVotes>>
						if(partyMap.get(partyId)==null) {
							value = partyCounter;
						} else {
							value = partyMap.get(partyId);								
						}
						partyMap.put(partyId, Integer.valueOf(value.intValue()+1));
			
						break;
					}
				}
				
				//Print the result
				System.out.println("Party Votes:");
	
				electionReportList = new ArrayList<ElectionReportItem>();
				for (Map.Entry<String, Integer> entry : partyMap.entrySet())
				{
					System.out.println(entry.getKey() + "/" + entry.getValue());
				}
				System.out.println("Party Votes Constituency vise:");
				for (Map.Entry<String, HashMap<String, ElectionResultsBean>> entry : constituencyPartyMap.entrySet())
				{
					List<ElectionResultsBean> resultPerConstituency = new ArrayList<ElectionResultsBean>();
					for (Map.Entry<String, ElectionResultsBean> entry1 : entry.getValue().entrySet()) {
						resultPerConstituency.add(entry1.getValue());
						System.out.println(entry.getKey() + "/" + entry1.getValue().getConstituencyName() + "/" + entry1.getKey() + "/" + entry1.getValue().getPartyName() + "/" + entry1.getValue().getCounter());					
					}
					Collections.sort(resultPerConstituency,new Comparator<ElectionResultsBean>() {
					public int compare(ElectionResultsBean a, ElectionResultsBean b) {
						return b.getCounter().compareTo(a.getCounter());
					}
					});
					int i=0;
					for (ElectionResultsBean electionResult: resultPerConstituency) {
						boolean winFlg = false;
						if (i==0) {
							winFlg = true;
						}				
						ElectionReportItem electionReportItem = new ElectionReportItem(electionResult,winFlg);
						electionReportList.add(electionReportItem);				
						i++;
					}
				}
			}	
			constituencyPartyMappingDAO.closeCurrentSession();
			voterLogbookHome.closeCurrentSession();	
			return electionReportList;
		} catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while counting votes: "+e);
			throw e;
        }	
    }
	
	//Start Election Process
	protected void startElectionProcess() throws Exception {
	
			try {
				//Generate secret keys to encrypt and decrypt the data
				secret = getSecretKeyInstance();
				hashKeySecret = getHashSecretKeyInstance();		
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error occurred while counting votes: "+e);
				throw e;
			}
	}
	
	
	private SecretKey getSecretKeyInstance() {
		if(secret == null) {
			try { 
				//Symmetric Key for encrypting data
				if(encryptKeyPassword1!=null && encryptKeyPassword2!=null && encryptKeyPassword3!=null) {
					StringBuffer password = new StringBuffer(encryptKeyPassword1);
					password.append(encryptKeyPassword2);
					password.append(encryptKeyPassword3);		
					SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
					KeySpec spec = new PBEKeySpec(password.toString().toCharArray(), "nypproject4".getBytes(), 65536, 128);
					SecretKey tmp = factory.generateSecret(spec);
					secret = new SecretKeySpec(tmp.getEncoded(), "AES");		
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error occurred while counting votes: "+e);
			}
		}
		return secret;		
	}
	
	private SecretKey getHashSecretKeyInstance() {
		if(hashKeySecret == null) {		
			try {
				//Symmetric Key for encrypting Hash
				if(encryptKeyPassword1!=null && encryptKeyPassword2!=null && encryptKeyPassword3!=null) {
					StringBuffer hashKeyPassword = new StringBuffer(encryptKeyPassword1);
					hashKeyPassword.append(encryptKeyPassword2);
					hashKeyPassword.append(encryptKeyPassword3);			
					SecretKeyFactory hashKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
					KeySpec hashKeySpec = new PBEKeySpec(hashKeyPassword.toString().toCharArray(), "NYPpr0j3ct44".getBytes(), 65536, 128);
					SecretKey hashKeyFactoryTmp = hashKeyFactory.generateSecret(hashKeySpec);
					hashKeySecret = new SecretKeySpec(hashKeyFactoryTmp.getEncoded(), "AES");	
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error occurred while counting votes: "+e);
			}
		}
		return hashKeySecret;		
	}
	
}
