package com.it.Controller;

import java.util.Date;
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

import com.it.Entity.InvoiceEntity;
import com.it.Entity.InvoicedetailEntity;
import com.it.Entity.RentEntity;
import com.it.Repository.InvoiceRepository;
import com.it.Repository.InvoicedetailRepository;
import com.it.Repository.RentRepository;
import com.it.model.InvoiceResponse;
import com.it.model.InvoicedetailResponse;
import com.it.model.RentResponse;


@RestController
public class InvoicedetailController {

	@Autowired
	private InvoicedetailRepository invoicedetailRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	private InvoicedetailResponse convertToResponse(InvoicedetailEntity entity) {
		InvoicedetailResponse response = modelMapper.map(entity, InvoicedetailResponse.class);
		
		//set invoice
		Optional<InvoiceEntity> invoiceEntity = invoiceRepository.findById(entity.getInId());
		if (invoiceEntity.isPresent()) {
			InvoiceResponse invoiceResponse = modelMapper.map(invoiceEntity.get(), InvoiceResponse.class);
		}
		return response;
  }
	
	@GetMapping("/invoicedetail")
	public ResponseEntity<List<InvoicedetailResponse>> getAllInvoicedetail(){
		List<InvoicedetailEntity> entities = invoicedetailRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/invoicedetail/{deId}")
	public ResponseEntity<InvoicedetailEntity> getInvoicedetailBydeId(@PathVariable("deId") Integer deId){
		Optional<InvoicedetailEntity> entity = invoicedetailRepository.findById(deId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/invoicedetail/save")
	public ResponseEntity<InvoicedetailEntity> saveInvoicedetail(@RequestBody InvoicedetailEntity request){
		if (request != null) {
			InvoicedetailEntity entity = new InvoicedetailEntity();
			entity.setDeWaold(request.getDeWaold());
			entity.setDeLiold(request.getDeLiold());
			entity.setDeWanew(request.getDeWanew());
			entity.setDeLinew(request.getDeLinew());
			entity.setTotalunitLi(request.getTotalunitLi());
			entity.setTotalunitWa(request.getTotalunitWa());
			entity.setTotalRoom(request.getTotalRoom());
			entity.setTotalLi(request.getTotalLi());
			entity.setTotalWa(request.getTotalWa());
			entity.setDeTotal(request.getDeTotal());
			entity.setInStart(request.getInStart());
			entity.setInEnd(request.getInEnd());
			entity.setInId(request.getInId());
		return ResponseEntity.ok(invoicedetailRepository.save(entity));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
		}
	
	@PostMapping("/invoicedetail/update")
	public ResponseEntity<InvoicedetailEntity> updateInvoicedetail(@RequestBody InvoicedetailEntity request){
		if (request.getDeId() != null) {
			Optional<InvoicedetailEntity> entity = invoicedetailRepository.findById(request.getDeId());
			if (entity.isPresent()) {
				InvoicedetailEntity updateEntity = entity.get();
				updateEntity.setDeWaold(request.getDeWaold());
				updateEntity.setDeLiold(request.getDeLiold());
				updateEntity.setDeWanew(request.getDeWanew());
				updateEntity.setDeLinew(request.getDeLinew());
				updateEntity.setTotalunitLi(request.getTotalunitLi());
				updateEntity.setTotalunitWa(request.getTotalunitWa());
				updateEntity.setTotalRoom(request.getTotalRoom());
				updateEntity.setTotalLi(request.getTotalLi());
				updateEntity.setTotalWa(request.getTotalWa());
				updateEntity.setDeTotal(request.getDeTotal());
				updateEntity.setInStart(request.getInStart());
				updateEntity.setInEnd(request.getInEnd());	
				updateEntity.setInId(request.getInId());
				return ResponseEntity.ok(invoicedetailRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@DeleteMapping("/invoicedetail/{deId}")
	public ResponseEntity<String> deleteinvoicedetailByDeId(@PathVariable("deId") Integer deId) {
		invoicedetailRepository.deleteById(Integer.valueOf(deId));
		return ResponseEntity.ok("SUCCESS");
	}

	
}//
