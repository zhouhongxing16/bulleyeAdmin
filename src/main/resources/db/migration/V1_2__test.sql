-- 创建表demo，使用存储过程进行判断，用完后删除
drop procedure if exists createHolidays;
delimiter $$
create procedure createHolidays() begin
  if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'kb_holidays') then
    create table kb_test
    (
       id                   varchar(40) not null,
       hospital_id          varchar(40),
       start_date           varchar(20),
       end_date             varchar(20),
       type_id              varchar(40) comment 'HolidayType',
       created              timestamp default CURRENT_TIMESTAMP,
       user_id              varchar(40),
       status               int default 0,
       primary key (id)
    );

  end if;
end $$
delimiter ;
call createHolidays();
drop procedure if exists createHolidays;