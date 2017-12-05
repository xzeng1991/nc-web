DROP TABLE t_user IF EXISTS;

CREATE TABLE t_user (
   user_id int NOT NULL,
   user_name varchar(32)  NULL,
   password varchar(32)  NULL,
   user_type int  NULL,
   locked int  NULL,
   credit int NULL,
   real_name varchar(32) null,
   phone varchar(32)  null,
   email varchar(32) null,
   create_time timestamp  NULL,
   create_user varchar(16)  NULL,
   modify_time timestamp  NULL,
   modify_user varchar(16)  NULL,
   PRIMARY KEY (user_id)
);

