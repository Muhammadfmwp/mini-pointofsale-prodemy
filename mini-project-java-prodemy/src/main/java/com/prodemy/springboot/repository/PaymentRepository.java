package com.prodemy.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodemy.springboot.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
