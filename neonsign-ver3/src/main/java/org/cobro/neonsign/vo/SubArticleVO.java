package org.cobro.neonsign.vo;

public class SubArticleVO {
	private int subArticleNo;
	private int mainArticleNo;
	private String memberEmail;
	private int subAtricleGrade;
	private String subArticleContent;
	private int isEnd;
	private int subArticleLike;
	private int isConnect;
	private int block;
	private String subArticleDate;
	private MemberVO memberVO;
	public SubArticleVO() {
		super();
	}
	public SubArticleVO(int subArticleNo, int mainArticleNo,
			String memberEmail, int subAtricleGrade, String subArticleContent,
			int isEnd, int subArticleLike, int isConnect, int block,
			String subArticleDate, MemberVO memberVO) {
		super();
		this.subArticleNo = subArticleNo;
		this.mainArticleNo = mainArticleNo;
		this.memberEmail = memberEmail;
		this.subAtricleGrade = subAtricleGrade;
		this.subArticleContent = subArticleContent;
		this.isEnd = isEnd;
		this.subArticleLike = subArticleLike;
		this.isConnect = isConnect;
		this.block = block;
		this.subArticleDate = subArticleDate;
		this.memberVO = memberVO;
	}
	public int getSubArticleNo() {
		return subArticleNo;
	}
	public void setSubArticleNo(int subArticleNo) {
		this.subArticleNo = subArticleNo;
	}
	public int getMainArticleNo() {
		return mainArticleNo;
	}
	public void setMainArticleNo(int mainArticleNo) {
		this.mainArticleNo = mainArticleNo;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public int getSubAtricleGrade() {
		return subAtricleGrade;
	}
	public void setSubAtricleGrade(int subAtricleGrade) {
		this.subAtricleGrade = subAtricleGrade;
	}
	public String getSubArticleContent() {
		return subArticleContent;
	}
	public void setSubArticleContent(String subArticleContent) {
		this.subArticleContent = subArticleContent;
	}
	public int getIsEnd() {
		return isEnd;
	}
	public void setIsEnd(int isEnd) {
		this.isEnd = isEnd;
	}
	public int getSubArticleLike() {
		return subArticleLike;
	}
	public void setSubArticleLike(int subArticleLike) {
		this.subArticleLike = subArticleLike;
	}
	public int getIsConnect() {
		return isConnect;
	}
	public void setIsConnect(int isConnect) {
		this.isConnect = isConnect;
	}
	public int getBlock() {
		return block;
	}
	public void setBlock(int block) {
		this.block = block;
	}
	public String getSubArticleDate() {
		return subArticleDate;
	}
	public void setSubArticleDate(String subArticleDate) {
		this.subArticleDate = subArticleDate;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	@Override
	public String toString() {
		return "SubArticleVO [subArticleNo=" + subArticleNo
				+ ", mainArticleNo=" + mainArticleNo + ", memberEmail="
				+ memberEmail + ", subAtricleGrade=" + subAtricleGrade
				+ ", subArticleContent=" + subArticleContent + ", isEnd="
				+ isEnd + ", subArticleLike=" + subArticleLike + ", isConnect="
				+ isConnect + ", block=" + block + ", subArticleDate="
				+ subArticleDate + ", memberVO=" + memberVO + "]";
	}

	
	
}
