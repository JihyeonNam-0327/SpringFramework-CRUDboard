package com.fuckyoujava.dao;

import java.util.List;

import com.fuckyoujava.dto.ReplyVO;

public interface ReplyDAO {
	//댓글 목록
	public List<ReplyVO> list(int id, int start);
	
	//댓글 입력
	public void create(ReplyVO vo);
	
	//댓글 수정
	public void update(ReplyVO vo);
	
	//댓글 삭제
	public void delete(String rno);
	
	//댓글 개수
	public int count(int id);
}
