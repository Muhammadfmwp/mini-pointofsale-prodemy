package com.prodemy.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prodemy.springboot.model.User;
import com.prodemy.springboot.model.UserOrder;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
	@Query("SELECT o FROM UserOrder o WHERE o.status = 'Cart'")
	List<UserOrder> findCartByUser(User user);
	
	@Query("SELECT o FROM UserOrder o WHERE o.status = 'Paid'")
	List<UserOrder> findHistoryByUser(User user);
}
