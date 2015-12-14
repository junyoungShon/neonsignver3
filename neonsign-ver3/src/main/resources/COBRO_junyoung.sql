select MAIN_ARTICLE_NO, MAIN_ARTICLE_EMAIL,
		MAIN_ARTICLE_TITLE,
		MAIN_ARTICLE_CONTENT, MAIN_ARTICLE_HIT,
		MAIN_ARTICLE_LIKE,
		MAIN_ARTICLE_TOTAL_LIKE, MAIN_ARTICLE_DATE,
		MAIN_ARTICLE_UPDATE_DATE
		from MAIN_ARTICLE where MAIN_ARTICLE_COMPLETE = 1 order by MAIN_ARTICLE_UPDATE_DATE
		
		insert into main_article
		(MAIN_ARTICLE_NO,MAIN_ARTICLE_EMAIL,MAIN_ARTICLE_TITLE,MAIN_ARTICLE_CONTENT,MAIN_ARTICLE_HIT, 
		MAIN_ARTICLE_LIKE, MAIN_ARTICLE_TOTAL_LIKE, MAIN_ARTICLE_DATE, MAIN_ARTICLE_UPDATE_DATE,MAIN_ARTICLE_COMPLETE) 
		values(main_article_seq.nextval,'e@naver.com','화장실에서 벌어진 일','손을 씻지 않고 나왔다',0, 0, 0, 
		sysdate, to_date('1970/01/01 00:00:00',  'yyyy/mm/dd hh24:mi:ss'),0);
		
		
		select max(SUB_ARTICLE_GRADE)+1 as SUB_ARTICLE_GRADE from sub_article where MAIN_ARTICLE_NO=39 and IS_CONNECT=1
		select main_article_no,to_char(main_article_update_date,'yyyymmddhh24miss') as MAIN_ARTICLE_UPDATE_DATE from MAIN_ARTICLE where MAIN_ARTICLE_NO = 37
		update main_article set main_article_update_date = sysdate where  MAIN_ARTICLE_NO = 36
		selectListHigherLikeSubArticle
		
		select sub_article_no,sub_article_grade,is_end,to_char(sub_article_date,'yyyymmddhh24miss')
		from sub_article
		where 
		
		select rownum,sub_article_no,sub_article_grade,is_end,to_char(sub_article_date,'yyyy/mm/dd hh24:mi:ss')
		from sub_article
		where subarticle_like=(select max(subarticle_like) from sub_article where main_article_no = 1 and sub_article_grade=2)
		and  main_article_no = 1 and sub_article_grade=2 and rownum=1 order by sub_article_date desc 
		
		
			select rn,sub_article_no,sub_article_grade,is_end,to_char(sub_article_date,'yyyy/mm/dd hh24:mi:ss') from (
			select rn,sub_article_no,sub_article_grade,is_end,to_char(sub_article_date,'yyyy/mm/dd hh24:mi:ss') from sub_article
			) where subarticle_like=(select max(subarticle_like) from sub_article where main_article_no = 1 and sub_article_grade=2)
			and  main_article_no = 1 and sub_article_grade=2 and rn=1

			select rn,sub_article_no,sub_article_grade,is_end,sub_article_date from(
			select  rn,sub_article_no,sub_article_grade,is_end,sub_article_date from(
			select rownum as rn,subarticle_like,sub_article_no,sub_article_grade,is_end,to_char(sub_article_date,'yyyy/mm/dd hh24:mi:ss') 
			as sub_article_date,main_article_no from sub_article order by subarticle_like asc  
			) where subarticle_like=(select max(subarticle_like) from sub_article where main_article_no = 5 and sub_article_grade=0)
			and  main_article_no = 5 and sub_article_grade=0
			) where rn=1
		
			
			select sub_article_no,sub_article_grade,is_end,to_char(sub_article_date,'yyyymmddhh24miss') as subarticle_date from sub_article
			where subarticle_like=(select max(subarticle_like) from sub_article where main_article_no = 1 and sub_article_grade=2)
			and  main_article_no = 1 and sub_article_grade=2 order by subarticle_date desc
			
			select * from sub_article
		
		rownum = 1
		
		select is_end,sub_article_no ,
		
		update main_article set main_article_update_date = sysdate where  MAIN_ARTICLE_NO = 3
			select ma.MAIN_ARTICLE_NO, ma.MAIN_ARTICLE_TITLE, ma.MAIN_ARTICLE_CONTENT, 
	ma.MAIN_ARTICLE_TOTAL_LIKE, to_char(ma.MAIN_ARTICLE_DATE, 'YYYY-MM-DD HH24:MI:SS') as ma_date, 
	to_char(ma.MAIN_ARTICLE_UPDATE_DATE, 'YYYY-MM-DD HH24:MI:SS') as ma_MAIN_ARTICLE_UPDATE_DATE, ma.MAIN_ARTICLE_COMPLETE, bm.MEMBER_NICKNAME
	from main_article ma, brain_member bm
	where bm.MEMBER_EMAIL = ma.MAIN_ARTICLE_EMAIL and ma.MAIN_ARTICLE_COMPLETE=-1 order by ma_date desc
		sle
 1970010100:00:00
 
