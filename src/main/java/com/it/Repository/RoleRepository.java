package com.it.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.Entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, String>{

}
