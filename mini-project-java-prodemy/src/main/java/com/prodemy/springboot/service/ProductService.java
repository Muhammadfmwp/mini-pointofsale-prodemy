package com.prodemy.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.prodemy.springboot.model.Product;
import com.prodemy.springboot.web.dto.ProductDto;

public interface ProductService {
	Product save(ProductDto productDto, MultipartFile mainImg, MultipartFile extraImg1, MultipartFile extraImg2, MultipartFile extraImg3);
	Page<Product> paginatedPage(int pageNo, int pageSize, String sortField, String productName, Double min, Double max, String sortDirection);
	Product updateProduct(Long id,ProductDto productDto,  MultipartFile mainImg, MultipartFile extraImg1, MultipartFile extraImg2, MultipartFile extraImg3);
	Product getByProduct(Long id);
	void deleteProduct(Long id);
	
}
