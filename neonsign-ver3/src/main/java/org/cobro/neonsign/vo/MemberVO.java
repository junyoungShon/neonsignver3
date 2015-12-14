package org.cobro.neonsign.vo;

import java.util.List;

public class MemberVO {
	private String memberEmail;
	private String memberNickName;
	private String memberPassword;
	private String memberJoinDate;
	private int memberPoint;
	private int memberReportAmount;
	private String memberCategory;
	private RankingVO rankingVO;
	private List<PickedVO> pickedVOList; // 2015.11.28 추가함
	private List<ItjaMemberVO> itjaMemberList;
	private List<SubscriptionInfoVO> subscriptionInfoList;  // 2015.12.11 추가
	
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberVO(String memberEmail, String memberNickName,
			String memberPassword, String memberJoinDate, int memberPoint,
			int memberReportAmount, String memberCategory, RankingVO rankingVO,
			List<PickedVO> pickedVOList, List<ItjaMemberVO> itjaMemberList,
			List<SubscriptionInfoVO> subscriptionInfoList) {
		super();
		this.memberEmail = memberEmail;
		this.memberNickName = memberNickName;
		this.memberPassword = memberPassword;
		this.memberJoinDate = memberJoinDate;
		this.memberPoint = memberPoint;
		this.memberReportAmount = memberReportAmount;
		this.memberCategory = memberCategory;
		this.rankingVO = rankingVO;
		this.pickedVOList = pickedVOList;
		this.itjaMemberList = itjaMemberList;
		this.subscriptionInfoList = subscriptionInfoList;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberNickName() {
		return memberNickName;
	}

	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberJoinDate() {
		return memberJoinDate;
	}

	public void setMemberJoinDate(String memberJoinDate) {
		this.memberJoinDate = memberJoinDate;
	}

	public int getMemberPoint() {
		return memberPoint;
	}

	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}

	public int getMemberReportAmount() {
		return memberReportAmount;
	}

	public void setMemberReportAmount(int memberReportAmount) {
		this.memberReportAmount = memberReportAmount;
	}

	public String getMemberCategory() {
		return memberCategory;
	}

	public void setMemberCategory(String memberCategory) {
		this.memberCategory = memberCategory;
	}

	public RankingVO getRankingVO() {
		return rankingVO;
	}

	public void setRankingVO(RankingVO rankingVO) {
		this.rankingVO = rankingVO;
	}

	public List<PickedVO> getPickedVOList() {
		return pickedVOList;
	}

	public void setPickedVOList(List<PickedVO> pickedVOList) {
		this.pickedVOList = pickedVOList;
	}

	public List<ItjaMemberVO> getItjaMemberList() {
		return itjaMemberList;
	}

	public void setItjaMemberList(List<ItjaMemberVO> itjaMemberList) {
		this.itjaMemberList = itjaMemberList;
	}

	public List<SubscriptionInfoVO> getSubscriptionInfoList() {
		return subscriptionInfoList;
	}

	public void setSubscriptionInfoList(
			List<SubscriptionInfoVO> subscriptionInfoList) {
		this.subscriptionInfoList = subscriptionInfoList;
	}

	@Override
	public String toString() {
		return "MemberVO [memberEmail=" + memberEmail + ", memberNickName="
				+ memberNickName + ", memberPassword=" + memberPassword
				+ ", memberJoinDate=" + memberJoinDate + ", memberPoint="
				+ memberPoint + ", memberReportAmount=" + memberReportAmount
				+ ", memberCategory=" + memberCategory + ", rankingVO="
				+ rankingVO + ", pickedVOList=" + pickedVOList
				+ ", itjaMemberList=" + itjaMemberList
				+ ", subscriptionInfoList=" + subscriptionInfoList + "]";
	}
	
}
