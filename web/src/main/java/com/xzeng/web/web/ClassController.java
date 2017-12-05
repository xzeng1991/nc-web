package com.xzeng.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/class")
public class ClassController {
	@RequestMapping("/index")
	public String index() {
		return "student/class";
	}
}
