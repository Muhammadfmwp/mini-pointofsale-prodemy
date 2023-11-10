package com.prodemy.springboot.service.impl;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prodemy.springboot.config.CustomUserDetails;
import com.prodemy.springboot.model.Role;
import com.prodemy.springboot.model.User;
import com.prodemy.springboot.model.UserOrder;
import com.prodemy.springboot.repository.RoleRepository;
import com.prodemy.springboot.repository.UserOrderRepository;
import com.prodemy.springboot.repository.UserRepository;
import com.prodemy.springboot.service.UserOrderService;
import com.prodemy.springboot.service.UserService;
import com.prodemy.springboot.web.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder passwordEncoder;
	private UserOrderRepository userOrderRepository;


	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository ,@Lazy BCryptPasswordEncoder passwordEncoder, UserOrderRepository userOrderRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.userOrderRepository = userOrderRepository;
	}

	@Override
	public User save(UserDto userRegistrationDto) {
		User user = new User();
		user.setFullName(userRegistrationDto.getFullName());
		user.setUserName(userRegistrationDto.getUserName());
		user.setEmail(userRegistrationDto.getEmail());
		user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
		user.setRoles(Arrays.asList(roleRepository.findByName("Customer")));
		return userRepository.save(user);
	}


	@Override
	public Page<User> paginatedPage(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.userRepository.findAll(pageable);
	}


	@Override
	public User getByUser(Long id) {
		Optional<User> optional = userRepository.findById(id);
		User user = null;
		if(optional.isPresent()) {
			user = optional.get();
		}
		else {
			throw new RuntimeException("User tidak ditemukan");
			}
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public User updateUser(Long id,UserDto userRegistrationDto) {
		User user = userRepository.getById(id);
		user.setFullName(userRegistrationDto.getFullName());
		user.setUserName(userRegistrationDto.getUserName());
		user.setEmail(userRegistrationDto.getEmail());
		return userRepository.save(user);
		
	}

	@Override
	public User getProfile(Principal principal) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUserName(principal.getName());
		return user;
	}

	@Override
	public User updatePassword(Principal principal, UserDto userRegistrationDto) {
		User user = userRepository.findByUserName(principal.getName());
		user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User updateProfile(Principal principal, UserDto userRegistrationDto) {
		User user = userRepository.findByUserName(principal.getName());
		user.setFullName(userRegistrationDto.getFullName());
		user.setEmail(userRegistrationDto.getEmail());
		return userRepository.save(user);
	}








}
