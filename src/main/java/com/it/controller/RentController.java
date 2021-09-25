package com.it.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.Entity.RentEntity;
import com.it.Entity.RoomEntity;
import com.it.Entity.UserEntity;
import com.it.Repository.RentRepository;
import com.it.Repository.RoomRepository;
import com.it.Repository.UserRepository;
import com.it.model.RentResponse;
import com.it.model.RoomResponse;
import com.it.model.UserResponse;

@RestController

public class RentController {

	@Autowired
	private RentRepository rentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	private RentResponse  convertToResponse(RentEntity entity) {
		RentResponse response = modelMapper.map(entity, RentResponse.class);
		
		//set user
				Optional<UserEntity> userEntity = userRepository.findById(entity.getUserId());
				if (userEntity.isPresent()) {
					response.setUser(modelMapper.map(userEntity.get(), UserResponse.class));
				}
		//set room	
				Optional<RoomEntity> roomEntity = roomRepository.findById(String.valueOf(entity.getRoomId()));//ถ้าเป็น autokey ให้ใส่ Integer.valueOf
				if (roomEntity.isPresent()) {
					response.setRoom(modelMapper.map(roomEntity.get(), RoomResponse.class));
				}
				return response;
			}
	
	@GetMapping("/rent")
	public ResponseEntity<List<RentResponse>> getAllRent(){
		List<RentEntity> entities = rentRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/rent/{rentId}")
	public ResponseEntity<List<RentResponse>> getRentByrentId(@PathVariable("rentId") Integer rentId){
		Optional<RentEntity> entity = rentRepository.findById(rentId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.stream().map(this::convertToResponse).collect(Collectors.toList()));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/rent/save")
	public ResponseEntity<RentEntity> saveRent(@RequestBody RentEntity request){
		if (request != null) {
			RentEntity entity = new RentEntity();
			entity.setRentId(request.getRentId());
			entity.setRentStart(request.getRentStart());
			entity.setRentEnd(request.getRentEnd());
			entity.setRentInsurance(request.getRentInsurance());
			entity.setRentTotalprice(request.getRentTotalprice());
			entity.setRentOther(request.getRentOther());
			entity.setRentLi(request.getRentLi());
			entity.setRentWa(request.getRentWa());
			entity.setUserId(request.getUserId());
			entity.setRoomId(request.getRoomId());
			
		return ResponseEntity.ok(rentRepository.save(entity));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
		}
	
	@PostMapping("/rent/update")
	public ResponseEntity<RentEntity> updateRent(@RequestBody RentEntity request){
		if (request.getRentId() != null) {
			Optional<RentEntity> entity = rentRepository.findById(request.getRentId());
			if (entity.isPresent()) {
				RentEntity updateEntity = entity.get();
				updateEntity.setRentInsurance(request.getRentInsurance());
				updateEntity.setRentTotalprice(request.getRentTotalprice());
				updateEntity.setRentOther(request.getRentOther());
				updateEntity.setRentStart(request.getRentStart());
				updateEntity.setRentEnd(request.getRentEnd());
				updateEntity.setRentLi(request.getRentLi());
				updateEntity.setRentWa(request.getRentWa());
				updateEntity.setUserId(request.getUserId());
				updateEntity.setRoomId(request.getRoomId());
				return ResponseEntity.ok(rentRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	@DeleteMapping("/rent/{rentId}")
	public ResponseEntity<String> deleteRentByRentId(@PathVariable("rentId") String rentId) {
		rentRepository.deleteById(Integer.valueOf(rentId));
		return ResponseEntity.ok("SUCCESS");
	}
}
