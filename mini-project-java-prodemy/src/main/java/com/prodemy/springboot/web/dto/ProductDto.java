package com.prodemy.springboot.web.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDto {

	private String productName;
	private MultipartFile mainImage;
	private MultipartFile extraImage1;
	private MultipartFile extraImage2;
	private MultipartFile extraImage3;
	private String description;
	private Double price;
	
	public ProductDto() {
		super();
	}




	public ProductDto(String productName, MultipartFile mainImage, MultipartFile extraImage1, MultipartFile extraImage2,
			MultipartFile extraImage3, String description, Double price) {
		super();
		this.productName = productName;
		this.mainImage = mainImage;
		this.extraImage1 = extraImage1;
		this.extraImage2 = extraImage2;
		this.extraImage3 = extraImage3;
		this.description = description;
		this.price = price;
	}




	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	

	public MultipartFile getMainImage() {
		return mainImage;
	}




	public void setMainImage(MultipartFile mainImage) {
		this.mainImage = mainImage;
	}




	public MultipartFile getExtraImage1() {
		return extraImage1;
	}




	public void setExtraImage1(MultipartFile extraImage1) {
		this.extraImage1 = extraImage1;
	}




	public MultipartFile getExtraImage2() {
		return extraImage2;
	}




	public void setExtraImage2(MultipartFile extraImage2) {
		this.extraImage2 = extraImage2;
	}




	public MultipartFile getExtraImage3() {
		return extraImage3;
	}




	public void setExtraImage3(MultipartFile extraImage3) {
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

	
	

}
