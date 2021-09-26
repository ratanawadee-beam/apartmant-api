package com.it.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.Entity.ContactEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer>{

}
