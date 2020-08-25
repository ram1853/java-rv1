package com.members.registry.dao;

import java.util.Base64;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.members.registry.model.Member;
import com.members.registry.utils.MemberRegistryUtils;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Member> getAllMembers(){

		Session session = sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		Query<Member> query = session.createQuery("from Member");

		return query.getResultList();
	}

	@Override
	public void addMember(Member member) {

		Session session = sessionFactory.getCurrentSession();

		storeFileData(member);

		session.save(member);

	}

	@Override
	public Member getMember(String name, String email) {

		Session session = sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		Query<Member> query = session.createQuery("from Member where name=:inputName and email=:inputEmail");

		query.setParameter("inputName", name);
		query.setParameter("inputEmail", email);

		Member member = query.getResultList().size()==1 ? query.getResultList().get(0) : null;

		return member;

	}

	@Override
	public Member getMemberById(int memberId) {

		Session session = sessionFactory.getCurrentSession();
		return session.get(Member.class, memberId);
	}

	@Override
	public void updateMember(Member member) {

		Session session = sessionFactory.getCurrentSession();
		session.update(member);
	}

	@Override
	public void deleteMember(Member member) {

		Session session = sessionFactory.getCurrentSession();
		session.delete(member);

	}

	@Override
	public String getBase64image(Member member) {

		if(member.getContent() != null) {
			return Base64.getEncoder().encodeToString(member.getContent());
		}else {
			storeFileData(member);
			return Base64.getEncoder().encodeToString(MemberRegistryUtils.getUtils().getFileData().get(member.getId()));
		}

	}

	@Override
	public void storeFileData(Member member) {
		
		//need to check if it is add or update
		MemberRegistryUtils utils = MemberRegistryUtils.getUtils();

		if(member.getContent() != null && member.getContent().length > 0) {
			utils.setFileData(member.getId(), member.getContent());
		}else {

			Session session = sessionFactory.getCurrentSession();

			@SuppressWarnings("unchecked")
			Query<byte[]> query = session.createQuery("select content from Member where id=:inputId");

			query.setParameter("inputId", member.getId());
			List<byte[]> list = query.getResultList();
			if(list.size() == 1) {

				utils.setFileData(member.getId(), list.get(0));
			}


		}

	}

}
