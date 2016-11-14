package web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import db.manager.XingzuoManager;
import db.model.PageResult;
import db.model.XingzuoInfo;
import model.ReturnT;
import util.LogUtils;

@Controller
@RequestMapping("/xzinfo")
public class XingzuoController {
	@Autowired
	private XingzuoManager xzManager;
	
	@RequestMapping
	public String index(Model model) {
		return "xzinfo/xzMain";
	}
	
	@RequestMapping("/pageList")
	public @ResponseBody Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
			@RequestParam(required = false, defaultValue = "10") int length, String xzName) throws Exception{
		LogUtils.logInfo("====== xzName : {} ======", xzName);
		PageResult<XingzuoInfo> pageList = xzManager.findList(xzName, start, length);

		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("recordsTotal", pageList.getPageSize());
		maps.put("recordsFiltered", pageList.getTotalCount());
		maps.put("data", pageList.getItems());

		return maps;
	}
	
	@RequestMapping("/add")
	public @ResponseBody ReturnT<String> add(XingzuoInfo xzInfo) {
		if (StringUtils.isBlank(xzInfo.getXzName())) {
			return new ReturnT<String>(500, "请输入“星座名称”");
		}
		if (StringUtils.isBlank(xzInfo.getXzStartTime())) {
			return new ReturnT<String>(500, "请输入“星座开始时间”");
		}
		if (StringUtils.isBlank(xzInfo.getXzEndTime())) {
			return new ReturnT<String>(500, "请输入“星座结束时间”");
		}
		
		// TODO jobinfo save to db

		return ReturnT.FAIL;
	}
	
	@RequestMapping("/update")
	public @ResponseBody ReturnT<String> update(XingzuoInfo xzInfo){
		if (StringUtils.isBlank(xzInfo.getXzName())) {
			return new ReturnT<String>(500, "请输入“星座名称”");
		}
		if (StringUtils.isBlank(xzInfo.getXzStartTime())) {
			return new ReturnT<String>(500, "请输入“星座开始时间”");
		}
		if (StringUtils.isBlank(xzInfo.getXzEndTime())) {
			return new ReturnT<String>(500, "请输入“星座结束时间”");
		}
		// 查询星座数据
		XingzuoInfo xzInfoDb = xzManager.findByName(xzInfo.getXzName());
		if(xzInfoDb != null){	// 将数据更新
			xzInfoDb.setXzInfo(xzInfo.getXzName());
			xzInfoDb.setXzStartTime(xzInfo.getXzStartTime());
			xzInfoDb.setXzEndTime(xzInfo.getXzEndTime());
			// 更新数据库
			xzManager.update(xzInfoDb);
			return ReturnT.SUCCESS;
		}
		
		return ReturnT.FAIL;
	}
	
	public void remove(){
		
	}
}
