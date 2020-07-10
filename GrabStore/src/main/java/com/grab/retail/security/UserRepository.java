package com.grab.retail.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
	
	public Optional<User> findByUserName(String userName);
}
