package com.increpas.cls.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.*;

import com.increpas.cls.dao.*;
import com.increpas.cls.vo.*;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;
import com.increpas.cls.util.*;

@Controller
// 전역으로 /member를  설정해두었다.
@RequestMapping("/member")
public class Member {
	
	@Autowired
	MemberDao mDao;

	@Autowired
	W3Color w3color;
	
	@RequestMapping("/login.cls") // ==> 클래스의 /member와 함수의 /login.cls를 합쳐서 /member/loginProc.cls로 처리한다
	public String loginPage() {
		return "member/login";
	}

	@RequestMapping(path="/loginProc.cls", params = { "id", "pw" }, method = RequestMethod.POST)
	// *** vo 따로 설정안해도 input name이 같으면 알아서= 셋팅된다. 
	public ModelAndView loginProc(ModelAndView mv, RedirectView rd, HttpSession session, MemberVO mVO) {
		int cnt = mDao.loginCnt(mVO);
		if (cnt == 0) {
			rd.setUrl("/cls/member/login.cls");
		} else
			session.setAttribute("SID", mVO.getId());
		rd.setUrl("/cls/main.cls");

		mv.setView(rd);
		return mv;
	}

//		return "member/join";
	/*
	 * 이 컨트롤러는 역할이 회원가입 뷰를 띄워주는 역할을 한다. 즉, 뷰만 보여주면 된다. 그런데 반환값 타입이 void인 경우는 요청내용을
	 * 이용해서 보여줄 뷰를 선택한다. 이 함수에서는 요청 내용이 localhost/cls/member/join.cls 이고 이것을 이용해서
	 * 보여주는 뷰는 prefix + member/join + surfix 로 만들어질 것이다. 이때 prefirx :
	 * /WEB-INF/views/ surfix : .jsp
	 * 
	 * 결과적으로 부르는 뷰는 /WEB-INF/views/member/join.jsp 를 보여주게 된다.
	 * 
	 * 이 함수내에서 처리할 내용을 로그인이 되어있는 회원의 경우는 리다이렉트로 main.cls로 보내야 한다.
	 */
	@RequestMapping("/join.cls")
	public ModelAndView joinForm(HttpSession session, ModelAndView mv) {
		String sid = (String) session.getAttribute("SID");
		if (sid != null) {
			/*			
 			rd.setUrl("/cls/main.cls");
			mv.setView(rd); // redirect 로 뷰를 호출하는 경우
			mv.setViewName("redirect:/main.cls");
			*/
		} else {
			// 데이터 만들고
			List<AvatarVO> list = mDao.getAvtList();
			// 데이터 뷰에 보내고
			mv.addObject("LIST", list);
			// 뷰 부르고
			// 넘기면 prefix surfix가 붙여줌
			mv.setViewName("member/join"); // forward로 뷰를 부르는 경우
		}
		
		return mv;
	}	
//	parameter 받을 때  ajax에 넘겨주는 변수 id로 읽어야한다.
	@RequestMapping("/idCheck.cls")
	// 반환되는 형태를 JSON으로 변경해줌
	@ResponseBody
	public HashMap<String, String> idCheck(String id) {
		// 할일
		// 데이터베이스에서 조회화고
		// 참고 json 형식 var 변수 = {키값: 데이터, 키값:데이터};
		/*
		int cnt = mDao.getIdCnt(id);
		String result = (cnt == 0)? "OK" : "NO";
		map.put("result", result);
		*/
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", mDao.getIdCnt(id) == 0 ? "OK" : "NO");
		// ==> {'result': 'OK'} or {'result': 'NO'}
		return map;
	}
	/*
	@RequestMapping("/joinProc.cls")
	public ModelAndView joinProc(ModelAndView mv, MemberVO mVO, HttpSession session) {
		// 할일
		// DB 작업하고
		
		int cnt = mDao.insertMember(mVO);
		
		if(cnt == 1) {
			// 성공하면 로그인처리하고
			session.setAttribute("SID", mVO.getId());
			// 메인페이지로 이동하고
			mv.setViewName("redirect:/main.cls");
		} else {
			// 실패한 경우
			// 회원가입페이지로 다시 이동시키고
			mv.setViewName("redirect:/member/join.cls");
		}
		return mv;
	}
	*/
	@RequestMapping(value="/joinProc.cls", method=RequestMethod.POST)
	@ResponseBody
	public String joinProc( MemberVO mVO, HttpSession session) {
		
		String result = "OK";
//		System.out.println(mVO.getId());
		// VO 가 완성됬으니 데이터 베이스 작업(DAO) 하고
		int cnt = mDao.insertMember(mVO);
		if(cnt == 1) {
			session.setAttribute("SID", mVO.getId());
		} else {
			result = "NO";
			
		}
		/*
		MultipartRequest multi;
		String path = session.getServletContext().getRealPath("resources/img/upload");
		System.out.println("### path : " + path);
		String result = "OK";
		try {
			multi = new MultipartRequest(req, path, 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
			System.out.println("***********************************");
			
			String id = multi.getParameter("id");
			String pw = multi.getParameter("pw");
			String name = multi.getParameter("name");
			String mail = multi.getParameter("mail");
			String gen = multi.getParameter("gen");
			String sno = multi.getParameter("avt");
			int avt = Integer.parseInt(sno);
			
			System.out.println("######joinproc id : "+ mVO.getId());
			mVO.setId(id);
			mVO.setPw(pw);
			mVO.setName(name);
			mVO.setMail(mail);
			mVO.setGen(gen);
			mVO.setAvt(avt);
			
			// VO 가 완성됬으니 데이터 베이스 작업(DAO) 하고
			int cnt = mDao.insertMember(mVO);
			if(cnt == 1) {
				session.setAttribute("SID", id);
			} else {
				result = "NO";
			}
		} catch(Exception e) {
			System.out.println("데이터 전송 실패");
			e.printStackTrace();
		}
		*/
		return result;
	}
	
