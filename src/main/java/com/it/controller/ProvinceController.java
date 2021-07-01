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


import com.it.Entity.ProvinceEntity;
import com.it.Repository.ProvinceRepository;

@RestController
public class ProvinceController {

	@Autowired
	private ProvinceRepository provinceRepository;
	
	@GetMapping("/province")
	public ResponseEntity<List<ProvinceEntity>> getAllProvince(){
		return ResponseEntity.ok(provinceRepository.findAll());
	}
	
	@GetMapping("/province/{ProvinceId}")
	public ResponseEntity<ProvinceEntity> getProvinceByProvinceId(@PathVariable("ProvinceId") Integer ProvinceId){
		Optional<ProvinceEntity> entity = provinceRepository.findById(ProvinceId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
			
	}
	
	@PostMapping("/province/save")
	public ResponseEntity<ProvinceEntity> saveProvince(@RequestBody ProvinceEntity request){
		if (request != null)  {
			ProvinceEntity entity = new ProvinceEntity();
			entity.setProvinceId(request.getProvinceId());
			entity.setProvinceCode(request.getProvinceCode());
			entity.setProvinceNameTh(request.getProvinceNameTh());
			entity.setProvinceNameEn(request.getProvinceNameEn());
			entity.setArea(request.getArea());
	return ResponseEntity.ok(provinceRepository.save(entity));

		} else {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	
	@PostMapping("/province/update")
	public ResponseEntity<ProvinceEntity> updateProvince(@RequestBody ProvinceEntity request) {
		if (request.getProvinceId() != null) {
			Optional<ProvinceEntity> entity = provinceRepository.findById(request.getProvinceId());
			if (entity.isPresent()) {
				//set update data form request
				ProvinceEntity updateEntity = entity.get();
				updateEntity.setProvinceCode(request.getProvinceCode());
				updateEntity.setProvinceNameTh(request.getProvinceNameTh());
				updateEntity.setProvinceNameEn(request.getProvinceNameEn());
				updateEntity.setArea(request.getArea());
				return ResponseEntity.ok(provinceRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
		}
}
