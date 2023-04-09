drop table if exists appointment;
drop table if exists user_info;

create table user_info(
                          openid text primary key,
                          nickname text,
                          avatar_url text,
                          name text,
                          phone varchar(11),
                          identity_card varchar(18),
                          gender int,
                          type char(10)
);

create table appointment(
                            id serial primary key,
                            openid text references user_info(openid),
                            idcard varchar(18),
                            name text,
                            appointment_date date,
                            phone varchar(11),
                            status varchar(10) not null,
                            accompanying_num int,
                            purpose text
);