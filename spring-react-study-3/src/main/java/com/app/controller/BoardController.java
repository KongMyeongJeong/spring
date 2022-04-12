package com.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BoardDto;
import com.app.dto.ResponeVo;
import com.app.service.BoardService;

import lombok.RequiredArgsConstructor;

/**
 *  @RequiredArgsConstructor : final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
 *  @ResponseBody : (비동기) 응답 본문
 *  @RequestBody : (비동기) 방식 요청 본문
 *
 */

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
	private final Logger logger = LoggerFactory.getLogger(BoardController.class);	
	private final BoardService boardService;
	
	
	/**
	 * 게시판 리스트
	 * @returnBoardDto타입의 배열
	 * @throws Exception
	 */
	@GetMapping()
	public List<BoardDto> list() throws Exception {		
		return boardService.boardList();
	}
	
	/**
	 * 게시판 상세
	 * @param no
	 * @return BoardDto타입의 배열
	 * @throws Exception
	 */
	@GetMapping("/{no}")
	public List<BoardDto> detail(@PathVariable int no) throws Exception {
		
		return boardService.boardDetail(no);
	}
	
	/**
	 * 게시판 글 작성
	 * @param boardVo
	 * @return ResponeDto (오브젝트)
	 * @throws Exception
	 */
	@ResponseBody()
	@PostMapping()
	public ResponeVo boardWrite(@RequestBody BoardDto boardVo) throws Exception{
		ResponeVo res = new ResponeVo();
		boolean insertCheck = boardService.boardWrite(boardVo);
		
		if(insertCheck) {
			res.setMsg("작성 성공");
			res.setResponse("sucess");
			
			return res;
		} else {
			logger.info("---- boardWrite ----");
			
			res.setMsg("작성 실패");
			res.setResponse("fail");
			
			return res;
		}			
	}
	
	
//	Model , ModelAndView 이용 가능
//	@GetMapping("/{no}")
//	public List<BoardDto> detail(@PathVariable int no, Model model) throws Exception {
//		System.err.println("PathVariable : " + no);
//		
//		model.addAttribute("m", boardMapper.detail(no));
//		
//		return boardMapper.detail(no);
//	}


}
