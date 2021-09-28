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

import com.it.Entity.AmphurEntity;
import com.it.Repository.AmphurRepository;

@RestController

public class AmphurController {

	@Autowired
	private AmphurRepository amphurRepository;
	
	@GetMapping("/amphur")
	public ResponseEntity<List<AmphurEntity>> getAllAmphur(){
		return ResponseEntity.ok(amphurRepository.findAll());
	}
	
	@GetMapping("/amphur/{AmphurId}")
	public ResponseEntity<AmphurEntity> getAmphurByAmphurId(@PathVariable("AmphurId") Integer AmphurId){
		Optional<AmphurEntity> entity = amphurRepository.findById(AmphurId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
			
	}
	
	@PostMapping("/amphur/save")
	public ResponseEntity<AmphurEntity> saveAmphur(@RequestBody AmphurEntity request){
		if (request != null)  {
			AmphurEntity entity = new AmphurEntity();
			entity.setAmphurId(request.getAmphurId());
			entity.setAmphurCode(request.getAmphurCode());
			entity.setAmphurNameTh(request.getAmphurNameTh());
			entity.setAmphurNameEn(request.getAmphurNameEn());
			entity.setProvinceId(request.getProvinceId());
	return ResponseEntity.ok(amphurRepository.save(entity));

		} else {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	
	@PostMapping("/amphur/update")
	public ResponseEntity<AmphurEntity> updateAmphur(@RequestBody AmphurEntity request) {
		if (request.getAmphurId() != null) {
			Optional<AmphurEntity> entity = amphurRepository.findById(request.getAmphurId());
			if (entity.isPresent()) {
				//set update data form request
				AmphurEntity updateEntity = entity.get();
				updateEntity.setAmphurCode(request.getAmphurCode());
				updateEntity.setAmphurNameTh(request.getAmphurNameTh());
				updateEntity.setAmphurNameEn(request.getAmphurNameEn());
				updateEntity.setProvinceId(request.getProvinceId());
				return ResponseEntity.ok(amphurRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
		}
	
	}//

