package com.fuckyoujava.dao;

import java.util.List;

import com.fuckyoujava.dto.MemberVO;
 
public interface MemberDAO {
    
    // 01. 게시글 작성
    public void create(MemberVO vo) throws Exception;
    // 02. 게시글 상세보기
    public MemberVO read(int id) throws Exception;
    // 03. 게시글 수정
    public void update(MemberVO vo) throws Exception;
    // 04. 게시글 삭제
    public void delete(int id) throws Exception;
    // 05. 게시글 전체 목록
    public List<MemberVO> listAll(int start) throws Exception;
    // 06. 전체 게시글 수 조회
    public int getContentCnt() throws Exception;

}