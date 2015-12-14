package org.cobro.neonsign.vo;

public class PickedVO {
	private String memberEmail;
	private int mainArticleNo;
	private MainArticleVO mainArticleVO;
	public PickedVO() {
		super();
	}
	public PickedVO(String memberEmail, int mainArticleNo,
			MainArticleVO mainArticleVO) {
		super();
		this.memberEmail = memberEmail;
		this.mainArticleNo = mainArticleNo;
		this.mainArticleVO = mainArticleVO;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public int getMainArticleNo() {
		return mainArticleNo;
	}
	public void setMainArticleNo(int mainArticleNo) {
		this.mainArticleNo = mainArticleNo;
	}
	public MainArticleVO getMainArticleVO() {
		return mainArticleVO;
	}
	public void setMainArticleVO(MainArticleVO mainArticleVO) {
		this.mainArticleVO = mainArticleVO;
	}
	@Override
	public String toString() {
		return "PickedVO [memberEmail=" + memberEmail + ", mainArticleNo="
				+ mainArticleNo + ", mainArticleVO=" + mainArticleVO + "]";
	}
	
}
