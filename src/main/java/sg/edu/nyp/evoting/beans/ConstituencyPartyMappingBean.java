package sg.edu.nyp.evoting.beans;

// Generated Feb 7, 2015 7:06:49 PM by Hibernate Tools 3.4.0.CR1

/**
 * ConstituencyPartyMapping generated by hbm2java
 */
public class ConstituencyPartyMappingBean implements java.io.Serializable {

	private String constituencyId;
	private String constituencyName;
	private PartyBean[] partyBean;
	
	public ConstituencyPartyMappingBean() {
	}

	public ConstituencyPartyMappingBean(String constituencyId, String constituencyName, PartyBean[] partyBean) {
			
		this.constituencyId = constituencyId;
		this.constituencyName = constituencyName;
		this.partyBean = partyBean;
		
	}

	public String getConstituencyId() {
		return this.constituencyId;
	}

	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getConstituencyName() {
		return this.constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
	public PartyBean[] getPartyBean() {
		return this.partyBean;
	}

	public void setPartyBean(PartyBean[] partyBean) {
		this.partyBean = partyBean;
	}

}