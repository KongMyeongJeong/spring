package com.app.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.BoardDto;
import com.app.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;

// @Service : Service 레이어 클래스들에 사용되는 어노테이션
// @AllArgsConstructor : 생성자를 자동으로 생성해주는 애노테이션
// @RequiredArgsConstructor : final 필드에 대해 생성자를 만들어주는 lombok의 애노테이션
@Service
@RequiredArgsConstructor
public class BoardService {
	private final Logger logger = LoggerFactory.getLogger(BoardService.class);
	private final BoardMapper boardMapper;
		
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
	Date date = new Date();
	String localTime = format.format(date);
	
	public List<BoardDto> boardList() throws Exception {
		List<BoardDto> data = new ArrayList<BoardDto>();

		data = boardMapper.list();
		
		logger.info("---- 로그 실행 ----");

		if (data.size() == 0) {
			return null;
		} else {
			return data;
		}
				
	}
	
	public List<BoardDto> boardDetail(int no) throws Exception {
		List<BoardDto> data = new ArrayList<BoardDto>();

		data = boardMapper.detail(no);

		if (data.size() == 0) {
			return null;
		} else {
			return data;
		}

	}
	
	@Transactional(rollbackFor = Exception.class)
	public boolean boardWrite(BoardDto boardVo) throws Exception {
		try {
			boardVo.setSubject(boardVo.getSubject());
			boardVo.setContent(boardVo.getContent());
			boardVo.setWriter(boardVo.getWriter());
			boardVo.setRegist_date(localTime);
			boardMapper.boardWrite(boardVo);
		} catch(Exception e) {
			e.printStackTrace();
			logger.info("--- boardWrite 에러 발생 ---");
			
			return false;
		}
			
		return true;
		
	}
	
}
