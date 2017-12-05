INSERT INTO t_user (user_id, user_name, password, user_type, locked, credit, real_name, phone, email, create_time, create_user, modify_time, modify_user)
   VALUES (1, 'xzeng', 'pass123', 2, 0, 0, '张三', '13970004111', '123@qq.com', CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');
INSERT INTO t_user (user_id, user_name, password, user_type, locked, credit,real_name, phone, email,create_time, create_user, modify_time, modify_user)
   VALUES (2, 'admin', 'pass1234', 1, 0, 0, '李四', '13970004112', '245@qq.com', CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');
INSERT INTO t_user (user_id, user_name, password, user_type, locked, credit,real_name, phone, email,create_time, create_user, modify_time, modify_user)
   VALUES (3, 'root', 'pass1235', 0, 0, 0, '王二', '13970004113', '178@qq.com', CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');
