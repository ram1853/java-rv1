package com.grab.retail.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> oUser = userRepository.findByUserName(username);
		
		oUser.orElseThrow(() -> new UsernameNotFoundException("Username "+username+" does not exist"));
		
		User user = oUser.get();
		
		UserDetails userDetails = new StoreUserDetails(user.getUserName(), user.getPassword(), user.getAuthority().getAuthority(), user.getEnabled());
		
		return userDetails;
	}

}
