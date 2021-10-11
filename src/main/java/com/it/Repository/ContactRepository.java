package com.it.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.Entity.ContactEntity;
import com.it.Entity.InvoiceEntity;
import com.it.Entity.InvoicedetailEntity;
import com.it.Entity.PaymentEntity;
import com.it.Entity.UserEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer>{
	
	
}
