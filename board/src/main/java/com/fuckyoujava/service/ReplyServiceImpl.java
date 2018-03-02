package com.fuckyoujava.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fuckyoujava.dao.ReplyDAO;
import com.fuckyoujava.dto.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	ReplyDAO replyDao;
	
	//댓글 목록
	@Override
	public List<ReplyVO> list(int id, int start){
		return replyDao.list(id, start);
	}
	
	//댓글 작성
	@Override
	public void create(ReplyVO vo) {
		replyDao.create(vo);
	}
	
	//댓글 수정
	@Override
	public void update(ReplyVO vo) {
		replyDao.update(vo);
	}
	
	//댓글 삭제
	@Override
	public void delete(String rno) {
		replyDao.delete(rno);
	}
	
	//댓글 갯수 카운트
	@Override
	public int count(int id) {
		return replyDao.count(id);
	}
	
	
}
