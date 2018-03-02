package com.fuckyoujava.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.fuckyoujava.dto.MemberVO;
import com.fuckyoujava.dto.PageVO;
import com.fuckyoujava.service.MemberService;
import com.hibernate.app.MemberProvider;
import com.hibernate.app.MemberProviderImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private static MemberProvider provider;
	
	@Inject
	private MemberService service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	private int nowPage;
	
	//01.게시글 목록
	@RequestMapping("gongji_list.do")
	public String list(@RequestParam(value="curPage", defaultValue="1", required=false) int curPage, 
			Model model) throws Exception{
		
		int totalContentCnt = service.getContentCnt();	//총 게시글 개수
		
		 //로직에 추가한 것 2: 만약 get 방식의 값에 curPage가 음수가 들어왔을 경우 처리
        if(curPage <= 0) {
        	curPage = 1;
        }
        
		PageVO pageVO = new PageVO(totalContentCnt, curPage); //총 개시글 개수와 현재 페이지를 PageVO에 넘겨줍니다.
		nowPage = curPage;
		int start = pageVO.getPageBegin(); //PageVO에서 현재 페이지의 시작글번호를 받아옵니다. 
		
		//게시글이 하나도 없을 경우~ 에러 방지
		if(start < 0) {
			start = 0;
		}
		
		//서비스클래스의 listAll 메소드 결과는 List 형식이다.
		List<MemberVO> list = service.listAll(start);	//시작글번호를 Mapper.xml에 넘겨주어서 Limit 시작글번호, 10 를 만듭니다.
		
		model.addAttribute("list", list);
		model.addAttribute("page", pageVO);
		
		return "board/gongji_list";
	}
	
	//02_01. 게시글 작성화면(수업때는 insert 화면이었음)
	@RequestMapping(value="gongji_write.do", method=RequestMethod.GET)
	public String write() {
		return "board/gongji_write"; //gongji_write.jsp로 이동
	}
	
	//02_02. 게시글 작성처리(수업때는 이게 write.jsp 였음)
	@RequestMapping(value="gongji_insert.do", method=RequestMethod.POST)
	public String insert(@ModelAttribute MemberVO vo) throws Exception{
		//service.create(vo); -- myBatis 버전
		
		provider = new MemberProviderImpl();
		//Hibernate 버전
		provider.insertUser(vo);
		
		
		return "redirect:gongji_list.do";
	}
	
	//03. 게시글 상세내용 조회
	//@RequestParam : get/post 방식으로 전달된 변수 1개
	//HttpSession 세션객체
	@RequestMapping(value="gongji_view.do", method=RequestMethod.GET)
	public ModelAndView view(@RequestParam int id, HttpSession session) throws Exception{
		//모델(데이터)+뷰(화면)를 함께 전달하는 객체
		ModelAndView mav = new ModelAndView();
		//뷰의 이름
		mav.setViewName("board/gongji_view");
		//뷰에 전달할 데이터
		mav.addObject("dto", service.read(id));
		mav.addObject("curPage", nowPage);
		return mav;
	}
	
    // 04. 게시글 수정
    // 폼에서 입력한 내용들은 @ModelAttribute MemberVO vo로 전달됨
    @RequestMapping(value="gongji_update.do", method=RequestMethod.POST)
    public String update(@ModelAttribute MemberVO vo) throws Exception{
    	service.update(vo);
        return "redirect:gongji_list.do?curPage="+nowPage;
    }
    
    // 05. 게시글 삭제
    @RequestMapping("gongji_delete.do")
    public String delete(@RequestParam int id) throws Exception{
    	service.delete(id);
        return "redirect:gongji_list.do?curPage="+nowPage;
    }
	
}
