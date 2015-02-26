package sg.edu.nyp.evoting.beans;

// Generated Feb 9, 2015 1:01:13 PM by Hibernate Tools 3.4.0.CR1

/**
 * VoterLogbookId generated by hbm2java
 */
public class VoterLogbookId implements java.io.Serializable {

	private String constituencyPartyMappingid;
	private String counter;
	private String isvalid;
	private String pollingStationId;
	private String dateOfVoting;
	private String checksum;

	public VoterLogbookId() {
	}

	public VoterLogbookId(String constituencyPartyMappingid, String counter,
			String isvalid, String dateOfVoting,
			String checksum) {
		this.constituencyPartyMappingid = constituencyPartyMappingid;
		this.counter = counter;
		this.isvalid = isvalid;
		this.dateOfVoting = dateOfVoting;
		this.checksum = checksum;
	}

	public String getConstituencyPartyMappingid() {
		return this.constituencyPartyMappingid;
	}

	public void setConstituencyPartyMappingid(String constituencyPartyMappingid) {
		this.constituencyPartyMappingid = constituencyPartyMappingid;
	}

	public String getCounter() {
		return this.counter;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}

	public String getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	public String getDateOfVoting() {
		return this.dateOfVoting;
	}

	public void setDateOfVoting(String dateOfVoting) {
		this.dateOfVoting = dateOfVoting;
	}

	public String getChecksum() {
		return this.checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VoterLogbookId))
			return false;
		VoterLogbookId castOther = (VoterLogbookId) other;

		return ((this.getConstituencyPartyMappingid() == castOther
				.getConstituencyPartyMappingid()) || (this
				.getConstituencyPartyMappingid() != null
				&& castOther.getConstituencyPartyMappingid() != null && this
				.getConstituencyPartyMappingid().equals(
						castOther.getConstituencyPartyMappingid())))
				&& ((this.getCounter() == castOther.getCounter()) || (this
						.getCounter() != null && castOther.getCounter() != null && this
						.getCounter().equals(castOther.getCounter())))
				&& ((this.getIsvalid() == castOther.getIsvalid()) || (this
						.getIsvalid() != null && castOther.getIsvalid() != null && this
						.getIsvalid().equals(castOther.getIsvalid())))
				&& ((this.getDateOfVoting() == castOther.getDateOfVoting()) || (this
						.getDateOfVoting() != null
						&& castOther.getDateOfVoting() != null && this
						.getDateOfVoting().equals(castOther.getDateOfVoting())))
				&& ((this.getChecksum() == castOther.getChecksum()) || (this
						.getChecksum() != null
						&& castOther.getChecksum() != null && this
						.getChecksum().equals(castOther.getChecksum())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getConstituencyPartyMappingid() == null ? 0 : this
						.getConstituencyPartyMappingid().hashCode());
		result = 37 * result
				+ (getCounter() == null ? 0 : this.getCounter().hashCode());
		result = 37 * result
				+ (getIsvalid() == null ? 0 : this.getIsvalid().hashCode());
		result = 37
				* result
				+ (getDateOfVoting() == null ? 0 : this.getDateOfVoting()
						.hashCode());
		result = 37 * result
				+ (getChecksum() == null ? 0 : this.getChecksum().hashCode());
		return result;
	}

}