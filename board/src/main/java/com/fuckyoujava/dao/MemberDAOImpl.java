package com.fuckyoujava.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.fuckyoujava.dto.MemberVO;

/**
 * MemberDAOImpl 은 SqlSession을 받아서
 * memberMapper.xml 에 등록한 쿼리문을 실행한다.
 * 쿼리 실행 결과 여러개의 데이터를 가져오게 되므로 List로 받아서 리턴한다.
 * MemberDAOImpl 작성 후 Bean 등록을 해야 한다. 
 * 빈 등록을 하기 위해 root-context.xml을 연다.
 * */

@Repository // 현재 클래스를 dao bean으로 등록
public class MemberDAOImpl implements MemberDAO {
 
    @Inject
    private SqlSession sqlSession;
    
    // 01. 게시글 작성
    @Override
    public void create(MemberVO vo) throws Exception {
    	sqlSession.insert("board.insert", vo);
    }
    // 02. 게시글 상세보기
    @Override
    public MemberVO read(int id) throws Exception {
        return sqlSession.selectOne("board.view", id);
    }
    // 03. 게시글 수정
    @Override
    public void update(MemberVO vo) throws Exception {
    	sqlSession.update("board.updateArticle", vo);
 
    }
    // 04. 게시글 삭제
    @Override
    public void delete(int id) throws Exception {
    	sqlSession.delete("board.deleteArticle",id);
 
    }
    // 05. 게시글 전체 목록
    @Override
    public List<MemberVO> listAll(int start) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("start", start);
    	
        return sqlSession.selectList("board.listAll", map);
    }
    
    // 06. 전체 게시글 조회
    @Override
    public int getContentCnt() {
        return sqlSession.selectOne("board.countAll");
    }
    
}