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

import com.it.Entity.ContactEntity;
import com.it.Entity.RoomEntity;
import com.it.Repository.ContactRepository;

@RestController
public class ContactController {

	@Autowired
	private ContactRepository contactRepository;
	
	@GetMapping("/contact")
	public ResponseEntity<List<ContactEntity>> getAllcontact(){
		return ResponseEntity.ok(contactRepository.findAll());
	}
	
	@GetMapping("/contact/{contactId}")
	public ResponseEntity<ContactEntity> getAllBycontactId(@PathVariable("contactId") Integer contactId){
		Optional<ContactEntity> entity = contactRepository.findById(contactId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
			
	}
	
	@PostMapping("/contact/save")
	public ResponseEntity<ContactEntity> saveContact(@RequestBody ContactEntity request){
		if (request != null)  {
			ContactEntity entity = new ContactEntity();
			
			entity.setConId(request.getConId());
			entity.setConName(request.getConName());
			entity.setConPhone(request.getConPhone());
			entity.setConText(request.getConText());
			entity.setRoomId(request.getRoomId());
			entity.setUserId(request.getUserId());
			
			
	return ResponseEntity.ok(contactRepository.save(entity));

		} else {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	
	@PostMapping("/contact/update")
	public ResponseEntity<ContactEntity> updateContact(@RequestBody ContactEntity request) {
		if (request.getConId() != null) {
			Optional<ContactEntity> entity = contactRepository.findById(request.getConId());
			if (entity.isPresent()) {
				//set update data form request
				ContactEntity updateEntity = entity.get();
				updateEntity.setConName(request.getConName());
				updateEntity.setConPhone(request.getConPhone());
				updateEntity.setConText(request.getConText());
				updateEntity.setRoomId(request.getRoomId());
				updateEntity.setUserId(request.getUserId());
				
				return ResponseEntity.ok(contactRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
		}
	
	
}
