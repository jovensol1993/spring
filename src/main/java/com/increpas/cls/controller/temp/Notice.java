package com.increpas.cls.controller.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.increpas.cls.dao.temp.NoticeDao;
import com.increpas.cls.vo.temp.NoticeVO;

@Controller
@RequestMapping("/notice")
public class Notice {
	
	@Autowired
	NoticeDao nDao;
	
	@RequestMapping("/notice.cls")
	public ModelAndView getList(ModelAndView mv) {
		
		mv.setViewName("temp/Notice");
		mv.addObject("LIST", nDao.getList());
		
		return mv;
	}
	
	@RequestMapping("/noticeDetail.cls")
	public ModelAndView getNoticeDetail(ModelAndView mv, NoticeVO nVO) {
		int cnt = nDao.updateHit(nVO);
		mv.addObject("nVO", nDao.getDetail(nVO));
		mv.setViewName("temp/NoticeDetail");
		
		return mv;
	}
	
	@RequestMapping("/noticeEditProc.cls")
	public ModelAndView updataNoticeDetail(ModelAndView mv, NoticeVO nVO) {
		String view = "";
		int cnt = nDao.updateContents(nVO);
		if(cnt != 0) {
			view = "/notice/noticeDetail.cls";
		} else {
			view = "/notice/noticeEdit.cls";
		}
		mv.addObject("URI", view);
		mv.addObject("ID", nVO.getNno());
//		System.out.println(nVO.getTitle()); null
		mv.setViewName("temp/redirect");
		return mv;
	}
	
	@RequestMapping("/noticeEdit.cls")
	public ModelAndView getNoticeEdit(ModelAndView mv, NoticeVO nVO) {
		
		nVO = nDao.getDetail(nVO);
		mv.addObject("nVO", nVO);
		mv.setViewName("temp/NoticeEdit");
		return mv;
	}
	
	@RequestMapping("/noticeDel.cls")
	public ModelAndView updateDel(ModelAndView mv, NoticeVO nVO) {
		int cnt  = nDao.updateDel(nVO);
		
		mv.setViewName("redirect:/notice/notice.cls");
		return mv;
	}
}
