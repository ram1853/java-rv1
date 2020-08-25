package com.members.registry.service;

import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.members.registry.dao.MemberDao;
import com.members.registry.model.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao memberDao;
	
	@Override
	@Transactional
	public List<Member> getAllMembers() {
		
		return memberDao.getAllMembers();
	}
	
	@Override
	@Transactional
	public void addMember(Member member) {
		
		memberDao.addMember(member);

	}

	@Override
	@Transactional
	public Member getMember(String name, String email) {
		
		return memberDao.getMember(name, email);
	}
	
	@Override
	@Transactional
	public Member getMemberById(int memberId) {
		
		return memberDao.getMemberById(memberId);
	}

	@Override
	@Transactional
	public void updateMember(Member member) {
		
		memberDao.updateMember(member);

	}

	@Override
	@Transactional
	public void deleteMember(Member member) {
		
		memberDao.deleteMember(member);

	}
	
	@Override
	@Transient
	@Transactional
	public String getBase64image(Member member) {

		return memberDao.getBase64image(member);

	}

}
