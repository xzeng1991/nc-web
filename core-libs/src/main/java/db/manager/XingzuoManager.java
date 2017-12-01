package db.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import db.dao.XingzuoDao;
import db.model.PageResult;
import db.model.XingzuoInfo;

//@Repository
public class XingzuoManager {
	@Autowired
	private XingzuoDao xzDao;

	public void update(XingzuoInfo xzInfo){
		xzDao.update(xzInfo);
	}
	
	public List<XingzuoInfo> loadAll() {
		return xzDao.loadAll();
	}

	public PageResult<XingzuoInfo> findList(String xzName, int start, int pageSize) {
		XingzuoInfo xzInfo = new XingzuoInfo();
		xzInfo.setXzName(xzName);
		// 查询记录
		List<XingzuoInfo> xzInfoList = xzDao.findList(xzInfo);
		// 组装结果
		PageResult<XingzuoInfo> xzInfoResult = new PageResult<XingzuoInfo>();
		xzInfoResult.setTotalCount(xzInfoList.size());
		int end = start + pageSize;
		if(end > xzInfoResult.getTotalCount()){
			end = xzInfoResult.getTotalCount();
		}
		xzInfoResult.setItems(xzInfoList.subList(start, end));
		xzInfoResult.setPageSize(xzInfoResult.getItems().size());
		
		return xzInfoResult;
	}
	
	public XingzuoInfo findByBirthday(String birthday){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("birthday", birthday);
		// 根据日期查询星座
		List<XingzuoInfo> xzInfoList = xzDao.findByParams(params);
		
		if(!CollectionUtils.isEmpty(xzInfoList)){
			return xzInfoList.get(0);
		}
		return null;
	}
	
	public XingzuoInfo findByName(String xzName){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("xzName", xzName);
		// 根据名称查询星座
		List<XingzuoInfo> xzInfoList = xzDao.findByParams(params);
		
		if(!CollectionUtils.isEmpty(xzInfoList)){
			return xzInfoList.get(0);
		}
		return null;
	}
}
