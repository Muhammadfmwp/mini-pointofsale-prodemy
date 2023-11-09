package com.prodemy.springboot.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prodemy.springboot.model.Payment;
import com.prodemy.springboot.model.Product;
import com.prodemy.springboot.model.Shipping;
import com.prodemy.springboot.model.User;
import com.prodemy.springboot.model.UserOrder;
import com.prodemy.springboot.repository.ProductRepository;
import com.prodemy.springboot.repository.UserOrderRepository;
import com.prodemy.springboot.repository.UserRepository;
import com.prodemy.springboot.service.UserOrderService;
import com.prodemy.springboot.web.dto.UserOrderDto;

@Service
public class UserOrderServiceImpl implements UserOrderService {

	private UserOrderRepository userOrderRepository;
	private UserRepository userRepository;
	private ProductRepository productRepository;
	
	public UserOrderServiceImpl(UserOrderRepository userOrderRepository, UserRepository userRepository, ProductRepository productRepository) {
		super();
		this.userOrderRepository = userOrderRepository;
		this.userRepository =  userRepository;
		this.productRepository = productRepository;
	}

	@Override
	public UserOrder addtoCart(Long id, Principal principal) {
		User user = userRepository.findByUserName(principal.getName());
		if(userOrderRepository.findCartByUser(user).isEmpty()) {
			UserOrder order = new UserOrder();
			Product product = productRepository.getById(id);
			order.setUser(user);
			order.setStatus("Cart");
			double before = order.getTotal();
			order.setProducts(Arrays.asList(product));
			order.setTotal(before + product.getPrice());
			return userOrderRepository.save(order);
		}
		else {
			List<UserOrder> order =  userOrderRepository.findCartByUser(user);
			Optional<UserOrder> optional = userOrderRepository.findById(order.get(0).getId());
			UserOrder cart = null;
			if(optional.isPresent()) {
				cart = optional.get();
				Product product = productRepository.getById(id);
				cart.getProducts().add(product);
				double before = cart.getTotal();
				cart.setTotal(before + product.getPrice());
				return userOrderRepository.save(cart);
			}
			else {
				throw new RuntimeException("Order tidak ditemukan");
				}
			
		}	
	
	}



	@Override
	public UserOrder getDetails(Long id) {
		Optional<UserOrder> optional = userOrderRepository.findById(id);
		UserOrder order = null;
		if(optional.isPresent()) {
			order = optional.get();
		}
		else {
			throw new RuntimeException("Order tidak ditemukan");
			}
		return order;
	}

	@Override
	public void cancel(Long id) {
		userOrderRepository.deleteById(id);
	}




	@Override
	public List<UserOrder> getHistoryByUser(Principal principal) {
		// TODO Auto-generated method stub
		User user =  userRepository.findByUserName(principal.getName());
		return userOrderRepository.findHistoryByUser(user);
	}

	@Override
	public UserOrder checkout(Long id, UserOrderDto userOrderDto) {
		UserOrder order = userOrderRepository.getById(id);
		order.setPayment(userOrderDto.getPayment());
		order.setShipping(userOrderDto.getShipping());
		Collection<Product> products = order.getProducts();
		double t = 0;
		for (Product p : products) {
			t += p.getPrice();
		}
		t += userOrderDto.getShipping().getFee();
		order.setTotal(t);
		order.setStatus("Paid");
		
		
		return userOrderRepository.save(order);
	}

	@Override
	public UserOrder getCartByUser(Principal principal) {
		User user =  userRepository.findByUserName(principal.getName());
		return userOrderRepository.findCartByUser(user).get(0);
	}






}
