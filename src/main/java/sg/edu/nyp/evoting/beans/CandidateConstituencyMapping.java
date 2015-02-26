package sg.edu.nyp.evoting.beans;

// Generated Feb 7, 2015 7:06:49 PM by Hibernate Tools 3.4.0.CR1

/**
 * CandidateConstituencyMapping generated by hbm2java
 */
public class CandidateConstituencyMapping implements java.io.Serializable {

	private String candidateConstituencyId;
	private CandidateMaster candidateMaster;
	private ConstituencyMaster constituencyMaster;

	public CandidateConstituencyMapping() {
	}

	public CandidateConstituencyMapping(String candidateConstituencyId) {
		this.candidateConstituencyId = candidateConstituencyId;
	}

	public CandidateConstituencyMapping(String candidateConstituencyId,
			CandidateMaster candidateMaster,
			ConstituencyMaster constituencyMaster) {
		this.candidateConstituencyId = candidateConstituencyId;
		this.candidateMaster = candidateMaster;
		this.constituencyMaster = constituencyMaster;
	}

	public String getCandidateConstituencyId() {
		return this.candidateConstituencyId;
	}

	public void setCandidateConstituencyId(String candidateConstituencyId) {
		this.candidateConstituencyId = candidateConstituencyId;
	}

	public CandidateMaster getCandidateMaster() {
		return this.candidateMaster;
	}

	public void setCandidateMaster(CandidateMaster candidateMaster) {
		this.candidateMaster = candidateMaster;
	}

	public ConstituencyMaster getConstituencyMaster() {
		return this.constituencyMaster;
	}

	public void setConstituencyMaster(ConstituencyMaster constituencyMaster) {
		this.constituencyMaster = constituencyMaster;
	}

}