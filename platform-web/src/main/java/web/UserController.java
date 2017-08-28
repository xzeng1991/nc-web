package web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import constants.enums.UserStatus;
import constants.enums.UserType;
import db.manager.UserManager;
import db.model.PageResult;
import db.model.UserInfo;
import model.ReturnT;
import service.RedisManager;
import util.CookieUtil;
import util.LogUtils;
import web.interceptor.PermissionInterceptor;

@Controller
@RequestMapping("/userInfo")
public class UserController {
	@Autowired
	private UserManager userManager;
	//@Autowired
	private RedisManager redisManager;

	@RequestMapping
	public String index(HttpServletRequest request, Model model) {
		String userId = CookieUtil.get(request, PermissionInterceptor.LOGIN_IDENTITY_KEY);

		String userJson = redisManager.getValueOps().get(userId);
		UserInfo userInfo = JSONObject.parseObject(userJson, UserInfo.class);
		// 查询出用户有权限的组
		List<UserType> userTypeList = new ArrayList<UserType>();
		for (UserType item : UserType.values()) {
			if (item.getType() >= userInfo.getUserType()) {
				userTypeList.add(item);
			}
		}

		model.addAttribute("userTypeList", userTypeList);
		model.addAttribute("userStatusList", UserStatus.values());
		return "userInfo/userMain";
	}

	@RequestMapping("/pageList")
	public @ResponseBody Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
			@RequestParam(required = false, defaultValue = "10") int length, String userName, Integer userType)
			throws Exception {
		LogUtils.logInfo("====== userName : {}; userType : {} ======", userName, userType);
		if (StringUtils.isEmpty(userName)) {
			userName = null;
		}

		PageResult<UserInfo> pageList = userManager.findPage(userName, userType, start, length);

		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("recordsTotal", pageList.getPageSize());
		maps.put("recordsFiltered", pageList.getTotalCount());
		maps.put("data", pageList.getItems());

		return maps;
	}

	@RequestMapping("add")
	public @ResponseBody ReturnT<String> addUser(HttpServletRequest request, Integer userType, String userName,
			String password, Integer locked) {
		if (UserType.match(userType) == null) {
			return new ReturnT<String>(500, "请选择“用户类型”");
		}
		if (StringUtils.isBlank(userName)) {
			return new ReturnT<String>(500, "请输入“用户名”");
		}
		if (StringUtils.isBlank(password)) {
			return new ReturnT<String>(500, "请输入“密码”");
		}
		if (UserStatus.match(locked) == null) {
			return new ReturnT<String>(500, "请选择“状态”");
		}

		UserInfo userInfo = new UserInfo(userName, password, userType, locked);
		String operator = CookieUtil.getCookieVal(request, "userName");
		userInfo.setCreateUser(operator);
		userManager.addUser(userInfo);

		return ReturnT.SUCCESS;
	}

	@RequestMapping("/remove")
	public @ResponseBody ReturnT<String> removeUser(Integer userId) {
		if (userId == null) {
			return new ReturnT<String>(500, "请输入“用户ID”");
		}

		userManager.removeUser(userId);

		return ReturnT.SUCCESS;
	}

	@RequestMapping("/update")
	public @ResponseBody ReturnT<String> updateUser(HttpServletRequest request, UserInfo userInfo) {
		if (UserType.match(userInfo.getUserType()) == null) {
			return new ReturnT<String>(500, "请选择“用户类型”");
		}
		if (StringUtils.isBlank(userInfo.getUserName())) {
			return new ReturnT<String>(500, "请输入“用户名”");
		}
		if (StringUtils.isBlank(userInfo.getPassword())) {
			return new ReturnT<String>(500, "请输入“密码”");
		}
		if (UserStatus.match(userInfo.getLocked()) == null) {
			return new ReturnT<String>(500, "请选择“状态”");
		}
		String operator = CookieUtil.getCookieVal(request, "userName");
		userInfo.setModifyUser(operator);
		
		userManager.updateUser(userInfo);

		return ReturnT.SUCCESS;
	}
}
