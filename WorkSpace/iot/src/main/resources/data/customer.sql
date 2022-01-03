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

select rownum no, c.* from (select * from customer order by name) c;





