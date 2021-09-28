package com.it.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.Entity.InvoiceEntity;
import com.it.Entity.RentEntity;


public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Integer>{

	@Query(value = "SELECT * FROM tb_invoice WHERE user_id =?1 " , nativeQuery = true)
	public List<InvoiceEntity> findByUserId(String userId);
}
