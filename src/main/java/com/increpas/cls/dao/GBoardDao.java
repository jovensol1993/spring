package com.increpas.cls.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.increpas.cls.service.GBoardService;
import com.increpas.cls.vo.GBoardVO;

public class GBoardDao {
	// 자동의존 주입  , bean처리가 되어있어야 가져올 수 있다.
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Autowired
	GBoardService gService;
	
	// 게시물 총 갯수 가져오기 전담 처리함수
	public int getTotal() {
		return sqlSession.selectOne("gSQL.getTotal()");
	}
	
	// 게시물 리스트 가져오기 전담 처리 함수
	public List<GBoardVO> getList(GBoardVO gVO){
		return sqlSession.selectList("gSQL.getList", gVO);
	}
	
	public String getAvatar(String id) {
		return sqlSession.selectOne("gSQL.getAvatar", id);
	}
}
