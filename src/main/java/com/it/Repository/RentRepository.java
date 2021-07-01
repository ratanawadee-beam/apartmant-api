package com.it.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.Entity.RentEntity;

public interface RentRepository extends JpaRepository<RentEntity, Integer>{

}
