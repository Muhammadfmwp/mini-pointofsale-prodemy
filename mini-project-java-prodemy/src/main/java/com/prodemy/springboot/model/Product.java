package com.prodemy.springboot.model;


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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Collection;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "product_name")
	private String productName;
	

	@Column(name = "main_image")
	private String mainImage;
	
	@Column(name = "extra_image1")
	private String extraImage1;
	
	@Column(name = "extra_image2")
	private String extraImage2;
	
	@Column(name = "extra_image3")
	private String extraImage3;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private Double price;
	
	@CreationTimestamp
	@Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
	
	public Product() {
		super();
	}






	public Product(String productName, String mainImage, String extraImage1, String extraImage2, String extraImage3,
			String description, Double price) {
		super();
		this.productName = productName;
		this.mainImage = mainImage;
		this.extraImage1 = extraImage1;
		this.extraImage2 = extraImage2;
		this.extraImage3 = extraImage3;
		this.description = description;
		this.price = price;
	}






	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getMainImage() {
		return mainImage;
	}






	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}






	public String getExtraImage1() {
		return extraImage1;
	}






	public void setExtraImage1(String extraImage1) {
		this.extraImage1 = extraImage1;
	}






	public String getExtraImage2() {
		return extraImage2;
	}






	public void setExtraImage2(String extraImage2) {
		this.extraImage2 = extraImage2;
	}






	public String getExtraImage3() {
		return extraImage3;
	}






	public void setExtraImage3(String extraImage3) {
		this.extraImage3 = extraImage3;
	}






	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
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


    

}
