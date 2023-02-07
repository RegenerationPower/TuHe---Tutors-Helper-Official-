create table hibernate_sequence (
    next_val bigint
) engine=MyISAM;

insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

CREATE TABLE user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email varchar(50) not null unique ,
    password varchar(100) not null,
    username varchar(50) not null unique,
    PRIMARY KEY (id)
) engine=MyISAM
