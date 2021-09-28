package com.it.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.Entity.InvoicedetailEntity;

public interface InvoicedetailRepository extends JpaRepository<InvoicedetailEntity, Integer>{
	
	@Query(value = "SELECT * FROM tb_invoicedetail WHERE in_id =?1 " , nativeQuery = true)
	public Optional<InvoicedetailEntity> findByInId(Integer inId);

}
