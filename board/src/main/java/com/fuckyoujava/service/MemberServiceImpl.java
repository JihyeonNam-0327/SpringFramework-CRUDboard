package com.fuckyoujava.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
 
import com.fuckyoujava.dao.MemberDAO;
import com.fuckyoujava.dto.MemberVO;
 
@Service
public class MemberServiceImpl implements MemberService {
 
    @Inject
    private MemberDAO dao;

    // 01. 게시글쓰기
    @Override
    public void create(MemberVO vo) throws Exception {
        String title = vo.getTitle();
        String content = vo.getContent();
        // *태그문자 처리 (< ==> &lt; > ==> &gt;)
        // replace(A, B) A를 B로 변경
        title = title.replace("<", "&lt;");
        title = title.replace(">", "&gt;");
        // *공백문자 처리
        title = title.replace("  ", "&nbsp;&nbsp;");
        // *줄바꿈 문자처리
        content = content.replace("\n", "<br>");
        vo.setTitle(title);
        vo.setContent(content);
        dao.create(vo);
    }
    // 02. 게시글 상세보기
    @Override
    public MemberVO read(int bno) throws Exception {
        return dao.read(bno);
    }
    // 03. 게시글 수정
    @Override
    public void update(MemberVO vo) throws Exception {
    	dao.update(vo);
    }
    // 04. 게시글 삭제
    @Override
    public void delete(int id) throws Exception {
    	dao.delete(id);
    }
    // 05. 게시글 전체 목록
    @Override
    public List<MemberVO> listAll(int start) throws Exception {
        return dao.listAll(start);
    }
    
    // 06. 전체 게시글 조회
    @Override
    public int getContentCnt() throws Exception {
    	return dao.getContentCnt();
    }

}
