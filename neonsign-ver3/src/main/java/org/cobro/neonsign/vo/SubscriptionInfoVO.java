package org.cobro.neonsign.vo;

public class SubscriptionInfoVO {
	private String publisher;
	private String subscriber;
	private String subscriptionDate;
	public SubscriptionInfoVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SubscriptionInfoVO(String publisher, String subscriber,
			String subscriptionDate) {
		super();
		this.publisher = publisher;
		this.subscriber = subscriber;
		this.subscriptionDate = subscriptionDate;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}
	public String getSubscriptionDate() {
		return subscriptionDate;
	}
	public void setSubscriptionDate(String subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}
	@Override
	public String toString() {
		return "SubscriptionInfoVO [publisher=" + publisher + ", subscriber="
				+ subscriber + ", subscriptionDate=" + subscriptionDate + "]";
	}
}
