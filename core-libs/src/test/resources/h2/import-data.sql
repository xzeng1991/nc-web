-- 用户
INSERT INTO t_user (user_id, user_name, password, user_type, locked, credit, create_time, create_user, modify_time, modify_user)
   VALUES (1, 'xzeng', 'pass123', 2, 0, 0,CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');
INSERT INTO t_user (user_id, user_name, password, user_type, locked, credit,create_time, create_user, modify_time, modify_user)
   VALUES (2, 'admin', 'pass1234', 1, 0, 0,CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');
INSERT INTO t_user (user_id, user_name, password, user_type, locked, credit,create_time, create_user, modify_time, modify_user)
   VALUES (3, 'root', 'pass1235', 0, 0, 0,CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');
-- 菜单
INSERT INTO t_user_menu (menu_id, parent_id, name, url, number, create_time, create_user, modify_time, modify_user)
   VALUES (1, 0, '菜单一', '#', 1, CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');   
INSERT INTO t_user_menu (menu_id, parent_id, name, url, number, create_time, create_user, modify_time, modify_user)
   VALUES (2, 1, '子菜单一', '#', 1, CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');   
INSERT INTO t_user_menu (menu_id, parent_id, name, url, number, create_time, create_user, modify_time, modify_user)
   VALUES (3, 0, '菜单二', '#', 2, CURRENT_TIMESTAMP,'xzeng',CURRENT_TIMESTAMP,'xzeng');   
-- 星座   
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (1, '摩羯座', '♑️', '12-22', '12-31', CURRENT_TIMESTAMP,'xzeng', CURRENT_TIMESTAMP,'xzeng');
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (2, '摩羯座', '♑️', '01-01', '01-19', CURRENT_TIMESTAMP,'xzeng', CURRENT_TIMESTAMP,'xzeng');
INSERT INTO t_xingzuo (id, xz_name, xz_info, xz_start_time, xz_end_time, create_time, create_user, modify_time, modify_user)
   VALUES (3, '水瓶座', '♒️', '01-20', '02-18', CURRENT_TIMESTAMP, 'xzeng', CURRENT_TIMESTAMP, 'xzeng');

