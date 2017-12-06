INSERT INTO t_user (user_id, user_name, password, user_type, locked, credit, real_name, phone, email, create_time, create_user, modify_time, modify_user)
   VALUES (1, 'xzeng', 'pass123', 2, 0, 0, '张三', '13970004111', '123@qq.com', CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');
INSERT INTO t_user (user_id, user_name, password, user_type, locked, credit,real_name, phone, email,create_time, create_user, modify_time, modify_user)
   VALUES (2, 'admin', 'pass1234', 1, 0, 0, '李四', '13970004112', '245@qq.com', CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');
INSERT INTO t_user (user_id, user_name, password, user_type, locked, credit,real_name, phone, email,create_time, create_user, modify_time, modify_user)
   VALUES (3, 'root', 'pass1235', 0, 0, 0, '王二', '13970004113', '178@qq.com', CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');

INSERT INTO t_class (class_id, class_name)
   VALUES (1, '语文');
INSERT INTO t_class (class_id, class_name)
   VALUES (2, '数学');
INSERT INTO t_class (class_id, class_name)
   VALUES (3, '英语');
   
INSERT INTO t_fee (fee_id, user_id, user_name, class_id, class_name, amount)
   VALUES (1, 1, '张三', 1, '语文', 10);
INSERT INTO t_fee (fee_id, user_id, user_name, class_id, class_name, amount)
   VALUES (2, 2, '李四', 2, '数学', 12);
INSERT INTO t_fee (fee_id, user_id, user_name, class_id, class_name, amount)
   VALUES (3, 3, '王二', 3, '英语', 15);
   
INSERT INTO t_record (record_id, user_id, user_name, class_id, class_name, create_time)
   VALUES (1, 1, '张三', 1, '语文', CURRENT_TIMESTAMP);
INSERT INTO t_record (record_id, user_id, user_name, class_id, class_name, create_time)
   VALUES (2, 2, '李四', 2, '数学', CURRENT_TIMESTAMP);
INSERT INTO t_record (record_id, user_id, user_name, class_id, class_name, create_time)
   VALUES (3, 3, '王二', 3, '英语', CURRENT_TIMESTAMP);