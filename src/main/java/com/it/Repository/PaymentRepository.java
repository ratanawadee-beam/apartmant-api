package com.it.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.it.Entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer>{

	@Query(value = "SELECT * FROM tb_payment WHERE in_id =?1" , nativeQuery = true)
	public List<PaymentEntity> findByInId(Integer inId);
}
