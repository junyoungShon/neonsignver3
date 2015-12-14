package org.cobro.neonsign.vo;

public class ItjaMemberVO {
	private int mainArticleNo;
	private int subArticleNo;
	private String memberEmail;
	public ItjaMemberVO(int mainArticleNo, int subArticleNo, String memberEmail) {
		super();
		this.mainArticleNo = mainArticleNo;
		this.subArticleNo = subArticleNo;
		this.memberEmail = memberEmail;
	}
	public ItjaMemberVO() {
		super();
	}

	public int getMainArticleNo() {
		return mainArticleNo;
	}
	public void setMainArticleNo(int mainArticleNo) {
		this.mainArticleNo = mainArticleNo;
	}
	public int getSubArticleNo() {
		return subArticleNo;
	}
	public void setSubArticleNo(int subArticleNo) {
		this.subArticleNo = subArticleNo;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	@Override
	public String toString() {
		return "ItjaMemberVO [mainArticleNo=" + mainArticleNo
				+ ", subArticleNo=" + subArticleNo + ", memberEmail="
				+ memberEmail + "]";
	}
	
}
