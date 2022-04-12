package com.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.app.dto.BoardDto;

//@Mapper : Marker interface for MyBatis mappers
@Mapper
public interface BoardMapper {
	// 게시판 목록
	List<BoardDto> list() throws Exception;
	
	// 게시판 상세
	List<BoardDto> detail(int no) throws Exception;
	
	// 게시글 작성
	void boardWrite(BoardDto boardVo) throws Exception;

}
