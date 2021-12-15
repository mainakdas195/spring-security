package com.example.springsecurity.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springsecurity.domain.UserData;
import com.example.springsecurity.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		UserData user = userRepository.findByName(name);
		if (user == null) {
			throw new UsernameNotFoundException("User " + name + " not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
				getGrantedAuthority(user));
	}

	private Collection<GrantedAuthority> getGrantedAuthority(UserData user) {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if (user.getRole().equalsIgnoreCase("admin")) {
			
			if ("US".equalsIgnoreCase(user.getGeo())) {
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN_US"));
			} else {
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN_UK"));
			}
		} else {
			if ("US".equalsIgnoreCase(user.getGeo())) {
				authorities.add(new SimpleGrantedAuthority("ROLE_USER_US"));
			} else {
				authorities.add(new SimpleGrantedAuthority("ROLE_USER_UK"));
			}
		}
		
		return authorities;
	}

}
