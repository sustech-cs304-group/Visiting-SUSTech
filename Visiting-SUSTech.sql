drop table if exists appointment;
drop table if exists forum;
drop table if exists comment;
drop table if exists forum_like;
drop table if exists forum_resource;
drop table if exists user_info;

create table user_info(
                          openid text primary key,
                          nickname text,
                          avatar_url text,
                          name text,
                          phone varchar(11),
                          identity_card varchar(18),
                          gender integer,
                          type varchar(10)
);

create table appointment(
                            id serial primary key,
                            openid text references user_info(openid),
                            create_time timestamp,
                            identity_card varchar(18),
                            name text,
                            appointment_date date,
                            phone varchar(11),
                            status integer not null,
                            accompanying_num int,
                            purpose text,
                            comment text
);

create table forum(
                      id serial primary key,
                      openid text references user_info(openid),
                      create_date timestamp,
                      location text,
                      content text
);

create table forum_like(
                           id serial primary key,
                           openid text references user_info(openid),
                           nickname text,
                           forum_id integer not null
);

create table comment(
                        id serial primary key,
                        openid text references user_info(openid),
                        nickname text,
                        forum_id integer not null ,
                        content text
);

create table forum_resource(
                               id serial primary key,
                               forum_id integer, --references user_info(openid),
                               resource text

);