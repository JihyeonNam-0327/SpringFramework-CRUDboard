package com.fuckyoujava.service;

import java.util.List;

import com.fuckyoujava.dto.MemberVO;
/**
 * Service는 비즈니스 로직이 들어가는 부분인데 
 * 예제는 단순히 데이터를 조회하는 간단한 코드이므로 Service가 별다른 의미가 없다.
 * 그래도 전체 구조를 알기위해 작성을 해보는것이 좋다.
 * @author jihyeon
 *
 */
public interface MemberService {
   
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