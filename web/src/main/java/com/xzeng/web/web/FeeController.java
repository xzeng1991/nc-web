package com.xzeng.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fee")
public class FeeController {
	@RequestMapping("/index")
	public String index() {
		return "student/fee";
	}
}
