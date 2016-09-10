package com.nurtureretargeting.management.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nurtureretargeting.management.service.JUserService;


@Service
@PropertySource("classpath:com/nurtureretargeting/properties/oauth.properties")
public class UserDetailsServiceImpl implements UserDetailsService {

	private ClientHttpRequestFactory clientHttpRequestFactory;
	private JUserService userService;
	
	@Value("${oauth.token}") private String tokenUrl;
	@Value("${oauth.clientID}") private String clientID; 
	@Value("${oauth.clientSecret}") private String clientSecret;
	@Value("${oauth.grantType}") private String grantType;
	
	public UserDetailsServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	public UserDetailsServiceImpl(JUserService userService) {
		// TODO Auto-generated constructor stub
		this.userService = userService;
	}
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
//		UserModel userModel = null;
		try {
//			userModel = (UserModel) IBaseDao.findOne("email", username, UserModel.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if(userModel == null) {
//			throw new UsernameNotFoundException("No user found with username: " + username);
//		}
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("role");
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(authority);
  
		User userDetails = new User("email", "password", true, true, true, true, authorities);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
		return userDetails;
	}
	

	
}
