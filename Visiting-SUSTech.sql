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
                            entry_time timestamp not null,
                            departure_time timestamp not null,
                            status varchar(10) not null,
                            accompanying_name text,
                            accompanying_identity_card varchar(18)
);