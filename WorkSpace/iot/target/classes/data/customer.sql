--테이블 생성
create table customer (
    id number constraint customer_id_pk primary key,
    name varchar2(50) not null,
    gender varchar(3) default '여' not null,
    email varchar(50),
    phone varchar(13)
);

-- 시퀀스 생성
create sequence seq_customer
start with 1 increment by 1;

-- 레코드 사입
insert into customer(id, name, gender)
values (seq_customer.nextval, '최성욱', '남');

insert into customer(name, gender)
values ('아이유', '여');

commit;

-- 트리거(trigger) 생성
create trigger trg_customer
    before insert on customer -- customer table 에 insert 되기 전데
    for each row              -- 모든행에 대하여 시작한다.
begin
    select seq_customer.nextval into :new.id from dual;
    -- 시퀀스의 데이터를 담고 있는 테이블은 없으므로 더미 테이블(dual)에서 조회한다.
end;
/ -- 끝 슬래시까지 적시해야 한다.


--회원 등록
select rownum no, c.* from (select * from customer order by name) c;

insert into customer(name, gender, email, phone)
values ('user1' , '남', 'user1@hanul.com', '010-1234-5678');

insert into customer(name, gender, email, phone)
values ('user'||SEQ_CUSTOMER.nextval , '남', 'user'||SEQ_CUSTOMER.nextval||'hanul.com', '010-1234-5678');

insert into customer(name, gender, email, phone)
values ('user'||SEQ_CUSTOMER.nextval , '여', 'user'||SEQ_CUSTOMER.nextval||'hanul.com', '010-1234-5678');

commit;

select * from customer;

-- 사원 목록 조회

select e.*, (select department_name from departments
					where e.department_id = department_id) department_name,
					(select job_title from jobs 
					where e.job_id = job_id) job_titile
		from employees e
		order by employee_id

-- 사원 상제 조회

select e.*, d.department_name, j.job_title 
from employees e inner join departments d
on e.department_id = d.department_id
inner join jobs j
on e.job_id = j.job_id
where employee_id = 102

-- membertable 생성
drop table member;

create table member (
   id		    varchar2(100),
   name		    varchar2(20),
   pw	    	varchar2(20),
   addr		    varchar2(200),
   post		    varchar2(10),
   tel 		    varchar2(20),
   gender   	varchar2(3) default '여',
   email		varchar2(100),
   birth   	  	varchar2(30),
   naver     	varchar2(100),
   kakao     	varchar2(100),
constraint member_id_pk primary key(id)
);

insert into member (id, name, pw)
values ('hanul', '한울', 'hanul');

insert into member (id, name, pw)
values ('master', '관리자', 'master');

commit;

select * from member;
        
    
        