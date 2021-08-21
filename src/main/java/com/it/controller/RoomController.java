package com.it.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.it.Entity.RoomEntity;
import com.it.Repository.RoomRepository;

@RestController
public class RoomController {

	@Autowired
	private RoomRepository roomRepository;
	
	@GetMapping("/room")
	public ResponseEntity<List<RoomEntity>> getAllRoom(){
		return ResponseEntity.ok(roomRepository.findAll());
	}
	
	@GetMapping("/room/{roomId}")
	public ResponseEntity<RoomEntity> getRoomByroomId(@PathVariable("roomId") Integer roomId){
		Optional<RoomEntity> entity = roomRepository.findById(roomId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
			
	}
	
	@PostMapping("/room/save")
	public ResponseEntity<RoomEntity> saveRoom(@RequestBody RoomEntity request){
		if (request != null)  {
			RoomEntity entity = new RoomEntity();
			entity.setRoomId(request.getRoomId());
			entity.setRoomName(request.getRoomName());
			entity.setRoomTypename(request.getRoomTypename());
			entity.setRoomPrice(request.getRoomPrice());
			entity.setRoomLight(request.getRoomLight());
			entity.setRoomWater(request.getRoomWater());
			entity.setRoomStatvs(request.getRoomStatvs());
			
			
	return ResponseEntity.ok(roomRepository.save(entity));

		} else {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	
	@PostMapping("/room/update")
	public ResponseEntity<RoomEntity> updateRoom(@RequestBody RoomEntity request) {
		if (request.getRoomId() != null) {
			Optional<RoomEntity> entity = roomRepository.findById(request.getRoomId());
			if (entity.isPresent()) {
				//set update data form request
				RoomEntity updateEntity = entity.get();
				updateEntity.setRoomName(request.getRoomName());
				updateEntity.setRoomTypename(request.getRoomTypename());
				updateEntity.setRoomPrice(request.getRoomPrice());
				updateEntity.setRoomLight(request.getRoomLight());
				updateEntity.setRoomWater(request.getRoomWater());
				updateEntity.setRoomStatvs(request.getRoomStatvs());
				
				return ResponseEntity.ok(roomRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
		}
	
}//
