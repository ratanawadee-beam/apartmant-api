package com.it.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.it.Entity.RoomEntity;

public interface RoomRepository extends JpaRepository<RoomEntity, Integer>{

}
