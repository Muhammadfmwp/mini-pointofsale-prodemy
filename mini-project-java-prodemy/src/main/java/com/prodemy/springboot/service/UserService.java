package com.prodemy.springboot.service;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;


import com.prodemy.springboot.model.User;
import com.prodemy.springboot.web.dto.UserDto;

public interface UserService {
	User save(UserDto userRegistrationDto);
	Page<User> paginatedPage(int pageNo, int pageSize, String sortField, String sortDirection);
	User updateUser(Long id,UserDto userRegistrationDto);
	User updateProfile(Principal principal ,UserDto userRegistrationDto);
	User getByUser(Long id);
	User getProfile(Principal principal);
	User updatePassword(Principal principal,UserDto userRegistrationDto);
	void deleteUser(Long id);
}
