package db.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import db.model.XingzuoInfo;

public class TestXingzuoDao extends TestBaseDao{
	@Autowired
	private XingzuoDao xzDao;
	
	@Test
	public void testLoadAll(){
		List<XingzuoInfo> xzInfoList = xzDao.loadAll();
		
		assertEquals(3, xzInfoList.size());
	}
	
	@Test
	public void testFindList(){
		XingzuoInfo xzInfo = new XingzuoInfo();
		xzInfo.setXzName("摩羯");
		
		List<XingzuoInfo> xzInfoList = xzDao.findList(xzInfo);
		assertEquals(1, xzInfoList.size());
	}
	
	@Test
	public void testFindByParams(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("birthday", "01-20");
		
		List<XingzuoInfo> xzInfoList = xzDao.findByParams(params);
		assertEquals(1, xzInfoList.size());
	}
	
	@Test
	public void testUpdate(){
		XingzuoInfo xzInfo = new XingzuoInfo();
		xzInfo.setId(1);
		xzInfo.setXzName("摩羯");
		xzInfo.setXzStartTime("12-22");
		xzInfo.setXzEndTime("13-19");
		
		xzDao.update(xzInfo);
	}
}
