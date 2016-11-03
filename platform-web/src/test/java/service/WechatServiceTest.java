package service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import constants.WechatConstants;
import constants.enums.WxMenuType;
import model.wechat.MenuItem;
import support.SpringContextTestCase;
import util.LogUtils;

public class WechatServiceTest extends SpringContextTestCase{
	@Autowired
	private WechatService wechatService; 
	private String accessToken = "Ja7tmMYBE4L9cl7G9SyKeqWtHlpkTLEIFOrcCT2589Ytu9N8V_kom4F8pSXVnD0IGVsid2r04S8Z4_SN6jMB4M6Yh7w7WSbqGnLBBcgcd7cQPGgACAHGX";
	
	@Test
	public void testAccessToken(){
		String appId = WechatConstants.appId;
		String appSecret = WechatConstants.appSecret;
		
		String accessToken = wechatService.accessToken(appId,appSecret);
		LogUtils.logInfo("wechat accessToken : [{}]", accessToken);
	}
	
	@Test
	public void testGetCallbackIp(){
		List<String> ipList = wechatService.getCallbackIp(accessToken);
		if(ipList != null){
			for(String ip : ipList){
				LogUtils.logInfo("wechat IP : [{}]", ip);
			}
		}
	}
	
	@Test
	public void testMenuCreate(){
		List<MenuItem> menuList = new ArrayList<MenuItem>();
		MenuItem item = new MenuItem();
		item.setType(WxMenuType.CLICK.getType());
		item.setName("查星座");
		item.setKey("event_constellation");
		menuList.add(item);
		
		item = new MenuItem();
		item.setType(WxMenuType.CLICK.getType());
		item.setName("待开发");
		item.setKey("event_test");
		menuList.add(item);
		
		item = new MenuItem();
		item.setType(WxMenuType.CLICK.getType());
		item.setName("待开发2");
		item.setKey("event_test2");
		menuList.add(item);
		
		wechatService.menuCreate(accessToken, menuList);
	}
}
