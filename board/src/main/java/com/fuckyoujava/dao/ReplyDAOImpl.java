package com.fuckyoujava.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.fuckyoujava.dto.ReplyVO;

@Repository // 현재 클래스를 dao bean으로 등록
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<ReplyVO> list(int id, int start) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
    	map.put("start", start);
    	
		return sqlSession.selectList("reply.listReply", map);
	}

	@Override
	public void create(ReplyVO vo) {
		
		sqlSession.insert("reply.insertReply", vo);
	}

	@Override
	public void update(ReplyVO vo) {
		sqlSession.update("reply.updateReply", vo);
	}

	@Override
	public void delete(String rno) {
		sqlSession.delete("reply.deleteReply", rno);
	}
	
	@Override
	public int count(int id) {
		return sqlSession.selectOne("reply.countReply", id);
	}

}
