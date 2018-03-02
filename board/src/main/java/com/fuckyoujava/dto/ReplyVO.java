package com.fuckyoujava.dto;

public class ReplyVO {
	private int rno; //댓글 번호
	private int id;  //게시글 번호
	private String replytext; //댓글 내용
	private String replyer;	  //댓글 작성자(현재는 'test'라고 함)
	private String date;	  //댓글 작성일자
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getId() {
	        return id;
	    }
    public void setId(int id) {
        this.id = id;
    }
    public String getReplytext() {
        return replytext;
    }
    public void setReplytext(String replytext) {
        this.replytext = replytext;
    }
    public String getReplyer() {
        return replyer;
    }
    public void setReplyer(String replyer) {
        this.replyer = replyer;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    
    // toString()
    @Override
    public String toString() {
        return "ReplyVO [rno=" + rno + ", id=" + id + ", replytext=" + replytext + ", replyer=" + replyer
                + ", date=" + date + "]";
    }
	    
}
