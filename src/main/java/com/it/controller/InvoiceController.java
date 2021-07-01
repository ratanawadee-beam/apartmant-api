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

import com.it.Entity.InvoiceEntity;
import com.it.Repository.InvoiceRepository;

@RestController

public class InvoiceController {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@GetMapping("/invoice")
	public ResponseEntity<List<InvoiceEntity>> getAllInvoice(){
		return ResponseEntity.ok(invoiceRepository.findAll());
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
			entity.setInvoiceStart(request.getInvoiceStart() != null ? entity.getInvoiceStart() : new Date());
			entity.setInvoiceEnd(request.getInvoiceEnd() != null ? entity.getInvoiceEnd() : new Date());
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

