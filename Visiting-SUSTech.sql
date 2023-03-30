drop table if exists appointment;
drop table if exists user_info;

create table user_info(
                          id serial primary key,
                          name text,
                          phone varchar(11),
                          identity_card varchar(18),
                          wechat_id text,
                          type char(10)
);

create table appointment(
                            id serial primary key,
                            user_id int references user_info(id),
                            entry_time timestamp not null,
                            departure_time timestamp not null,
                            status varchar(10) not null,
                            accompanying_name text,
                            accompanying_identity_card varchar(18)
);