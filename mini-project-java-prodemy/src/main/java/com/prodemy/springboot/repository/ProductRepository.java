package com.prodemy.springboot.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prodemy.springboot.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "Select * from product where price >= ?1 AND price <= ?2", nativeQuery = true)
	public Page<Product> findProducts(String productName, @Param("min") Double min, @Param("max") Double max, Pageable pageable);
	
	@Query(value = "Select * from product where price >= ?1 AND price <= ?2", nativeQuery = true)
	public Page<Product> findByProductsPrice(@Param("min") Double min, @Param("max") Double max, Pageable pageable);
	
	public Page<Product> findByProductName(String productName, Pageable pageable);
}
