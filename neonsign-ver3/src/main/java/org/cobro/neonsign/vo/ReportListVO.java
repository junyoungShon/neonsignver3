package org.cobro.neonsign.vo;

import java.util.ArrayList;

public class ReportListVO {
	private ArrayList<ReportVO> list;
	private PagingBean pagingBean;
	public ReportListVO(ArrayList<ReportVO> list, PagingBean pagingBean) {
		super();
		this.list = list;
		this.pagingBean = pagingBean;
	}
	public ReportListVO() {
	}
	public java.util.ArrayList<ReportVO> getList() {
		return list;
	}
	public void setList(java.util.ArrayList<ReportVO> list) {
		this.list = list;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
	@Override
	public String toString() {
		return "ListVO [list=" + list + ", pagingBean=" + pagingBean + "]";
	}
	
}
