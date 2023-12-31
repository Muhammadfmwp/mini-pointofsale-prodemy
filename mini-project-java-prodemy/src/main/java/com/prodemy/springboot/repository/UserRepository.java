package com.prodemy.springboot.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prodemy.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByUserName(String userName);
}
