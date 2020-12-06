package com.increpas.cls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
//	localhost/main.cls를  호출하면 main.jsp가 호출된다.
	@RequestMapping("/main.cls")
	public String getMain() {
		return "main";
	}
}
