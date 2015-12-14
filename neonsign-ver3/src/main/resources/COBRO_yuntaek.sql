		select * from main_article m,  brain_member b   where
		 m.MAIN_ARTICLE_NO=1
		and b.MEMBER_EMAIL=m.MAIN_ARTICLE_EMAIL 
		 
		SELECT MAX(report_no) as report_no FROM report
		
		select * from REPORTER
		
		    select max(SUB_ARTICLE_GRADE)+1 as SUB_ARTICLE_GRADE from sub_article where MAIN_ARTICLE_NO=1 and IS_CONNECT=1
		--이어진글 쿼리
		select * from sub_article sa , BRAIN_MEMBER bm 
		where sa.MEMBER_EMAIL=bm.MEMBER_EMAIL and sa.MAIN_ARTICLE_NO=1 and sa.IS_CONNECT=1 order by sa.SUB_ARTICLE_GRADE asc
		
		--잇는글 쿼리
		select * from sub_article sa, BRAIN_MEMBER bm 
		where sa.MEMBER_EMAIL=bm.MEMBER_EMAIL and sa.MAIN_ARTICLE_NO=1 and  sa.SUB_ARTICLE_GRADE=1
		
		select max(SUB_ARTICLE_GRADE)+1 from sub_article where MAIN_ARTICLE_NO=1 and IS_CONNECT=1;
	select * from REPORT r , MAIN_ARTICLE ma, SUB_ARTICLE sa, brain_member bm where
	r.MAIN_ARTICLE_NO=ma.MAIN_ARTICLE_NO and ma.MAIN_ARTICLE_NO=sa.MAIN_ARTICLE_NO and
 	ma.MAIN_ARTICLE_EMAIL=bm.MEMBER_EMAIL 
	 and r.SUB_ARTICLE_NO is not null order by REPORT_NO desc
 	and sa.SUB_ARTICLE_NO=r.SUB_ARTICLE_NO
		select * from  SUB_ARTICLE sa
		where MAIN_ARTICLE_NO= 9 and  
		--신고리스트에서 해당 신고글 삭제
		delete tablename where condition
		--신고한 회원 목록
		select MEMBER_EMAIL from reporter where REPORT_NO=6
		
		--신고한 회원에게 10 포인트 지급
		update BRAIN_MEMBER SET MEMBER_POINT=MEMBER_POINT+10 
		WHERE MEMBER_EMAIL='a@naver.com' 

		select * from main_article m,  brain_member b,sub_article s  where
		m.MAIN_ARTICLE_NO=s.MAIN_ARTICLE_NO and m.MAIN_ARTICLE_NO=3 
		and b.MEMBER_EMAIL=m.MAIN_ARTICLE_EMAIL
 
		select * from main_article m,  brain_member b,sub_article s  
		where m.MAIN_ARTICLE_NO=s.MAIN_ARTICLE_NO and 
		m.
		 order by 
		s.SUB_ARTICLE_GRADE asc
		
		select * from MAIN_ARTICLE where MAIN_ARTICLE_NO=21 
		select * from SUB_ARTICLE
		
		select * from BRAIN_MEMBER where MEMBER_EMAIL='a@naver.com'
		
		update brain_member set col1=val1 from brain_member where condition
		
		select * from MAIN_ARTICLE ma, BRAIN_MEMBER bm where 
		MAIN_ARTICLE_NO=21 and  ma.MAIN_ARTICLE_EMAIL=bm.MEMBER_EMAIL
		
		    select *
    from brain_member
    where MEMBER_CATEGORY ='일반회원'
    order by MEMBER_JOIN_DATE desc 
		