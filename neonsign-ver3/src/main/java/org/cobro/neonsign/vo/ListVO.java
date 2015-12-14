package org.cobro.neonsign.vo;

import java.util.ArrayList;

public class ListVO {
	private ArrayList<Object> list;
	private PagingBean pagingBean;
	public ListVO(ArrayList<Object> list, PagingBean pagingBean) {
		super();
		this.list = list;
		this.pagingBean = pagingBean;
	}
	public ListVO() {
	}
	public java.util.ArrayList<Object> getList() {
		return list;
	}
	public void setList(java.util.ArrayList<Object> list) {
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
