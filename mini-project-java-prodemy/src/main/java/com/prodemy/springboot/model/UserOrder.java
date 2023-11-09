package com.prodemy.springboot.model;

import java.time.Instant;
import java.util.Collection;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Table(name = "user_order")
@Entity
public class UserOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	  
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "order_products",
			joinColumns = @JoinColumn(
					name = "order_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "product_id ", referencedColumnName = "id")
			)
	private Collection<Product> products;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "shipping_id", referencedColumnName = "id")
	private Shipping shipping;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "payment_id", referencedColumnName = "id")
	private Payment payment;
	
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "total")
	private double total;
	
	@CreationTimestamp
	@Column(name = "created_at")
	private Instant createdAt;
	
	@CreationTimestamp
	@Column(name = "updated_at")
	private Instant updatedAt;

	
	public UserOrder() {
		super();
	}


	public UserOrder(User user, Collection<Product> products, Shipping shipping, Payment payment, String status) {
		super();
		this.user = user;
		this.products = products;
		this.shipping = shipping;
		this.payment = payment;
		this.status = status;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Collection<Product> getProducts() {
		return products;
	}


	public void setProducts(Collection<Product> products) {
		this.products = products;
	}


	public Shipping getShipping() {
		return shipping;
	}


	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}


	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Instant getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}


	public Instant getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}
	
	

}
