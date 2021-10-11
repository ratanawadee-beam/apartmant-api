package com.it.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.Entity.DistrictEntity;

public interface DistrictRepository extends JpaRepository<DistrictEntity, Integer>{
	
	List<DistrictEntity> findByZipCode(String zipCode);
	
}
