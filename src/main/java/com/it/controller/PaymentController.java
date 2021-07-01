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


import com.it.Entity.PaymentEntity;
import com.it.Repository.PaymentRepository;

@RestController
public class PaymentController {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@GetMapping("/payment")
	public ResponseEntity<List<PaymentEntity>> getAllPayment(){
		return ResponseEntity.ok(paymentRepository.findAll());
	}
	
	@GetMapping("/payment/{payId}")
	public ResponseEntity<PaymentEntity> getPaymentBypayId(@PathVariable("payId") Integer payId){
		Optional<PaymentEntity> entity = paymentRepository.findById(payId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
			
	}
	
	@PostMapping("/payment/save")
	public ResponseEntity<PaymentEntity> savePayment(@RequestBody PaymentEntity request){
		if (request != null)  {
			PaymentEntity entity = new PaymentEntity();
			entity.setPayId(request.getPayId());
			entity.setPayDate(request.getPayDate() != null ? entity.getPayDate() : new Date());
			entity.setPayTotal(request.getPayTotal());
			entity.setInvoiceId(request.getInvoiceId());
	return ResponseEntity.ok(paymentRepository.save(entity));

		} else {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	
	@PostMapping("/payment/update")
	public ResponseEntity<PaymentEntity> updatePayment(@RequestBody PaymentEntity request) {
		if (request.getPayId() != null) {
			Optional<PaymentEntity> entity = paymentRepository.findById(request.getPayId());
			if (entity.isPresent()) {
				//set update data form request
				PaymentEntity updateEntity = entity.get();
				updateEntity.setPayTotal(request.getPayTotal());
				updateEntity.setInvoiceId(request.getInvoiceId());
				if (request.getPayDate() != null) {
					updateEntity.setPayDate(request.getPayDate());
				}
				
				return ResponseEntity.ok(paymentRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
		}
}
