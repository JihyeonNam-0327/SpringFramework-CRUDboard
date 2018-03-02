package com.hibernate.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuckyoujava.dto.MemberVO;

public class MemberProviderImpl implements MemberProvider{

	@Override
	public void insertUser(MemberVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		//session.persist(user);	//객체 저장
		
		session.save(vo);
		
		tx.commit();
		session.close();
	}

	@Override
	public List<MemberVO> findAllUser() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		List users = session.createQuery("from User u order by u.name asc").list(); //객체 조회
		
		tx.commit();
		session.close();
		
		return users;
	}

	@Override
	public MemberVO findUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO updateUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void read() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String userId = "id";
		MemberVO user = (MemberVO) session.get(MemberVO.class, userId);
		
		System.out.println("Id : " + user.getId());
		System.out.println("title : " + user.getTitle());
		System.out.println("content : " + user.getContent());
		
		session.close();
	}

}
