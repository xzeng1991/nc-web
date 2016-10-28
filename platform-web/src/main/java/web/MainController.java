package web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.request.InitWechatRequest;
import model.response.WxTextMessage;

@Controller
public class MainController {
	@RequestMapping("/handle51")
	public @ResponseBody WxTextMessage index(@RequestBody InitWechatRequest initWechatRequest,
			HttpServletResponse resp) {
		System.out.println(initWechatRequest);
		resp.setContentType(MediaType.APPLICATION_XML_VALUE);
		WxTextMessage textMessage = new WxTextMessage();
		textMessage.setContent("hello");
		textMessage.setCreateTime(System.currentTimeMillis());
		textMessage.setFromUserName("xzeng");
		textMessage.setMsgType("world");
		textMessage.setToUserName("rmy");

		return textMessage;
	}
}
