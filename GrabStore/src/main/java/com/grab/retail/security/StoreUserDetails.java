package com.grab.retail.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class StoreUserDetails implements UserDetails {
	
	private String userName;
	private String password;
	private boolean enabled;
	private List<GrantedAuthority> authorities;
	
	public StoreUserDetails(String userName, String password, String authorities, int enabled) {
		this.userName = userName;
		this.password = password;
		this.authorities = Arrays.stream(authorities.split(","))
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		if(enabled == 1) {
			this.enabled = true;
		}else {
			this.enabled = false;
		}
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return enabled;
	}

}
