package com.increpas.cls.dao.temp;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.increpas.cls.vo.temp.NoticeVO;

public class NoticeDao {
	// 자동의존 주입  , bean처리가 되어있어야 가져올 수 있다.
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 게시물 총 갯수 가져오기 전담 처리함수
	public List<NoticeVO> getList() {
		return sqlSession.selectList("nSQL.getList");
	}
	
	public int updateContents(NoticeVO nVO) {
		return sqlSession.update("nSQL.updateContents", nVO);
	}
	
	public NoticeVO getDetail(NoticeVO nVO) {
		return sqlSession.selectOne("nSQL.getDetail", nVO);
	}
	
	// 공지사항 삭제 전담 처리 함수
	public int updateDel(NoticeVO nVO) {
		return sqlSession.update("nSQL.updateDel", nVO);
	}
	
	// 조회수 처리 함수
	public int updateHit(NoticeVO nVO) {
		return sqlSession.update("nSQL.updateHit", nVO);
	}
}
