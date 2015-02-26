package sg.edu.nyp.evoting.beans;

// Generated Feb 7, 2015 7:06:49 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ElectionMaster generated by hbm2java
 */
public class ElectionMaster implements java.io.Serializable {

	private int electionId;
	private String electionType;
	private Date dateOfElection;
	private String electionStatus;
	private Set<CandidateMaster> candidateMasters = new HashSet<CandidateMaster>(
			0);
	private Set<ConstituencyMaster> constituencyMasters = new HashSet<ConstituencyMaster>(
			0);

	public ElectionMaster() {
	}

	public ElectionMaster(int electionId) {
		this.electionId = electionId;
	}

	public ElectionMaster(int electionId, String electionType,
			Date dateOfElection, String electionStatus,
			Set<CandidateMaster> candidateMasters,
			Set<ConstituencyMaster> constituencyMasters) {
		this.electionId = electionId;
		this.electionType = electionType;
		this.dateOfElection = dateOfElection;
		this.electionStatus = electionStatus;
		this.candidateMasters = candidateMasters;
		this.constituencyMasters = constituencyMasters;
	}

	public int getElectionId() {
		return this.electionId;
	}

	public void setElectionId(int electionId) {
		this.electionId = electionId;
	}

	public String getElectionType() {
		return this.electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public Date getDateOfElection() {
		return this.dateOfElection;
	}

	public void setDateOfElection(Date dateOfElection) {
		this.dateOfElection = dateOfElection;
	}

	public String getElectionStatus() {
		return this.electionStatus;
	}

	public void setElectionStatus(String electionStatus) {
		this.electionStatus = electionStatus;
	}

	public Set<CandidateMaster> getCandidateMasters() {
		return this.candidateMasters;
	}

	public void setCandidateMasters(Set<CandidateMaster> candidateMasters) {
		this.candidateMasters = candidateMasters;
	}

	public Set<ConstituencyMaster> getConstituencyMasters() {
		return this.constituencyMasters;
	}

	public void setConstituencyMasters(
			Set<ConstituencyMaster> constituencyMasters) {
		this.constituencyMasters = constituencyMasters;
	}

}
