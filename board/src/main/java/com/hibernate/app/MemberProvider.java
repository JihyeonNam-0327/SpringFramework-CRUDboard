package com.hibernate.app;

import java.util.List;

import com.fuckyoujava.dto.MemberVO;

public interface MemberProvider {

	public void insertUser(MemberVO vo);
	
	public List<MemberVO> findAllUser();
	
	public MemberVO findUser(int id);
	
	public MemberVO updateUser(int id);

	public void read();
}
