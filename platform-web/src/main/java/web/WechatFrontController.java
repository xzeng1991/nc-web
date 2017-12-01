package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.wechat.FrontService;
import util.LogUtils;

//@Controller
//@RequestMapping("front/v1")
public class WechatFrontController {
	@Autowired
	private FrontService frontService;

	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	public ModelAndView demoPage() {
		ModelAndView mv = new ModelAndView("wechat/demo");
		return mv;
	}

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public ModelAndView auth(String code) {
		LogUtils.logInfo("==== The code passed by wechat : {}", code);

		String accessToken = frontService.getTokenByCode(code);

		LogUtils.logInfo("====I get the code : {} .", accessToken);
		ModelAndView mv = new ModelAndView("wechat/demo");
		return mv;

	}
}
