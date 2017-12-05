package com.xzeng.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/record")
public class RecordController {
	@RequestMapping("/index")
	public String index() {
		return "student/record";
	}
}
