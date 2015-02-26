package sg.edu.nyp.evoting.beans;

// Generated Feb 7, 2015 7:06:49 PM by Hibernate Tools 3.4.0.CR1

/**
 * ConstituencyAreaId generated by hbm2java
 */
public class ConstituencyAreaId implements java.io.Serializable {

	private Integer zipcode;
	private String pollingStationId;
	private String constituencyId;

	public ConstituencyAreaId() {
	}

	public ConstituencyAreaId(Integer zipcode, String pollingStationId,
			String constituencyId) {
		this.zipcode = zipcode;
		this.pollingStationId = pollingStationId;
		this.constituencyId = constituencyId;
	}

	public Integer getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public String getPollingStationId() {
		return this.pollingStationId;
	}

	public void setPollingStationId(String pollingStationId) {
		this.pollingStationId = pollingStationId;
	}

	public String getConstituencyId() {
		return this.constituencyId;
	}

	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ConstituencyAreaId))
			return false;
		ConstituencyAreaId castOther = (ConstituencyAreaId) other;

		return ((this.getZipcode() == castOther.getZipcode()) || (this
				.getZipcode() != null && castOther.getZipcode() != null && this
				.getZipcode().equals(castOther.getZipcode())))
				&& ((this.getPollingStationId() == castOther
						.getPollingStationId()) || (this.getPollingStationId() != null
						&& castOther.getPollingStationId() != null && this
						.getPollingStationId().equals(
								castOther.getPollingStationId())))
				&& ((this.getConstituencyId() == castOther.getConstituencyId()) || (this
						.getConstituencyId() != null
						&& castOther.getConstituencyId() != null && this
						.getConstituencyId().equals(
								castOther.getConstituencyId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getZipcode() == null ? 0 : this.getZipcode().hashCode());
		result = 37
				* result
				+ (getPollingStationId() == null ? 0 : this
						.getPollingStationId().hashCode());
		result = 37
				* result
				+ (getConstituencyId() == null ? 0 : this.getConstituencyId()
						.hashCode());
		return result;
	}

}