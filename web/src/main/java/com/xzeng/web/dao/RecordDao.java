package com.xzeng.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xzeng.web.common.Constrants;
import com.xzeng.web.domain.RecordInfo;
import com.xzeng.web.domain.UserInfo;

@Repository
public class RecordDao {
	private static Logger Log = LoggerFactory.getLogger(RecordDao.class);
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public void add(RecordInfo recordInfo) {
		Random random = new Random();
		StringBuilder sql = new StringBuilder(
				"INSERT INTO t_record (record_id, user_name, class_name, create_time) ");
		sql.append("VALUES (?, ?, ?, CURRENT_TIMESTAMP)");
		Object[] objects = { random.nextInt(100), recordInfo.getUserName(), recordInfo.getClassName() };
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

	public List<RecordInfo> pageList(int offset, int pagesize, String userName, String className) {
		StringBuilder sql = new StringBuilder(
				"SELECT record_id, user_id, user_name, class_id, class_name, create_time ");
		sql.append("FROM t_record WHERE 1=1 ");
		// 查询条件
		Map<String, Object> params = new HashMap();
		if (StringUtils.isNotEmpty(userName)) {
			sql.append(" and user_name=:userName ");
			params.put("userName", userName);
		}
		if (StringUtils.isNotEmpty(className)) {
			sql.append(" and class_name=:className ");
			params.put("className", className);
		}

		List<RecordInfo> userInfos = namedJdbcTemplate.query(sql.toString(), params, new RowMapper<RecordInfo>() {
			RecordInfo result = null;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");;

			@Override
			public RecordInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				result = new RecordInfo();

				result.setRecordId(rs.getInt("record_id"));
				result.setUserId(rs.getInt("USER_ID"));
				result.setUserName(rs.getString("USER_NAME"));
				result.setClassId(rs.getInt("class_id"));
				result.setClassName(rs.getString("class_name"));
				result.setCreateTime(formatter.format(rs.getDate("create_time")));

				return result;
			}

		});
		return userInfos;
	}

	public int pageListCount(int offset, int pagesize, String userName, String className) {
		StringBuilder sql = new StringBuilder("SELECT count(1) as count FROM t_record where 1=1");
		// 查询条件
		Map<String, Object> params = new HashMap();
		if (StringUtils.isNotEmpty(userName)) {
			sql.append(" and user_name=:userName ");
			params.put("userName", userName);
		}
		if (StringUtils.isNotEmpty(className)) {
			sql.append(" and class_name=:className ");
			params.put("className", className);
		}

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
