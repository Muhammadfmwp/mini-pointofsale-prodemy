package com.prodemy.springboot.web.dto;

import java.util.Collection;

import com.prodemy.springboot.model.Payment;
import com.prodemy.springboot.model.Product;
import com.prodemy.springboot.model.Shipping;

public class UserOrderDto {
	private String status;
	private Collection<Product> products;
	private Payment payment;
	private Shipping shipping;
	private double total;
	
	public UserOrderDto() {
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	

}
