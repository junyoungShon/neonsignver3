package org.cobro.neonsign.vo;

public class RankingVO {
	private String memberGrade;
	private int minPoint;
	private int maxPoint;
	public RankingVO() {
		super();
	}
	public RankingVO(String memberGrade, int minPoint, int maxPoint) {
		super();
		this.memberGrade = memberGrade;
		this.minPoint = minPoint;
		this.maxPoint = maxPoint;
	}
	public String getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}
	public int getMinPoint() {
		return minPoint;
	}
	public void setMinPoint(int minPoint) {
		this.minPoint = minPoint;
	}
	public int getMaxPoint() {
		return maxPoint;
	}
	public void setMaxPoint(int maxPoint) {
		this.maxPoint = maxPoint;
	}
	@Override
	public String toString() {
		return "RankingVO [memberGrade=" + memberGrade + ", minPoint="
				+ minPoint + ", maxPoint=" + maxPoint + "]";
	}
	
}
