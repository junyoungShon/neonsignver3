package org.cobro.neonsign.vo;

public class FindPasswordVO {
	private String memberEmail;
	private String randomSentence;
	public FindPasswordVO() {
		super();
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getRandomSentence() {
		return randomSentence;
	}
	public void setRandomSentence(String randomSentence) {
		this.randomSentence = randomSentence;
	}
	@Override
	public String toString() {
		return "FindPassword [memberEmail=" + memberEmail + ", randomSentence="
				+ randomSentence + "]";
	}
}
