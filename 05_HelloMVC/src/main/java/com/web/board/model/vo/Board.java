package com.web.board.model.vo;

import java.sql.Date;
import java.util.List;

import com.web.member.vo.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Board {

	private int boardNo;
	private String title;
	private String writer;
//	private Member writer;
	private String content;
	private String oriFilename;
	private String reFilename;
	private Date enrollDate;
	private int readCount;
//	private List<BoardComment> comments;
}
