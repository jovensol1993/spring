package com.increpas.cls.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.increpas.cls.dao.SurveyDao;
import com.increpas.cls.util.W3Color;
import com.increpas.cls.vo.SurveyVO;

@Controller
@RequestMapping("/survey")
public class Survey {
	@Autowired
	SurveyDao sDao;
	
	@Autowired
	W3Color w3color;
	
	@RequestMapping("/surveyInfo.cls")
	public ModelAndView surveyInfo(ModelAndView mv , HttpSession session) {
		String sid = (String)session.getAttribute("SID");
		if(sid == null) {
			mv.setViewName("redirect:/member/login.cls");
			return mv;
		} else {
			List<SurveyVO> list = sDao.getCurList(sid);
			mv.addObject("LIST", list);
			mv.setViewName("survey/surveyInfo");
			return mv;
		}
	}
	
	@RequestMapping("/survey.cls")
	public ModelAndView survey(ModelAndView mv, int sno) {
		List<SurveyVO> list = sDao.getQuestList(sno);
		mv.addObject("LIST", list);
		mv.addObject("SNO", sno);
		mv.setViewName("survey/survey");
		return mv;
	}
	
	@RequestMapping("/surveyResult.cls")
	public ModelAndView surveyResult(ModelAndView mv, HttpSession session, int sno) {
		String sid = (String)session.getAttribute("SID");
		if(sid == null) {
			mv.setViewName("redirect:/member/login.cls");
			return mv;
		} else {
			List<SurveyVO> list = sDao.getAnswerList(sno);
			mv.addObject("LIST", list);
			mv.addObject("COLOR", w3color);
			mv.setViewName("survey/surveyResult");
			return mv;
		}
	}
	
	@RequestMapping("/surveyProc.cls")
	public ModelAndView surveyProc(ModelAndView mv, SurveyVO vo) {
//	public ModelAndView surveyProc(ModelAndView mv, HttpServletRequest req, SurveyVO vo) {
		// 할일
		// 파라미터 꺼내고
		// 문제는 어떤 키값으로 데이터가 넘어오는 모른다는 것이다.
		ArrayList<SurveyVO> list = new ArrayList<SurveyVO>();
		for(int no : vo.getAqno()) {
			SurveyVO sVO = new SurveyVO();
			sVO.setId(vo.getId());
			sVO.setSno(vo.getSno());
			sVO.setQno(no);
			
			list.add(sVO);
		}
		
		int cnt = 0;
		try {
			cnt = sDao.addAnswer(list);
			mv.addObject("SNO", vo.getSno());
			mv.addObject("VIEW", "/cls/survey/surveyResult.cls");
			mv.addObject("CNT", cnt);
			
			mv.setViewName("survey/surveyRedirect");
		} catch(Exception e) {
			e.printStackTrace();
			cnt = -1;
			mv.addObject("SNO", vo.getSno());
			mv.addObject("CNT", cnt);
			mv.addObject("VIEW", "/cls/survey/survey.cls");
			mv.setViewName("survey/surveyRedirect");
		}
		/*
		Enumeration en = req.getParameterNames();
		while(en.hasMoreElements()) {
			SurveyVO sVO = new SurveyVO();
			sVO.setId(vo.getId());
			sVO.setSno(vo.getSno());
			String key = (String) en.nextElement();
			if(key.equals("cnt") || key.equals("id") || key.equals("sno")) {
				continue;
			}
			sVO.setQno(Integer.parseInt(req.getParameter(key)));
			
			list.add(sVO);
		}
		 */
		
		return mv;
	}
}

