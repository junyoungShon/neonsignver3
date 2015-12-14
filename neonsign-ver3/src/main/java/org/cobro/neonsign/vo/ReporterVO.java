package org.cobro.neonsign.vo;

public class ReporterVO {
	private int notifyNo;
	private String memberEmail;
	public ReporterVO(int notifyNo, String memberEmail) {
		super();
		this.notifyNo = notifyNo;
		this.memberEmail = memberEmail;
	}
	public ReporterVO() {
		super();
	}
	public int getNotifyNo() {
		return notifyNo;
	}
	public void setNotifyNo(int notifyNo) {
		this.notifyNo = notifyNo;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	@Override
	public String toString() {
		return "NotifierVO [notifyNo=" + notifyNo + ", memberEmail="
				+ memberEmail + "]";
	}
}
