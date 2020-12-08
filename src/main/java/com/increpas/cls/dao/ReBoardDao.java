package com.increpas.cls.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.increpas.cls.vo.ReBoardVO;

public class ReBoardDao {
	// 자동의존 주입  , bean처리가 되어있어야 가져올 수 있다.
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 댓글 게시판 가져오기 전담 처리 함수
	public List<ReBoardVO> getReBoardList(ReBoardVO rVO) {
		return sqlSession.selectList("rSQL.getReBoardList", rVO);
	}
	
	// 댓글 게시물 수 가져오기 전담 처리 함수
	public int getReBoardTotal() {
		return sqlSession.selectOne("rSQL.getReBoardTotal");
	}
	
	// 글쓰기 전담 처리 함수
	public int addBoard(ReBoardVO rVO) {
		return sqlSession.insert("rSQL.addReBoard", rVO);
	}
	
	// 아바타 가져오기 전담 처리함수
	public String getAvatar(ReBoardVO rVO) {
		return sqlSession.selectOne("rSQL.getAvatar", rVO);
	}
	
	// 댓글 게시판 삭제 전담 처리 함수
	public int deleteReBoard(ReBoardVO rVO) {
		return sqlSession.update("rSQL.deleteReBoard", rVO);
	}
	
	// 댓글 게시판 편집 전담 처리 함수
	public int editReBoard(ReBoardVO rVO) {
		return sqlSession.update("rSQL.editReBoard", rVO);
	}
	
	// 댓글 추가 전담 처리 함수
	public int addReBoard(ReBoardVO rVO) {
		return sqlSession.insert("rSQL.addReBoard", rVO);
	}
}
