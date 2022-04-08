package com.app.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
	private int no;
	private String subject;
	private String content;
	private String writer;
	private Date regist_date;
}
