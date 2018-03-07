package com.fuckyoujava.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MemberVO {
	 
    private int id;
    private String title;
    private String date;
    private String content;
    private int recnt;	//게시글 댓글의 수 추가
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		
		//게시글 작성 시간이 하루 이내라면 시간을 표시합니다.
		SimpleDateFormat dfhm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		String resdate = date;    //기준일
		String today = dfhm.format(cal.getTime());

		if(resdate!=null) {
			//숫자 비교를 위해 특수문자 제거
			resdate = resdate.replaceAll("-", "");
			resdate = resdate.replaceAll(":", "");
			resdate = resdate.replaceAll(" ", "");
			today = today.replaceAll("-", "");
			today = today.replaceAll(":", "");
			today = today.replaceAll(" ", "");
			
			//yyyymmdd 까지만 자르기
			String begindate = resdate.substring(0, 8);
			String enddate = today.substring(0, 8);
			
			//int형 변환
			int begin = Integer.parseInt(begindate);
			int end = Integer.parseInt(enddate);	//현재 날짜
			//차이 비교
			int timediff = end - begin;
						
			//DB에서 시간 부분만 자르기
			String printtime = date.substring(11, 19);
			System.out.println("printtime : " + printtime + "\n");
			String printdate = date.substring(0, 10);
			if(timediff<=1) {
				return printtime;
			}
			return printdate;
			
		}else {
			return date;
		}
			
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
 
	 // toString()
    @Override
    public String toString() {
        return "MemberVO [id=" + id + ", title=" + title + ", content=" + content + ", date="
                + date + ", recnt=" + recnt + " ]";
    }

	public int getRecnt() {
		return recnt;
	}

	public void setRecnt(int recnt) {
		this.recnt = recnt;
	}
 
}