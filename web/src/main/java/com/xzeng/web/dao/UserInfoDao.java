package com.xzeng.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.h2.util.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xzeng.web.common.Constrants;
import com.xzeng.web.domain.UserInfo;

@Repository
public class UserInfoDao {
	private static Logger Log = LoggerFactory.getLogger(UserInfoDao.class);
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public void addUser(UserInfo userInfo) {
		Random random = new Random();
		StringBuilder sql = new StringBuilder(
				"INSERT INTO t_user (user_id, real_name, phone, email, LOCKED, create_time) ");
		sql.append("VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)");
		Object[] objects = { random.nextInt(), userInfo.getRealName(), userInfo.getPhone(), userInfo.getEmail(),
				Constrants.LOCK_OK };
		jdbcTemplate.update(sql.toString(), objects);
	}

	public void updateUser(UserInfo userInfo) {
		StringBuilder sql = new StringBuilder(
				"update t_user set real_name = ?, phone = ?, email = ? where user_id = ? ");
		Object[] objects = { userInfo.getRealName(), userInfo.getPhone(), userInfo.getEmail(), userInfo.getUserId() };
		jdbcTemplate.update(sql.toString(), objects);
	}

	public void removeUser(String userId) {
		StringBuilder sql = new StringBuilder("update t_user set LOCKED = ? where user_id = ? ");
		Object[] objects = { Constrants.LOCK_DEL, userId };
		jdbcTemplate.update(sql.toString(), objects);
	}

	public UserInfo findUserInfo(String userName) {
		StringBuilder sql = new StringBuilder(
				"SELECT USER_ID, USER_NAME, PASSWORD, USER_TYPE, LOCKED, CREDIT, REAL_NAME, PHONE, EMAIL, CREATE_TIME, CREATE_USER, MODIFY_TIME, MODIFY_USER ");
		sql.append("FROM T_USER WHERE USER_NAME =:userName");

		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", userName);

		UserInfo userInfo = namedJdbcTemplate.query(sql.toString(), params, new ResultSetExtractor<UserInfo>() {
			UserInfo result = null;

			@Override
			public UserInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					result = new UserInfo();
					result.setUserId(rs.getInt("USER_ID"));
					result.setUserName(rs.getString("USER_NAME"));
					result.setPassword(rs.getString("PASSWORD"));
					result.setUserType(rs.getInt("USER_TYPE"));
					result.setLocked(rs.getInt("LOCKED"));
					result.setCredit(rs.getInt("CREDIT"));
					result.setRealName(rs.getString("REAL_NAME"));
					result.setPhone(rs.getString("PHONE"));
					result.setEmail(rs.getString("EMAIL"));
				}
				return result;
			}
		});

		return userInfo;
	}

	public List<UserInfo> pageList(int offset, int pagesize, String realName, String phone) {
		StringBuilder sql = new StringBuilder(
				"SELECT USER_ID, USER_NAME, PASSWORD, USER_TYPE, LOCKED, CREDIT, REAL_NAME, PHONE, EMAIL, CREATE_TIME, CREATE_USER, MODIFY_TIME, MODIFY_USER ");
		sql.append("FROM T_USER WHERE 1=1 ");
		// 查询条件
		Map<String, Object> params = new HashMap();
		if (StringUtils.isNotEmpty(realName)) {
			sql.append(" and REAL_NAME=:realName ");
			params.put("realName", realName);
		}
		if (StringUtils.isNotEmpty(phone)) {
			sql.append(" and PHONE=:phone ");
			params.put("phone", phone);
		}

		sql.append(" and LOCKED=:lock");
		params.put("lock", Constrants.LOCK_OK);

		List<UserInfo> userInfos = namedJdbcTemplate.query(sql.toString(), params, new RowMapper<UserInfo>() {
			UserInfo result = null;

			@Override
			public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				result = new UserInfo();

				result.setUserId(rs.getInt("USER_ID"));
				result.setUserName(rs.getString("USER_NAME"));
				result.setPassword(rs.getString("PASSWORD"));
				result.setUserType(rs.getInt("USER_TYPE"));
				result.setLocked(rs.getInt("LOCKED"));
				result.setCredit(rs.getInt("CREDIT"));
				result.setRealName(rs.getString("REAL_NAME"));
				result.setPhone(rs.getString("PHONE"));
				result.setEmail(rs.getString("EMAIL"));

				return result;
			}

		});
		return userInfos;
	}

	public int pageListCount(int offset, int pagesize, String realName, String phone) {
		StringBuilder sql = new StringBuilder("SELECT count(1) as count FROM T_USER where 1=1");
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		params.put("realName", realName);

		int count = namedJdbcTemplate.query(sql.toString(), params, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getInt("count");
				}
				return 0;
			}
		});
		return count;
	}
}
