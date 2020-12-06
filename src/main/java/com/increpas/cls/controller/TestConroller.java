package com.increpas.cls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// 이 클래스가 컨트롤러의 역할을 하려면 @Controller라는 어노테이션을 붙여야 한다.
public class TestConroller {
	
	@RequestMapping("/test/test01.cls")
	public void getTest01() {
		System.out.println("test01 출력");
	}
}
