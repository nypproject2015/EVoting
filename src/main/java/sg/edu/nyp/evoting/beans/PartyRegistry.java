package sg.edu.nyp.evoting.beans;

// Generated Feb 7, 2015 7:06:49 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PartyRegistry generated by hbm2java
 */
public class PartyRegistry implements java.io.Serializable {

	private String partyId;
	private String partyName;
	private byte[] partySymbol;
	private Date dateOfInception;
	private String status;
	private Set<CandidateMaster> candidateMasters = new HashSet<CandidateMaster>(
			0);
	private Set<ConstituencyPartyMapping> constituencyPartyMappings = new HashSet<ConstituencyPartyMapping>(
			0);

	public PartyRegistry() {
	}

	public PartyRegistry(String partyId) {
		this.partyId = partyId;
	}

	public PartyRegistry(String partyId, String partyName, byte[] partySymbol,
			Date dateOfInception, String status,
			Set<CandidateMaster> candidateMasters,
			Set<ConstituencyPartyMapping> constituencyPartyMappings) {
		this.partyId = partyId;
		this.partyName = partyName;
		this.partySymbol = partySymbol;
		this.dateOfInception = dateOfInception;
		this.status = status;
		this.candidateMasters = candidateMasters;
		this.constituencyPartyMappings = constituencyPartyMappings;
	}

	public String getPartyId() {
		return this.partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return this.partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public byte[] getPartySymbol() {
		return this.partySymbol;
	}

	public void setPartySymbol(byte[] partySymbol) {
		this.partySymbol = partySymbol;
	}

	public Date getDateOfInception() {
		return this.dateOfInception;
	}

	public void setDateOfInception(Date dateOfInception) {
		this.dateOfInception = dateOfInception;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<CandidateMaster> getCandidateMasters() {
		return this.candidateMasters;
	}

	public void setCandidateMasters(Set<CandidateMaster> candidateMasters) {
		this.candidateMasters = candidateMasters;
	}

	public Set<ConstituencyPartyMapping> getConstituencyPartyMappings() {
		return this.constituencyPartyMappings;
	}

	public void setConstituencyPartyMappings(
			Set<ConstituencyPartyMapping> constituencyPartyMappings) {
		this.constituencyPartyMappings = constituencyPartyMappings;
	}

}