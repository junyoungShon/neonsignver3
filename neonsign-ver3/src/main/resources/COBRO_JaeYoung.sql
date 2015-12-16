select * from BRAIN_MEMBER where MEMBER_EMAIL='ygoyo@naver.com' and MEMBER_PASSWORD='1234'
create table MEMBER_ACTION(
   MEMBER_EMAIL varchar2(16) not null,
   MEMBER_ACTION varchar2(25) not null,
   MEMBER_LASTLOGIN_DATE date,
   MEMBER_BLACK_DATE date,
   constraint fk_MEMBER_ACTION foreign key(MEMBER_EMAIL) references brain_member(MEMBER_EMAIL),
   constraint pk_MEMBER_ACTION primary key(MEMBER_EMAIL, MEMBER_ACTION)
)
drop table MEMBER_ACTION