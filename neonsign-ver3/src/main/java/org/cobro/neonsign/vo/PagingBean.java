package org.cobro.neonsign.vo;

public class PagingBean {
	private int contentNumberPerPage
	=13 ;//페이지당 보여줄 게시물 수
	private int nowPage;//현재 페이지
	private int pageNumberPerPageGroup
	=4;//페이지 그룹당 페이지 수
	private int totalContents;//총 게시물 수
	public PagingBean(int totalContenrs, int nowPage){
		this.totalContents=totalContenrs;//총 게시물 수
		this.nowPage=nowPage;//현재 페이지
	}
	public PagingBean(int totalContents) {
		super();
		this.totalContents = totalContents;
		this.nowPage=1;
	}
	
	public int getNowPage(){
		return nowPage;
	}
	public int getNextPage(){
		return getNowPageGroup()*4+1;
		
	}
	public int getPreviousPage(){
		return (getNowPageGroup()-1)*4;
		
	}
	public int getTotalPage(){
		int totalPage=0;
		if(totalContents%contentNumberPerPage==0){
			totalPage=totalContents/contentNumberPerPage;
		}else if(totalContents%contentNumberPerPage>0){
			totalPage=totalContents/contentNumberPerPage+1;
		}
		/*총 페이지 수를 return한다.
		1. 전체 데이터(게시물) % 한 페이지에 보여줄 데이터 개수 
		=> 0 이면 둘을 / 값이 총 페이지 수
		2. 전체 데이터(게시물) % 한 페이지에 보여줄 데이터 개수 
		=> 0보다 크면 둘을 / 값에 +1을 한 값이 총 페이지 수
		게시물수 1 2 3 4 5 6 7 8 9 10 11 12
		1페이지 1~5
		2페이지 6~10
		3페이지 11 
		ex) 게시물 32 개 , 페이지당 게시물수 5개 
		-> 7 페이지*/
		return totalPage;
	}
	public int getTotalPageGroup(){
		int totalPageGroup=0;
		if(getTotalPage()%pageNumberPerPageGroup==0){
			totalPageGroup=getTotalPage()/pageNumberPerPageGroup;
		}else if(getTotalPage()%pageNumberPerPageGroup>0){
			totalPageGroup=getTotalPage()/pageNumberPerPageGroup+1;
		}
	/*총 페이지 그룹의 수를 return한다.
	1. 총 페이지수 % Page Group 안의 Page 수. 
	=> 0 이면 둘을 / 값이 총 페이지 수
	2. 총 페이지수 % Page Group 내 Page 수. 
	=> 0보다 크면 둘을 / 값에 +1을 한 값이 총 페이지 수
	ex) 총 게시물 수 23 개 
	총 페이지 ? 총 페이지 그룹수 ? 
	페이지 1 2 3 4 5
	페이지그룹 1234(1그룹) 5(2그룹) 
	}*/
		return totalPageGroup;
	}
	public int getNowPageGroup(){
		int nowPageGroup=0;
		if(getNowPage()%pageNumberPerPageGroup==0){
			nowPageGroup=getNowPage()/pageNumberPerPageGroup;
		}else if(getNowPage()%pageNumberPerPageGroup>0){
			nowPageGroup=getNowPage()/pageNumberPerPageGroup+1;
		}
	/*현재 페이지가 속한 페이지 그룹 번호(몇 번째 페이지 그룹인지) 을 return 하는 메소드 
	1. 현재 페이지 % Page Group 내 Page 수 => 0 이면 
	둘을 / 값이 현재 페이지 그룹. 
	2. 현재 페이지 % Page Group 내 Page 수 => 0 크면 
	둘을 / 값에 +1을 한 값이 현재 페이지 그룹
	1 2 3 4 5 6 7 8 9 10*/
		return nowPageGroup;
	}
	public int getStartPageOfPageGroup(){
		int startPageOfPageGroup=pageNumberPerPageGroup*(getNowPageGroup()-1)+1;
	/*현재 페이지가 속한 페이지 그룹의 시작 페이지 번호를 return 한다.
	Page Group 내 Page 수*(현재 페이지 그룹 -1) + 1을 한 값이 첫 페이지이다.
	(페이지 그룹*페이지 그룹 개수 이 그 그룹의 마지막 번호이므로) 
	페이지 그룹 
	1 2 3 4 -> 5 6 7 8 -> 9 10*/ 
		return startPageOfPageGroup;
	}
	public int getEndPageOfPageGroup(){
		int endPageOfPageGroup=getNowPageGroup()*pageNumberPerPageGroup;
		if(endPageOfPageGroup>getTotalPage()){
			endPageOfPageGroup=getTotalPage();
		}
	/*현재 페이지가 속한 페이지 그룹의 마지막 페이지 번호를 return 한다.
	1. 현재 페이지 그룹 * 페이지 그룹 개수 가 마지막 번호이다. 
	2. 그 그룹의 마지막 페이지 번호가 전체 페이지의 마지막 페이지 번호보다 
	큰 경우는 전체 페이지의 마지막 번호를 return 한다.
	1 2 3 4 -> 5 6 7 8 -> 9 10*/
	return endPageOfPageGroup;
	}
	public boolean isPreviousPageGroup(){
		boolean isPrevioys=false;
		if(getNowPageGroup()>1){
			isPrevioys=true;
		}
	/*이전 페이지 그룹이 있는지 체크하는 메서드 
	현재 페이지가 속한 페이지 그룹이 1보다 크면 true
	ex ) 페이지 1 2 3 4 / 5 6 7 8 / 9 10 
	1 2 3 group*/
			return isPrevioys;
	}
	public boolean isNextPageGroup(){
		boolean isNextPageGroup=false;
		if(getNowPageGroup()<getTotalPageGroup()){
			isNextPageGroup=true;
		}
/*	다음 페이지 그룹이 있는지 체크하는 메서드 
	현재 페이지 그룹이 마지막 페이지 그룹(
	마지막 페이지 그룹 == 총 페이지 그룹 수) 보다 작으면 true
	* ex ) 페이지 
	1 2 3 4 / 5 6 7 8 / 9 10 
	1 2 3 group*/
		return isNextPageGroup;
	}
	
	
}
