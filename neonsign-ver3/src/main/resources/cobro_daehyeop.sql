drop sequence main_article_seq;
drop sequence sub_article_seq;
drop sequence report_seq;
drop table brain_member;
drop table main_article;
drop table sub_article;
drop table picked_article;
drop table tag;
drop table tag_board;
drop table search_board;
drop table report;
drop table reporter;
drop table ranking;
drop table itja_member;


select SUB_ARTICLE_NO, SUB_ARTICLE_CONTENT from SUB_ARTICLE where MAIN_ARTICLE_NO=2;
select * from SUB_ARTICLE where MAIN_ARTICLE_NO=2;
--주제글 목록
select * from MAIN_ARTICLE where MAIN_ARTICLE_COMPLETE = 1;

--완결게시물 추천수순 정렬
select ma.MAIN_ARTICLE_NO,
	ma.MAIN_ARTICLE_TITLE,
	ma.MAIN_ARTICLE_CONTENT, 
	ma.MAIN_ARTICLE_HIT,
	ma.MAIN_ARTICLE_LIKE,
	ma.MAIN_ARTICLE_TOTAL_LIKE, 
	ma.MAIN_ARTICLE_DATE,
	ma.MAIN_ARTICLE_UPDATE_DATE, 
	bm.MEMBER_NICKNAME
from main_article ma, brain_member bm
where ma.MAIN_ARTICLE_EMAIL=bm.MEMBER_EMAIL and ma.MAIN_ARTICLE_COMPLETE = 1 
order by ma.MAIN_ARTICLE_TOTAL_LIKE desc
		
--관리자 계정 등록
insert into brain_member(MEMBER_EMAIL,MEMBER_NICKNAME,MEMBER_PASSWORD,MEMBER_JOIN_DATE,MEMBER_CATEGORY) 
values('eoguq4384@gmail.com','고대협','koh4384',sysdate,'관리자');

--완결게시물 프록시 등록
insert into main_article
(MAIN_ARTICLE_NO,MAIN_ARTICLE_EMAIL,MAIN_ARTICLE_TITLE,MAIN_ARTICLE_CONTENT,MAIN_ARTICLE_HIT, 
MAIN_ARTICLE_LIKE, MAIN_ARTICLE_TOTAL_LIKE, MAIN_ARTICLE_DATE, MAIN_ARTICLE_UPDATE_DATE,MAIN_ARTICLE_COMPLETE) 
values(main_article_seq.nextval,'a@naver.com','가나다라마바사아자차카타파하','가나다라마바사! 아자차카타파하!',84, 15, 1, 
sysdate, to_date('2015/11/20 13:51:40',  'yyyy/mm/dd hh24:mi:ss'),1);
--게시물 태그 등록
insert into tag_board(TAG_NAME, MAIN_ARTICLE_NO) 
values('무협', 16);
--신규게시물 프록시 등록
insert into main_article(MAIN_ARTICLE_NO,MAIN_ARTICLE_EMAIL,MAIN_ARTICLE_TITLE,MAIN_ARTICLE_CONTENT,MAIN_ARTICLE_DATE) 
values(main_article_seq.nextval,'a@naver.com','제성이형의 Barin','도대체 무슨 생각을 하는지 모르겠다',sysdate);

select * from brain_member where MEMBER_EMAIL='a@naver.com';

-- 신규게시물 페이징 적용
select MAIN_ARTICLE_NO, MAIN_ARTICLE_TITLE, MAIN_ARTICLE_CONTENT, MAIN_ARTICLE_TOTAL_LIKE, 
	MAIN_ARTICLE_DATE, MAIN_ARTICLE_UPDATE_DATE,MAIN_ARTICLE_COMPLETE, MEMBER_NICKNAME 
from(
	select ma.MAIN_ARTICLE_NO, ma.MAIN_ARTICLE_TITLE, 
	ma.MAIN_ARTICLE_CONTENT, ma.MAIN_ARTICLE_TOTAL_LIKE, 
	to_char(ma.MAIN_ARTICLE_DATE,'YYYY-MM-DD HH24:MI:SS') as MAIN_ARTICLE_DATE, 
	to_char(ma.MAIN_ARTICLE_UPDATE_DATE,'YYYY-MM-DD HH24:MI:SS') as MAIN_ARTICLE_UPDATE_DATE, 
	ma.MAIN_ARTICLE_COMPLETE, bm.MEMBER_NICKNAME as MEMBER_NICKNAME, 
	ceil(rownum/9) as page 
	from main_article ma, brain_member bm
	where ma.MAIN_ARTICLE_EMAIL = bm.MEMBER_EMAIL and ma.MAIN_ARTICLE_COMPLETE = 0 and  ma.MAIN_ARTICLE_TOTAL_LIKE<10
	order by ma.MAIN_ARTICLE_DATE desc
)
where page=1;


select * from tag_board;
-- 신규게시물 태그순 정렬
select MAIN_ARTICLE_NO, MAIN_ARTICLE_TITLE, MAIN_ARTICLE_CONTENT, MAIN_ARTICLE_TOTAL_LIKE, 
	MAIN_ARTICLE_DATE, MAIN_ARTICLE_UPDATE_DATE,MAIN_ARTICLE_COMPLETE, MEMBER_NICKNAME 
from(
	select ma.MAIN_ARTICLE_NO, ma.MAIN_ARTICLE_TITLE, 
	ma.MAIN_ARTICLE_CONTENT, ma.MAIN_ARTICLE_TOTAL_LIKE, 
	to_char(ma.MAIN_ARTICLE_DATE,'YYYY-MM-DD HH24:MI:SS') as MAIN_ARTICLE_DATE, 
	to_char(ma.MAIN_ARTICLE_UPDATE_DATE,'YYYY-MM-DD HH24:MI:SS') as MAIN_ARTICLE_UPDATE_DATE, 
	ma.MAIN_ARTICLE_COMPLETE, bm.MEMBER_NICKNAME as MEMBER_NICKNAME, 
	ceil(rownum/9) as page 
	from main_article ma, brain_member bm, tag_board tb
	where ma.MAIN_ARTICLE_EMAIL = bm.MEMBER_EMAIL and ma.MAIN_ARTICLE_COMPLETE = 0 and  ma.MAIN_ARTICLE_TOTAL_LIKE<10
		and ma.MAIN_ARTICLE_NO=tb.MAIN_ARTICLE_NO and tb.TAG_NAME='공포'
	order by ma.MAIN_ARTICLE_DATE desc
)
where page=1;