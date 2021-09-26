package com.it.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.Entity.RentEntity;

public interface RentRepository extends JpaRepository<RentEntity, Integer>{
	
	@Query(value = "SELECT * FROM tb_rent WHERE user_id =?1 " , nativeQuery = true)
	Optional<RentEntity> findByUserId(String userId);
}
