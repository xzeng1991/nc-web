package web;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.request.InitWechatRequest;
import service.WechatService;
import util.LogUtils;
import util.MessageUtil;
import util.wechat.SignUtil;

@Controller
@RequestMapping("mapi/v1/we")
public class WechatController {
	@Autowired
	private WechatService wechatService;

	@RequestMapping(value = "/wxCallback", method = RequestMethod.GET)
	public void registerWechat(InitWechatRequest initReq, PrintWriter out) {
		LogUtils.logInfo("Wechat init start.");
		String token = "token1012nctechnology";

		// 微信校验、认证
		if (SignUtil.checkSignature(initReq.getSignature(), initReq.getTimestamp(), initReq.getNonce(), token)) {
			LogUtils.logInfo("Success! {}", initReq);
			out.print(initReq.getEchostr());
		} else {
			LogUtils.logError("Fail! {}", initReq);
			out.print("");
		}

		LogUtils.logInfo("Wechat init end.");
	}

	@RequestMapping(value = "/wxCallback", method = RequestMethod.POST)
	public void wechatMessage(HttpServletRequest request, PrintWriter out) {
		String respMsg = "success";
		Map<String, String> wechatData = MessageUtil.parseXml(request);
		if (!CollectionUtils.isEmpty(wechatData)) {
			respMsg = wechatService.processWechatMsg(wechatData);
		}
		out.print(respMsg);
		out.close();
	}
}
