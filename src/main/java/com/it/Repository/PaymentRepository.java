package com.it.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.Entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer>{

}
