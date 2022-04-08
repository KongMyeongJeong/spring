package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BoardDto;
import com.app.mapper.BoardMapper;

@RestController
@RequestMapping("/api/board")
public class BoardController {
	@Autowired
	private BoardMapper boardMapper;
	
	@GetMapping
	public List<BoardDto> list() throws Exception {		
		return boardMapper.list();
	}
	
	@GetMapping("/{no}")
	public List<BoardDto> detail(@PathVariable int no) throws Exception {
		System.err.println("PathVariable : " + no);
		return boardMapper.detail(no);
	}

}
