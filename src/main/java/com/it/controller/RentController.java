package com.it.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.Entity.RentEntity;
import com.it.Repository.RentRepository;

@RestController

public class RentController {

	
	@Autowired
	private RentRepository rentRepository;
	
	@GetMapping("/rent")
	public ResponseEntity<List<RentEntity>> getAllRent(){
		return ResponseEntity.ok(rentRepository.findAll());
	}
	
	@GetMapping("/rent/{rentId}")
	public ResponseEntity<RentEntity> getRentByrentId(@PathVariable("rentId") Integer rentId){
		Optional<RentEntity> entity = rentRepository.findById(rentId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/rent/save")
	public ResponseEntity<RentEntity> saveRent(@RequestBody RentEntity request){
		if (request != null) {
			RentEntity entity = new RentEntity();
			entity.setRentId(request.getRentId());
			entity.setRentStart(request.getRentStart() != null ? entity.getRentStart() : new Date());
			entity.setRentEnd(request.getRentEnd() != null ? entity.getRentEnd() : new Date());
			entity.setRentInsurance(request.getRentInsurance());
			entity.setRentWa(request.getRentWa());
			entity.setRentTotalprice(request.getRentTotalprice());
			entity.setRentLi(request.getRentLi());
			entity.setRentOther(request.getRentOther());
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
				updateEntity.setRentWa(request.getRentWa());
				updateEntity.setRentTotalprice(request.getRentTotalprice());
				updateEntity.setRentLi(request.getRentLi());
				updateEntity.setRentOther(request.getRentOther());
				updateEntity.setUserId(request.getUserId());
				updateEntity.setRoomId(request.getRoomId());
				if (request.getRentStart() != null) {
					updateEntity.setRentStart(request.getRentStart());
				}
				if (request.getRentEnd() != null) {
					updateEntity.setRentEnd(request.getRentEnd());
				}
			
				return ResponseEntity.ok(rentRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
}
