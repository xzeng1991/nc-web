DROP TABLE t_user IF EXISTS;
DROP TABLE t_xingzuo IF EXISTS;
DROP TABLE t_user_menu IF EXISTS;

CREATE TABLE t_user (
   user_id int NOT NULL,
   user_name varchar(30) NOT NULL,
   password varchar(30) NOT NULL,
   user_type int NOT NULL,
   locked int NOT NULL,
   credit int NULL,
   create_time timestamp NOT NULL,
   create_user varchar(16) NOT NULL,
   modify_time timestamp NOT NULL,
   modify_user varchar(16) NOT NULL,
   PRIMARY KEY (user_id)
);

CREATE TABLE t_user_menu (
   menu_id int NOT NULL,
   parent_id int NOT NULL,
   name varchar(30) NOT NULL,
   url varchar(30) NOT NULL,
   number int NOT NULL,
   create_time timestamp NOT NULL,
   create_user varchar(16) NOT NULL,
   modify_time timestamp NOT NULL,
   modify_user varchar(16) NOT NULL,
   PRIMARY KEY (menu_id)
);

CREATE TABLE t_xingzuo (
   id int NOT NULL,
   xz_name varchar(32) NOT NULL,
   xz_info varchar(256) NOT NULL,
   xz_start_time varchar(16) NOT NULL,
   xz_end_time varchar(16) NOT NULL,
   create_time Date NOT NULL,
   create_user varchar(16) NOT NULL,
   modify_time Date NOT NULL,
   modify_user varchar(16) NOT NULL,
   PRIMARY KEY (id)
);