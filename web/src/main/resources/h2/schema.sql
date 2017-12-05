DROP TABLE t_user IF EXISTS;

CREATE TABLE t_user (
   user_id int NOT NULL,
   user_name varchar(30) NOT NULL,
   password varchar(30) NOT NULL,
   user_type int NOT NULL,
   locked int NOT NULL,
   credit int NULL,
   real_name varchar(32) null,
   phone varchar(32) not null,
   email varchar(32) null,
   create_time timestamp NOT NULL,
   create_user varchar(16) NOT NULL,
   modify_time timestamp NOT NULL,
   modify_user varchar(16) NOT NULL,
   PRIMARY KEY (user_id)
);

