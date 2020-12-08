package com.increpas.cls.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.increpas.cls.vo.AvatarVO;
import com.increpas.cls.vo.MemberVO;

public class MemberDao {
	@Autowired
	SqlSessionTemplate sqlSession;
// 함수이름의 매개변수는 SQL.xml에서 parameterType을 따른다.
	// 로그인 조회 전담 처리 함수
	public int loginCnt(MemberVO mVO) {
//		mSQL 중 아이디가 login인 질의명령 호출해주세요
		return sqlSession.selectOne("mSQL.login", mVO);
	}
	
	// 회원 정보 조회 전담 처리 함수
	public MemberVO getInfo(String id) {
		return sqlSession.selectOne("mSQL.getInfo", id);
	}
	
	// 회원 정보 조회 전담 처리 함수
	// *** dao의 매개변수와 sql #{no} 의 별칭은 같아야 한다.
	public MemberVO getInfo(int no) {
		return sqlSession.selectOne("mSQL.getInfoNo", no);
	}
	
	// 회원 탈퇴 담당 처리 함수
	public int outMember(MemberVO mVO) {
		return sqlSession.update("mSQL.del_memb", mVO);
	}
	
	// 회원 이름 리스트 조회 전담 처리함수
	public List<MemberVO> getNameList(){
		return sqlSession.selectList("mSQL.nameList");
	}
	
	// AVATAR
	// 아바타 리스트 가져오기 전담 처리함수
	public List<AvatarVO> getAvtList(){
		return sqlSession.selectList("aSQL.getAll");
	}
	
	// 회원 아이디체크 전담 처리함수
	public int getIdCnt(String id) {
		return sqlSession.selectOne("mSQL.idCount", id);
	}
	
	// 회원가입 처리 전담 처리 함수
	public int insertMember(MemberVO mVO) {
		return sqlSession.insert("mSQL.addMember", mVO);
	}
	
	// 여러 회원가입 처리 전담 처리 함수
	@Transactional
	public int insertMember(ArrayList<MemberVO> list) {
		int cnt = 0;
		for(MemberVO mVO : list) {
			cnt += insertMember(mVO);
		}
		return cnt;
	}
	// 회원 정보 수정 전담 처리함수
	public int editMember(MemberVO mVO) {
		return sqlSession.update("mSQL.editMember",mVO);
	}
}
