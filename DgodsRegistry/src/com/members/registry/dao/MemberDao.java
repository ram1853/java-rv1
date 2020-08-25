package com.members.registry.dao;

import java.util.List;

import com.members.registry.model.Member;

public interface MemberDao {
	
	List<Member> getAllMembers();
	
	void addMember(Member member);
	
	Member getMember(String name, String email);
	
	Member getMemberById(int memberId);
	
	void updateMember(Member member);
	
	void deleteMember(Member member);
	
	String getBase64image(Member member);
	
	void storeFileData(Member member);
	
}
