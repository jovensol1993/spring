package com.increpas.cls.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls.dao.ReBoardDao;
import com.increpas.cls.util.PageUtil;
import com.increpas.cls.vo.ReBoardVO;

@Controller
@RequestMapping("/reBoard")
public class ReBoard {
	@Autowired
	ReBoardDao rDao;
	
	// 댓글게시판 리스트 보기 컨트롤러
	@RequestMapping("/reBoardList.cls")
	public ModelAndView reBoardList(ModelAndView mv , ReBoardVO rVO, HttpSession session, PageUtil page) {
		
		// sid 불러오고
//		int sid = Integer.parseInt((String)session.getAttribute("SID"));
		String sid = (String)session.getAttribute("SID");
		rVO.setId(sid);
		
		session.setAttribute("SID", sid);
		// avatar 만들고
		String avatar = "noimage.jpg";
		try {
			if(sid != null) {
				avatar = rDao.getAvatar(rVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// page처리하고
		int total = rDao.getReBoardTotal();
		page.setTotalCount(total);
		page.setPage();
		// vo에 페이지 담고
		rVO.setPage(page);
		// list로 rvo 가져오고
		List<ReBoardVO> list = rDao.getReBoardList(rVO);
			
		// 데이터 심고
		mv.addObject("LIST", list);
		mv.addObject("SID", sid);
		mv.addObject("AVTIMG", avatar);
		mv.addObject("PAGE", page);
		// 뷰 호출하고
		mv.setViewName("reBoard/reBoard");
		return mv;
	}
	
	// 댓글게시판 글쓰기 컨트롤러
	@RequestMapping(value="/reBoardWriteProc.cls", method=RequestMethod.POST)
	public ModelAndView reBoardWriteProc(ModelAndView mv, RedirectView rd, ReBoardVO rVO, PageUtil page) {
		int cnt = 0;
		System.out.println("******** cnt : " + cnt);
		cnt = rDao.addBoard(rVO);
		System.out.println("******** cnt : " + cnt);
		// 데이터 심고
//		mv.addObject("rVO", rVO);
		// 뷰 호출하고
		rd.setUrl("/cls/reBoard/reBoardList.cls");
		mv.setView(rd);
		return mv;
	}
	
	// 왜 콘솔에 암것두 안떠? 인식을못함..??????
	
	@RequestMapping("/reBoardDelProc.cls")
	public ModelAndView reBoardDelProc(ModelAndView mv, ReBoardVO rVO) {
		
		int cnt = rDao.deleteReBoard(rVO);
		mv.setViewName("redirect:/reBoard/reBoardList.cls");
		return mv;
	}
	
	// 쓴글 수정 처리 해주는 컨트롤러
	@RequestMapping("/reBoardEditView.cls")
	public ModelAndView reBoardEditView(ModelAndView mv, HttpServletRequest req) {
		
		mv.setViewName("reBoard/reBoardEdit");
		return mv;
	}
	
	// 편집
	@RequestMapping("/reBoardEditProc.cls")
	public ModelAndView reBoardEditProc(ModelAndView mv, ReBoardVO rVO) {
		
		int cnt = rDao.editReBoard(rVO);
		System.out.println("********  " + cnt);
		mv.addObject("URI", "/reBoard/reBoardList.cls");
		mv.setViewName("reBoard/redirectView");
		return mv;
	}
	
	// 댓글 쓰기 창 보여주는 컨트롤러
	@RequestMapping("/reBoardComment.cls")
	public ModelAndView reBoardComment(ModelAndView mv, HttpSession session) {
//		String sid = (String)session.getAttribute("SID");
		mv.setViewName("reBoard/reBoardComment");
		return mv;
	}
	
	// 댓글 쓴거 처리해주는 컨트롤러
	@RequestMapping("/reBoardCommentProc.cls")
	public ModelAndView reBoardCommentProc(ModelAndView mv, ReBoardVO rVO, HttpSession session) {
		String sid = (String)session.getAttribute("SID");
		rVO.setId(sid);
		int cnt = rDao.addReBoard(rVO)	;
		mv.setViewName("redirect:/reBoard/reBoardList.cls");
		return mv;
	}
}