	// 회원 정보 보는 컨트롤러
	@RequestMapping("/memberInfo.cls")
	public ModelAndView getInfo(ModelAndView mv, HttpSession session, RedirectView rv, String id, String msg) {
//		String id = (String) session.getAttribute("SID");
		if(msg != null) {
			mv.addObject("MSG", msg);	
		}
		if(id != null) {
			mv.addObject("ID", id);	
		}
		if (id == null) {
			rv.setUrl("/cls/member/login.cls");
			mv.setView(rv);
		} else {
			mv.setViewName("member/memberInfo");
			MemberVO mVO = mDao.getInfo(id);
			List<AvatarVO> list = mDao.getAvtList();// avatar list 꺼내오기
			
			mv.addObject("DATA", mVO);
			mv.addObject("LIST", list);
		}
		
		return mv;
	}	
	
	@RequestMapping("/memberInfo2.cls")
	@ResponseBody
	public MemberVO getInfo(MemberVO mVO) {
		System.out.println("##### vo id : " + mVO.getId());
		mVO = mDao.getInfo(mVO.getMno());
		return mVO;
	}
//	오버로딩 한건데 mno가 있으때만 호출하는 함수이다 즉, nameList의 form 태그를 가져올때 호출되는 함수라 할 수 있다. 
	@RequestMapping(value="/memberInfo.cls", params="mno", method=RequestMethod.POST)
	public ModelAndView getInfo(ModelAndView mv , int mno) {
		
		MemberVO mVO = mDao.getInfo(mno);
		
		mv.addObject("DATA", mVO);
		mv.setViewName("member/memberInfo");		
		
		return mv;
	}
	
	@RequestMapping("/memberEditProc.cls")
	public ModelAndView memberEditProc(ModelAndView mv, MemberVO mVO) {
		String msg = "수정에 실패하였습니다.";
		/*이따가합세 ㅋㅋ
			mv.setViewName("redirect:/member/memberInfo.cls?id=" + mVO.getId() + "pw=" + mVO.getPw());
		 	jsp 에서 파라미터 꺼내서 사용하는 방법
		 		$param.msg}
		 	
		 	이 경우 전달되는 데이터는 주소표시줄에 노출이 되고
		 	데이터를 꺼내는 구문도 길어진다.
		 	따라서 여기서는 리다이렉트 jsp페이지를 만들고
		 	해당 페이지가 열리면 바로 post 방식으로 리다이렉트가 이루어지도록 처리해보자
		 */
		int cnt = mDao.editMember(mVO);
		if(cnt != 0) {
			// 수정에 성공한 경우
			msg = "정보 수정에 성공했습니다!";
		}
		
		mv.setViewName("member/redirect");
		mv.addObject("ID", mVO.getId());
		mv.addObject("MSG" , msg);
		
		return mv;
	}
	
	@RequestMapping("/logout.cls")
	public ModelAndView logOut(ModelAndView mv, HttpSession session) {
		// 할일
		// 세션에 기록 내용(속성 : SID)을 지운다.
		session.removeAttribute("SID");
		
		// 뷰를 지정한다.
		// forwarding 이지만 redirect할 때 이렇게 써도 된다는 것을 보여주기 위함
		mv.setViewName("redirect:/main.cls");
		
		return mv;
	}
	
	@RequestMapping(value="/memberDel.cls", method=RequestMethod.POST)
	public ModelAndView memberDel(ModelAndView mv, HttpSession session,MemberVO mVO) {
		int cnt = mDao.outMember(mVO);
		System.out.println("#####cnt : " + cnt);
		if(cnt == 1) {
			// 이 경우는 회원 탈퇴 성공한 경우이므로 
			// 세션에 기록된 데이터 지우고 
			// 메인페이지로 돌려보낸다.
			session.removeAttribute("SID");
			mv.setViewName("redirect:/main.cls");
		} else {
			// 처리에 실패한 경우이므로 회원상세정보페이지로 다시 보낸다.
			mv.setViewName("redirect:/member/memberInfo.cls");
		}
		return mv;
	}
	
	@RequestMapping("/nameList.cls")
	public ModelAndView getList(ModelAndView mv) {
		
		List<MemberVO> list = mDao.getNameList();
		
		// 데이터를 뷰에 전달하는 방법
	
		mv.addObject("LIST", list);
		// COLOR 라는 애칭으로 w3color.getList()의 내용을 담는다.
		mv.addObject("COLOR", w3color.getList()); 
		mv.setViewName("member/memberList");
		
		return mv;
	}
}