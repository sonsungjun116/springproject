select * from tab;
select * from seq;
select * from myboard;
select count(*) from myboard;

insert into myboard values(myboard_seq.nextval,'홍길동','1234',
		'게시판 연습','게시판 내용',0,sysdate);
		