package com.it.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.it.Entity.InvoiceEntity;

import com.it.Entity.RentEntity;
import com.it.Entity.RoomEntity;
import com.it.Entity.UserEntity;
import com.it.Repository.InvoiceRepository;
import com.it.Repository.RentRepository;
import com.it.Repository.RoomRepository;
import com.it.Repository.UserRepository;

import com.it.model.InvoiceResponse;

import com.it.model.RentResponse;
import com.it.model.RoomResponse;
import com.it.model.UserResponse;

@RestController

public class InvoiceController {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private RentRepository rentRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
//	private  InvoiceResponse convertToResponse(InvoiceEntity entity) {
//		InvoiceResponse response = new InvoiceResponse();
//		if (ObjectUtils.isNotEmpty(entity)) {
//			response = modelMapper.map(entity, InvoiceResponse.class);
//			
//			Optional<RentEntity> rentEntity = rentRepository.findById(Integer.valueOf(entity.getRentId()));
//			if (rentEntity.isPresent()) {
//				response.setRent(modelMapper.map(rentEntity.get(), RentResponse.class));
//					
//					Optional<UserEntity> userEntity = userRepository.findById(String.valueOf(entity.getUserId()));
//					if (userEntity.isPresent()) {
//						response.setUser(modelMapper.map(userEntity.get(), UserResponse.class));
//						
//						Optional<RoomEntity> roomEntity = roomRepository.findById(String.valueOf(entity.getRoomId()));
//						if (roomEntity.isPresent()) {
//							response.setRoom(modelMapper.map(roomEntity.get(), RoomResponse.class));
//					}
//				}
//			}
//		}
//		return response;
//	}

	public InvoiceResponse convertToResponse(InvoiceEntity entity) {
		InvoiceResponse response = modelMapper.map(entity, InvoiceResponse.class);

		Optional<RentEntity> rentEntity = rentRepository.findById(entity.getRentId());
		if (rentEntity.isPresent()) {
			RentResponse rentResponse = modelMapper.map(rentEntity.get(), RentResponse.class);

			Optional<UserEntity> userEntity = userRepository.findById(String.valueOf(entity.getUserId()));
			if (userEntity.isPresent()) {
				response.setUser(modelMapper.map(userEntity.get(), UserResponse.class));

				Optional<RoomEntity> roomEntity = roomRepository.findById(String.valueOf(entity.getRoomId()));
				if (roomEntity.isPresent()) {
					response.setRoom(modelMapper.map(roomEntity.get(), RoomResponse.class));
				}
			}
		}

		return response;
	}
	
	@GetMapping("/invoice")
	public ResponseEntity<List<InvoiceResponse>> getAllInvoice(){
		List<InvoiceEntity> entities = invoiceRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/invoice/{InvoiceId}")
	public ResponseEntity<InvoiceResponse> getInvoiceByInvoiceId(@PathVariable("InvoiceId") Integer InvoiceId){
		Optional<InvoiceEntity> entity = invoiceRepository.findById(InvoiceId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(this.convertToResponse(entity.get()));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
			
	}
	
	@PostMapping("/invoice/save")
	public ResponseEntity<InvoiceEntity> saveInvoice(@RequestBody InvoiceEntity request){
		if (request != null)  {
			InvoiceEntity entity = new InvoiceEntity();
			entity.setInId(request.getInId());
			entity.setInStetus(request.getInStetus());
			entity.setInStart(request.getInStart());
			entity.setInEnd(request.getInEnd());
			entity.setRoomId(request.getRoomId());
			entity.setUserId(request.getUserId());
			entity.setRentId(request.getRentId());
			
	return ResponseEntity.ok(invoiceRepository.save(entity));

		} else {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	
	@PostMapping("/invoice/update")
	public ResponseEntity<InvoiceEntity> updateinvoice(@RequestBody InvoiceEntity request) {
		if (request.getInId() != null) {
			Optional<InvoiceEntity> entity = invoiceRepository.findById(request.getInId());
			if (entity.isPresent()) {
				//set update data form request
				InvoiceEntity updateEntity = entity.get();
				updateEntity.setInStetus(request.getInStetus());
				updateEntity.setInStart(request.getInStart());
				updateEntity.setInEnd(request.getInEnd());
				updateEntity.setRentId(request.getRentId());
				
				return ResponseEntity.ok(invoiceRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
		}
	
	@GetMapping("/invoice/by-userId{userId}")
	public ResponseEntity<List<InvoiceResponse>> invoiceByuserId(@PathVariable("userId") String userId){
//		Optional<RentEntity> entity = rentRepository.findByUserId(userId);
//		if (null != entity && entity.size() > 0) {
			Optional<InvoiceEntity> entity = invoiceRepository.findByUserId(userId);
			if (entity.isPresent()) {
			return ResponseEntity.ok(entity.stream().map(this::convertToResponse).collect(Collectors.toList()));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	

	
	
	
	}//

