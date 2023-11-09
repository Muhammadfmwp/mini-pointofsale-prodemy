package com.prodemy.springboot.service;

import java.security.Principal;
import java.util.List;

import com.prodemy.springboot.model.Payment;
import com.prodemy.springboot.model.Product;
import com.prodemy.springboot.model.Shipping;
import com.prodemy.springboot.model.User;
import com.prodemy.springboot.model.UserOrder;
import com.prodemy.springboot.web.dto.UserOrderDto;

public interface UserOrderService {
	UserOrder addtoCart(Long id, Principal principal);
	UserOrder checkout(Long id,UserOrderDto userOrderDto);
	UserOrder getDetails(Long id);
	UserOrder getCartByUser(Principal principal);
	List<UserOrder> getHistoryByUser(Principal principal);
	void cancel(Long id);
}
