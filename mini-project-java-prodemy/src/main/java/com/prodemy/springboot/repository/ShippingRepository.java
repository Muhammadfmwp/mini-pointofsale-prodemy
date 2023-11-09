package com.prodemy.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodemy.springboot.model.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {

}