select bm.MEMBER_EMAIL, bm.member_Category,bm.MEMBER_NICKNAME, pa.MAIN_ARTICLE_NO, bm.MEMBER_POINT
		from BRAIN_MEMBER bm, picked_article pa	
		where bm.MEMBER_EMAIL=pa.MEMBER_EMAIL and 
		bm.MEMBER_EMAIL='a@naver.com' and bm.MEMBER_PASSWORD='aaaa'
 
		select * from brain_member
		select * from main_article m,  brain_member b,sub_article s  where
		m.MAIN_ARTICLE_NO=s.MAIN_ARTICLE_NO and m.MAIN_ARTICLE_NO=2
		and b.MEMBER_EMAIL=m.MAIN_ARTICLE_EMAIL order by s.SUB_ARTICLE_GRADE asc
		
		select * from MAIN_ARTICLE
		select * from ITJA_MEMBER
		select * from sub_article
		
		select main_article_no,sub_article_no,sub_article_grade,is_end,to_char(sub_article_date,'yyyymmddhh24miss') as subarticle_date from sub_article
			where subarticle_like=(select max(subarticle_like) from sub_article where main_article_no = 39 and sub_article_grade=0
			and main_article_no = #{mainArticleNo} and sub_article_grade=#{subAtricleGrade} order by subarticle_date desc
			
			
				select sub_article_no,sub_article_grade,is_end,to_char(sub_article_date,'yyyymmddhh24miss') as subarticle_date from sub_article
			where subarticle_like=(select max(subarticle_like) from sub_article where main_article_no =40 and sub_article_grade=0)
			and main_article_no = 40 and sub_article_grade=0 order by subarticle_date desc
			
		
		select max(SUB_ARTICLE_GRADE)+1 as SUB_ARTICLE_GRADE from sub_article where MAIN_ARTICLE_NO=46 and IS_CONNECT=1
		
		select count(*) from sub_article where member_email = 'qqqq@qqqq.eee' and SUB_ARTICLE_GRADE =4 and main_article_no = 46
		
		
		select ma.MAIN_ARTICLE_NO, ma.MAIN_ARTICLE_TITLE, ma.MAIN_ARTICLE_CONTENT, 
	ma.MAIN_ARTICLE_TOTAL_LIKE, to_char(ma.MAIN_ARTICLE_DATE, 'YYYY-MM-DD HH24:MI:SS') as ma_date, 
	to_char(ma.MAIN_ARTICLE_DATE, 'YYYY-MM-DD HH24:MI:SS') as ma_MAIN_ARTICLE_DATE, ma.MAIN_ARTICLE_COMPLETE, bm.MEMBER_NICKNAME
	from main_article ma, brain_member bm
	where bm.MEMBER_EMAIL = ma.MAIN_ARTICLE_EMAIL and ma.MAIN_ARTICLE_COMPLETE=-1 order by ma_date desc

	
	
	select 
	from 
	where
	
	
	select max(SUB_ARTICLE_GRADE)+1 as SUB_ARTICLE_GRADE from sub_article where MAIN_ARTICLE_NO=10 and IS_CONNECT=1
	