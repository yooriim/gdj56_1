package com.web.notice.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
	public int noticeNo;
	public String noticeTitle;
	public String writer;
	public String file; //머임 ; 
	public String noticeContent;
	public Date noticeDate;
	public String filepath; //머임 ;;;;;;
	public char status;	
	
}
