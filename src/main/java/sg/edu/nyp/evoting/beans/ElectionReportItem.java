package sg.edu.nyp.evoting.beans;

public class ElectionReportItem {
	
	public ElectionReportItem(ElectionResultsBean electionResultsBean, boolean winFlg) {
		this.constituencyName = electionResultsBean.getConstituencyName();
		this.partyName = electionResultsBean.getPartyName();
		this.winFlg = winFlg;
		this.noofVotes = electionResultsBean.getCounter();
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public boolean isWinFlg() {
		return winFlg;
	}
	public void setWinFlg(boolean winFlg) {
		this.winFlg = winFlg;
	}
	public int getNoofVotes() {
		return noofVotes;
	}
	public void setNoofVotes(int noofVotes) {
		this.noofVotes = noofVotes;
	}
	private String constituencyName;
	private String partyName;
	private boolean winFlg;
	private int noofVotes;
	
	public String toString() {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("Constituency Name:").append(constituencyName).append(",PartyName:").append(partyName).append(",Win:").append(winFlg);
		return strBuffer.toString();
	}
	
}