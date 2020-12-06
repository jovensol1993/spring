package com.increpas.cls.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.increpas.cls.dao.GBoardDao;
import com.increpas.cls.service.GBoardService;
import com.increpas.cls.util.PageUtil;
import com.increpas.cls.vo.GBoardVO;

// 컨트롤러로 작동하기 위해선 아래 어노테이션을 써야 한다.
@Controller
@RequestMapping("/guestBoard")
public class GuestBoard {
	
	@Autowired
	GBoardDao gDao;
	
	@Autowired
	GBoardService gService;
	
	// 방명록 리스트 폼 보기 요청 처리 함수
	@RequestMapping("/guestBoard.cls")
	public ModelAndView gBoardForm(ModelAndView mv, PageUtil page, HttpSession session, GBoardVO gVO) {
		
		gService.setGBoardPage(mv, page, session, gVO);
		return mv;
	}
}
