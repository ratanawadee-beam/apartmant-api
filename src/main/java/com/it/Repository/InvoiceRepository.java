package com.it.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.Entity.InvoiceEntity;


public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Integer>{

}
