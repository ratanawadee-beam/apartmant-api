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
	
	private InvoiceResponse convertToResponse(InvoiceEntity entity) {
		InvoiceResponse response = modelMapper.map(entity, InvoiceResponse.class);

		// set rent
		Optional<RentEntity> rentEntity = rentRepository.findById(Integer.valueOf(entity.getRentId()));
		if (rentEntity.isPresent()) {
			response.setRent(modelMapper.map(rentEntity.get(), RentResponse.class));
		}

		// set user
		Optional<UserEntity> userEntity = userRepository.findById(Integer.valueOf(entity.getUserId()));
		if (userEntity.isPresent()) {
			response.setUser(modelMapper.map(userEntity.get(), UserResponse.class));
		}
		//set room	
		Optional<RoomEntity> roomEntity = roomRepository.findById(Integer.valueOf(entity.getRoomId()));// ถ้าเป็น
																										// autokey
																										// ให้ใส่
																										// Integer.valueOf
		if (roomEntity.isPresent()) {
			response.setRoom(modelMapper.map(roomEntity.get(), RoomResponse.class));
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
	public ResponseEntity<InvoiceEntity> getInvoiceByInvoiceId(@PathVariable("InvoiceId") Integer InvoiceId){
		Optional<InvoiceEntity> entity = invoiceRepository.findById(InvoiceId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
			
	}
	
	@PostMapping("/invoice/save")
	public ResponseEntity<InvoiceEntity> saveInvoice(@RequestBody InvoiceEntity request){
		if (request != null)  {
			InvoiceEntity entity = new InvoiceEntity();
			entity.setInvoiceId(request.getInvoiceId());
			entity.setInvoiceStetus(request.getInvoiceStetus());
			entity.setInvoiceNote(request.getInvoiceNote());
			entity.setInvoiceStart(request.getInvoiceStart());
			entity.setInvoiceEnd(request.getInvoiceEnd());
			entity.setRentId(request.getRentId());
			entity.setRoomId(request.getRoomId());
			entity.setUserId(request.getUserId());
			
	return ResponseEntity.ok(invoiceRepository.save(entity));

		} else {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	
	@PostMapping("/invoice/update")
	public ResponseEntity<InvoiceEntity> updateinvoice(@RequestBody InvoiceEntity request) {
		if (request.getInvoiceId() != null) {
			Optional<InvoiceEntity> entity = invoiceRepository.findById(request.getInvoiceId());
			if (entity.isPresent()) {
				//set update data form request
				InvoiceEntity updateEntity = entity.get();
				updateEntity.setInvoiceStetus(request.getInvoiceStetus());
				updateEntity.setInvoiceNote(request.getInvoiceNote());
				if (request.getInvoiceStart() != null) {
					updateEntity.setInvoiceStart(request.getInvoiceStart());
				}
				if (request.getInvoiceEnd() != null) {
					updateEntity.setInvoiceEnd(request.getInvoiceEnd());
				}
				updateEntity.setRentId(request.getRentId());
				updateEntity.setRoomId(request.getRoomId());
				updateEntity.setUserId(request.getUserId());
				
				return ResponseEntity.ok(invoiceRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
		}
	
	}//

