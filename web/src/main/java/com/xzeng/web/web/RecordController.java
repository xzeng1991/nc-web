package com.xzeng.web.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzeng.web.dao.RecordDao;
import com.xzeng.web.domain.RecordInfo;
import com.xzeng.web.domain.ReturnT;
import com.xzeng.web.domain.UserInfo;

@Controller
@RequestMapping("/record")
public class RecordController {
	@Autowired
	private RecordDao recordDao;
	
	@RequestMapping("/index")
	public String index() {
		return "student/record";
	}
	
	@RequestMapping("/pageList")
	public @ResponseBody Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
			@RequestParam(required = false, defaultValue = "10") int length, String userName, String className,
			String filterTime) {
		// page list
		List<RecordInfo> list = recordDao.pageList(start, length, userName, className);
		int list_count = recordDao.pageListCount(start, length, userName, className);

		// package result
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("recordsTotal", list_count); // 总记录数
		maps.put("recordsFiltered", list_count); // 过滤后的总记录数
		maps.put("data", list); // 分页列表
		return maps;
	}

	@RequestMapping("/add")
	public @ResponseBody ReturnT<String> add(RecordInfo recordInfo) {
		recordDao.add(recordInfo);
		return ReturnT.SUCCESS;
	}

	@RequestMapping("/update")
	public @ResponseBody ReturnT<String> update(UserInfo userInfo) {
		recordDao.updateUser(userInfo);
		return ReturnT.SUCCESS;
	}

	@RequestMapping("/remove")
	public @ResponseBody ReturnT<String> remove(String userId) {
		recordDao.removeUser(userId);
		return ReturnT.SUCCESS;
	}
}
