package com.it.Controller;

import java.util.Date;
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


import com.it.Entity.InvoicedetailEntity;
import com.it.Entity.RentEntity;
import com.it.Repository.InvoicedetailRepository;
import com.it.Repository.RentRepository;
import com.it.model.InvoicedetailResponse;
import com.it.model.RentResponse;


@RestController
public class InvoicedetailController {

	@Autowired
	private InvoicedetailRepository invoicedetailRepository;
	
	@Autowired
	private RentRepository rentRepository;
	
	
	@Autowired 
	private ModelMapper modelMapper;
	
	private InvoicedetailResponse convertToResponse(InvoicedetailEntity entity) {
		InvoicedetailResponse response = modelMapper.map(entity, InvoicedetailResponse.class);
		
		//set rent
		Optional<RentEntity> rentEntity = rentRepository.findById(Integer.valueOf(entity.getRentId()));
		if (rentEntity.isPresent()) {
			response.setRent(modelMapper.map(rentEntity.get(), RentResponse.class));
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
			entity.setDeId(request.getDeId());
			entity.setDeStartdate(request.getDeStartdate() != null ? entity.getDeStartdate() : new Date());
			entity.setDeStartdate(request.getDeEnddate() != null ? entity.getDeEnddate() : new Date());
			entity.setDeWaOld(request.getDeWaOld());
			entity.setDeWaNew(request.getDeWaNew());
			entity.setDeLiOld(request.getDeLiOld());
			entity.setDeLiNew(request.getDeLiNew());
			entity.setDeTotalunitLi(request.getDeTotalunitLi());
			entity.setDeTotalunitWa(request.getDeTotalunitWa());
			entity.setDeTotalroom(request.getDeTotalroom());
			entity.setDeTotalLi(request.getDeTotalLi());
			entity.setDeTotalWa(request.getDeTotalWa());
			entity.setDeTotal(request.getDeTotal());
			entity.setDeUnpaid(request.getDeUnpaid());
			entity.setRentId(request.getRentId());
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
				updateEntity.setDeWaOld(request.getDeWaOld());
				updateEntity.setDeWaNew(request.getDeWaNew());
				updateEntity.setDeLiOld(request.getDeLiOld());
				updateEntity.setDeLiNew(request.getDeLiNew());
				updateEntity.setDeTotalunitLi(request.getDeTotalunitLi());
				updateEntity.setDeTotalunitWa(request.getDeTotalunitWa());
				updateEntity.setDeTotalroom(request.getDeTotalroom());
				updateEntity.setDeTotalLi(request.getDeTotalLi());
				updateEntity.setDeTotalWa(request.getDeTotalWa());
				updateEntity.setDeTotal(request.getDeTotal());
				updateEntity.setDeUnpaid(request.getDeUnpaid());
				updateEntity.setRentId(request.getRentId());
				if (request.getDeStartdate() != null) {
					updateEntity.setDeStartdate(request.getDeStartdate());
				}
				if (request.getDeEnddate() != null) {
					updateEntity.setDeEnddate(request.getDeEnddate());
				}
				return ResponseEntity.ok(invoicedetailRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
}//
