create table hibernate_sequence (
    next_val bigint
) engine=MyISAM;

insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

CREATE TABLE user (
    userId BIGINT NOT NULL AUTO_INCREMENT,
    email varchar(50) not null unique ,
    password varchar(100) not null,
    username varchar(50) not null unique,
    PRIMARY KEY (userId)
) engine=MyISAM;

CREATE TABLE student (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      studentName varchar(50) not null,
                      PRIMARY KEY (id)
) engine=MyISAM;

CREATE TABLE event (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      title varchar(50) not null,
                      startTime DATETIME not null,
                      endTime DATETIME not null,
                      cost DOUBLE not null,
                      userId BIGINT not null,
                      studentId BIGINT,
                      PRIMARY KEY (id)
) engine=MyISAM;