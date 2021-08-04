package com.it.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
	
	public UserEntity findByUserUsername(String username);
	

}
