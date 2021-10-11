package com.it.Controller;

import java.util.List;
import java.util.Optional;import javax.security.auth.message.callback.PrivateKeyCallback.Request;

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
	public ResponseEntity<List<RoomEntity>> getAllRoom() {
		return ResponseEntity.ok(roomRepository.findAll());
	}

	@GetMapping("/room/{roomId}")
	public ResponseEntity<RoomEntity> getRoomByroomId(@PathVariable("roomId") String roomId) {
		Optional<RoomEntity> entity = roomRepository.findById(roomId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}

	@PostMapping("/room/save")
	public ResponseEntity<RoomEntity> saveRoom(@RequestBody RoomEntity request) {
		if (request != null) {
			RoomEntity entity = null;
			Optional<RoomEntity> opEntity = roomRepository.findById(request.getRoomId());
			if (opEntity.isPresent()) {
				entity = opEntity.get();
			} else {
				entity = new RoomEntity();
			}
			entity.setRoomId(request.getRoomId());
			entity.setRoomTypename(request.getRoomTypename());
			entity.setRoomPrice(request.getRoomPrice());
			entity.setRoomLight(request.getRoomLight());
			entity.setRoomWater(request.getRoomWater());
			entity.setRoomStatus(request.getRoomStatus());

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
				// set update data form request
				RoomEntity updateEntity = entity.get();
				updateEntity.setRoomTypename(request.getRoomTypename());
				updateEntity.setRoomPrice(request.getRoomPrice());
				updateEntity.setRoomLight(request.getRoomLight());
				updateEntity.setRoomWater(request.getRoomWater());
				updateEntity.setRoomStatus(request.getRoomStatus());

				return ResponseEntity.ok(roomRepository.save(updateEntity));
			} else {
				return ResponseEntity.badRequest().body(null);
			}

		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}

	@PostMapping("/room/updateLightAndWater")
	public ResponseEntity<RoomEntity> updateLightAndWater(@RequestBody RoomEntity request) {
		if (request != null) {
			Optional<RoomEntity> opEntity = roomRepository.findById(request.getRoomId());
			if (opEntity.isPresent()) {
				RoomEntity entity = opEntity.get();
				entity.setRoomLight(request.getRoomLight());
				entity.setRoomWater(request.getRoomWater());
				entity.setRoomTypename(request.getRoomTypename());
				return ResponseEntity.ok(roomRepository.save(entity));
			}
		}
		return ResponseEntity.badRequest().body(null);
	}

	@PostMapping("/room/updateStatus")
	public ResponseEntity<RoomEntity> updateStatus(@RequestBody RoomEntity request) {
		if (request != null) {
			Optional<RoomEntity> room = roomRepository.findById(request.getRoomId());
			if (room.isPresent()) {
				RoomEntity entity = room.get();
				room.get().setRoomStatus("1");
				roomRepository.save(room.get());
				return ResponseEntity.ok(roomRepository.save(entity));
			}	
		}
		return ResponseEntity.badRequest().body(null);
	}

}//
