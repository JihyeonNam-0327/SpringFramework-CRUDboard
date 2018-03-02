package com.fuckyoujava.dto;

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
		return date;
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