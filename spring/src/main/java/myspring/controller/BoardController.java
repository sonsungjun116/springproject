package myspring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import myspring.model.Board;
import myspring.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	//글작성 폼
	@RequestMapping("boardform.do")
	public String boardform() {
		
		return "board/boardform";
	}
	
	// 글작성
	@RequestMapping("boardwrite.do")
	public String boardwrite(Board board, Model model) {
		
		int result = service.insert(board);
		System.out.println("result:" + result);
		
		model.addAttribute("result", result);
		
		return "board/insertresult";
	}
	
	// 글 목록
	@RequestMapping("boardlist.do")
	public String boardlist(HttpServletRequest request, Model model) {
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;
		
		int listcount = service.getCount();
		System.out.println("listcount:" + listcount);
		
		List<Board> boardlist = service.getBoardList(page); //dao까진 두개가 전달이 가능하지만 mybatis에서 불가능하다
		System.out.println("boardlist:" + boardlist);
		
		// 총페이지수
		int pageCount = listcount / limit+ ((listcount%limit==0) ? 0:1); 
		
		int startPage = ((page - 1) / 10) * limit + 1;	// 1, 11, 21...
		int endPage = startPage + 10 - 1;				// 10,20, 30
		
		if(endPage > pageCount)
			endPage = pageCount;
		
		model.addAttribute("page", page);
		model.addAttribute("listcount", listcount);
		model.addAttribute("boardlist", boardlist);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "board/boardlist";
	}
	
	//상세 페이지 : 조회수1증가 + 상세 정보 구하기
	@RequestMapping("boardcontent.do")
	public String boardcontent(int no, int page, Model model) {
		
		service.updatecount(no);			// 조회수 1증가
		Board board = service.getBoard(no);	// 상세 정보 구하기
		String content = board.getContent().replace("\n", "<br>");
		
		model.addAttribute("board", board);
		model.addAttribute("content", content);
		model.addAttribute("page", page);
		
		
		return "board/boardcontent";
	}
	
	// 수정 폼
	@RequestMapping("boardupdateform.do")
	public String boardupdateform(int no, int page, Model model) {
		Board board = service.getBoard(no);		// 상세 정보 구하기
				
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		
		return "board/boardupdateform";
	}
	
	// 글 수정
	@RequestMapping("boardupdate.do")
	public String boardupdate(Board board, int page, Model model) {
		int result = 0;
		Board old = service.getBoard(board.getNo());
		if(old.getPasswd().equals(board.getPasswd())) {	// 비번 일치시
			result = service.update(board);
		}else {											// 비번 불일치시
			result= -1;
		}
		
		model.addAttribute("result", result);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		
		return "board/updateresult";
	}
	
	// 삭제 폼
	@RequestMapping("boarddeleteform.do")
	public String boarddeleteform() {
		
		return "board/boarddeleteform";
	}
	
	// 글 삭제
	@RequestMapping("boarddelete.do")
	public String boarddelete(Board board, int page, Model model) {
		int result = 0;
		Board old = service.getBoard(board.getNo());
		if(old.getPasswd().equals(board.getPasswd())) {	// 비번 일치시
			result = service.delete(board.getNo());
		}else {											// 비번 불일치시
			result = -1;
		}
		model.addAttribute("result", result);	
		model.addAttribute("page", page);	
		
		return "board/deleteresult";
	}
	
	
}
