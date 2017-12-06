DROP TABLE t_user IF EXISTS;
DROP TABLE t_class IF EXISTS;
DROP TABLE t_fee IF EXISTS;
DROP TABLE t_record IF EXISTS;

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

CREATE TABLE t_class (
   class_id int NOT NULL,
   class_name varchar(32)  NULL,
   create_time timestamp  NULL,
   create_user varchar(16)  NULL,
   modify_time timestamp  NULL,
   modify_user varchar(16)  NULL,
   PRIMARY KEY (class_id)
);

CREATE TABLE t_fee (
   fee_id int NOT NULL,
   user_id int  NULL,
   user_name varchar(32)  NULL,
   class_id int  NULL,
   class_name varchar(32)  NULL,
   amount int NULL,
   create_time timestamp  NULL,
   create_user varchar(16)  NULL,
   modify_time timestamp  NULL,
   modify_user varchar(16)  NULL,
   PRIMARY KEY (fee_id)
);

CREATE TABLE t_record (
   record_id int NOT NULL,
   user_id int  NULL,
   user_name varchar(32)  NULL,
   class_id int  NULL,
   class_name varchar(32)  NULL,
   create_time timestamp  NULL,
   create_user varchar(16)  NULL,
   modify_time timestamp  NULL,
   modify_user varchar(16)  NULL,
   PRIMARY KEY (record_id)
);

