package org.cobro.neonsign.vo;

import java.util.ArrayList;

public class MemberListVO {
	private ArrayList<MemberVO> list;
	private PagingBean pagingBean;
	public MemberListVO(ArrayList<MemberVO> list, PagingBean pagingBean) {
		super();
		this.list = list;
		this.pagingBean = pagingBean;
	}
	public MemberListVO() {
	}
	public ArrayList<MemberVO> getList() {
		return list;
	}
	public void setList(ArrayList<MemberVO> list) {
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
