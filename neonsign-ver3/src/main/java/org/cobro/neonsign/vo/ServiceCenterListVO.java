
package org.cobro.neonsign.vo;

import java.util.List;

public class ServiceCenterListVO {
	private List<ServiceCenterVO> ServiceCenterVO;
	private PagingBean pagingBean;
	public ServiceCenterListVO(List<ServiceCenterVO> ServiceCenterVO,
			PagingBean pagingBean) {
		super();
		this.ServiceCenterVO = ServiceCenterVO;
		this.pagingBean = pagingBean;
	}
	public List<ServiceCenterVO> getServiceCenterVO() {
		return ServiceCenterVO;
	}
	public void setServiceCenterVO(List<ServiceCenterVO> ServiceCenterVO) {
		this.ServiceCenterVO = ServiceCenterVO;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
	@Override
	public String toString() {
		return "ServiceCenterVO [ServiceCenterVO=" + ServiceCenterVO
				+ ", pagingBean=" + pagingBean + "]";
	}
	
}
