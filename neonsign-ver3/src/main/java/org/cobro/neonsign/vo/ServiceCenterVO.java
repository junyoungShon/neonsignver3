
package org.cobro.neonsign.vo;

public class ServiceCenterVO {
	private int ServiceCenterNo; //문의글번호
	private String ServiceCenterTitle; //문의글 제목
	private String ServiceCenterContext;//문의글내용
	private String ServiceCenterDate;//문의글 작성시간
	private String  ServiceCenterEmail; //문의글 작성자 이메일
	public ServiceCenterVO() {
		super();
	}
	public ServiceCenterVO(int serviceCenterNo, String serviceCenterTitle,
			String serviceCenterContext, String serviceCenterDate,
			String serviceCenterEmail) {
		super();
		ServiceCenterNo = serviceCenterNo;
		ServiceCenterTitle = serviceCenterTitle;
		ServiceCenterContext = serviceCenterContext;
		ServiceCenterDate = serviceCenterDate;
		ServiceCenterEmail = serviceCenterEmail;
	}
	public int getServiceCenterNo() {
		return ServiceCenterNo;
	}
	public void setServiceCenterNo(int serviceCenterNo) {
		ServiceCenterNo = serviceCenterNo;
	}
	public String getServiceCenterTitle() {
		return ServiceCenterTitle;
	}
	public void setServiceCenterTitle(String serviceCenterTitle) {
		ServiceCenterTitle = serviceCenterTitle;
	}
	public String getServiceCenterContext() {
		return ServiceCenterContext;
	}
	public void setServiceCenterContext(String serviceCenterContext) {
		ServiceCenterContext = serviceCenterContext;
	}
	public String getServiceCenterDate() {
		return ServiceCenterDate;
	}
	public void setServiceCenterDate(String serviceCenterDate) {
		ServiceCenterDate = serviceCenterDate;
	}
	public String getServiceCenterEmail() {
		return ServiceCenterEmail;
	}
	public void setServiceCenterEmail(String serviceCenterEmail) {
		ServiceCenterEmail = serviceCenterEmail;
	}
	@Override
	public String toString() {
		return "ServiceCenterVO [ServiceCenterNo=" + ServiceCenterNo
				+ ", ServiceCenterTitle=" + ServiceCenterTitle
				+ ", ServiceCenterContext=" + ServiceCenterContext
				+ ", ServiceCenterDate=" + ServiceCenterDate
				+ ", ServiceCenterEmail=" + ServiceCenterEmail + "]";
	}
   
}