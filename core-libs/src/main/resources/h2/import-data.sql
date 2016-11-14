INSERT INTO t_user (user_id, user_name, password, user_type, locked, credit, create_time, create_user, modify_time, modify_user)
   VALUES (1, 'xzeng', 'pass123', 2, 0, 0,CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');
INSERT INTO t_user (user_id, user_name, password, user_type, locked, credit,create_time, create_user, modify_time, modify_user)
   VALUES (2, 'admin', 'pass1234', 1, 0, 0,CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');
INSERT INTO t_user (user_id, user_name, password, user_type, locked, credit,create_time, create_user, modify_time, modify_user)
   VALUES (3, 'root', 'pass1235', 0, 0, 0,CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');

INSERT INTO t_user_menu (menu_id, parent_id, name, url, number, create_time, create_user, modify_time, modify_user)
   VALUES (1, 0, '菜单一', '#', 1, CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');   
INSERT INTO t_user_menu (menu_id, parent_id, name, url, number, create_time, create_user, modify_time, modify_user)
   VALUES (2, 1, '子菜单一', '#', 1, CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');   
INSERT INTO t_user_menu (menu_id, parent_id, name, url, number, create_time, create_user, modify_time, modify_user)
   VALUES (3, 0, '菜单二', '#', 2, CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');   
   
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (1, '摩羯座', '♑️', '12-22', '12-31', CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (2, '摩羯座', '♑️', '01-01', '01-19', CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (3, '水瓶座', '♒️', '01-20', '02-18', CURRENT_TIMESTAMP, 'xzeng', CURRENT_TIMESTAMP, 'xzeng');
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (4, '双鱼座', '♓️', '02-19', '03-20', CURRENT_TIMESTAMP, 'xzeng', CURRENT_TIMESTAMP, 'xzeng');
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (5, '白羊座', '😊', '03-21', '04-20', CURRENT_TIMESTAMP, 'xzeng', CURRENT_TIMESTAMP, 'xzeng');
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (6, '金牛座', '♉️', '04-21', '05-20', CURRENT_TIMESTAMP, 'xzeng', CURRENT_TIMESTAMP, 'xzeng');
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (7, '双子座', '♊️', '05-21', '06-21', CURRENT_TIMESTAMP, 'xzeng', CURRENT_TIMESTAMP, 'xzeng');
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (8, '巨蟹座', '♋️', '06-22', '07-22', CURRENT_TIMESTAMP, 'xzeng', CURRENT_TIMESTAMP, 'xzeng');
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (9, '狮子座', '♌️', '07-23', '08-23', CURRENT_TIMESTAMP, 'xzeng', CURRENT_TIMESTAMP, 'xzeng');
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (10, '处女座', '♍️', '08-24', '09-23', CURRENT_TIMESTAMP, 'xzeng', CURRENT_TIMESTAMP, 'xzeng');
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (11, '天平座', '🍮', '09-24', '10-23', CURRENT_TIMESTAMP, 'xzeng', CURRENT_TIMESTAMP, 'xzeng');
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (12, '天蝎座', '♏️', '10-24', '11-21', CURRENT_TIMESTAMP, 'xzeng', CURRENT_TIMESTAMP, 'xzeng');
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (13, '射手座', '♐️', '11-22', '12-21', CURRENT_TIMESTAMP, 'xzeng', CURRENT_TIMESTAMP, 'xzeng');
