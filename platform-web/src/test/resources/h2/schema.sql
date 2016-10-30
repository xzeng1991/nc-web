DROP TABLE t_user IF EXISTS;

CREATE TABLE t_user (
   user_id int NOT NULL,
   user_name varchar(30) NOT NULL,
   password varchar(30) NOT NULL,
   user_type int NOT NULL,
   locked int NOT NULL,
   credit int NULL,
   PRIMARY KEY (user_id)
);