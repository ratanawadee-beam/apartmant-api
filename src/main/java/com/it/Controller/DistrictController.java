package com.it.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.Entity.AmphurEntity;
import com.it.Entity.DistrictEntity;
import com.it.Repository.AmphurRepository;
import com.it.Repository.DistrictRepository;
import com.it.Repository.ProvinceRepository;
import com.it.Entity.ProvinceEntity;
import com.it.model.AmphurResponse;
import com.it.model.DistrictResponse;
import com.it.model.ProvinceResponse;

@RestController
public class DistrictController {

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private AmphurRepository amphurRepository;

	@Autowired
	private ProvinceRepository provinceRepository;

	@Autowired
	private ModelMapper modelMapper;

	private DistrictResponse converToResponse(DistrictEntity entity) {
		DistrictResponse response = new DistrictResponse();
		
		if (ObjectUtils.isNotEmpty(entity)) {
			response = modelMapper.map(entity, DistrictResponse.class);
			
			Optional<AmphurEntity> amphurEntity = amphurRepository.findById(entity.getAmphurId());
			if (amphurEntity.isPresent()) {
				response.setAmphur(modelMapper.map(amphurEntity.get(), AmphurResponse.class));

				Optional<ProvinceEntity> provinceEntity = provinceRepository.findById(amphurEntity.get().getProvinceId());
				if (provinceEntity.isPresent()) {
					response.setProvince(modelMapper.map(provinceEntity.get(), ProvinceResponse.class));
				}
			}
		}
		return response;
	}

	@GetMapping("/district/zipCode")
	public ResponseEntity<List<DistrictResponse>> getAllDistrict(@RequestParam("zipCode") String zipCode) {
		List<DistrictResponse> response = new ArrayList<>();
		List<DistrictEntity> entities = districtRepository.findByZipCode(zipCode);
		if (CollectionUtils.isNotEmpty(entities)) {
			response = entities.stream().map(this::converToResponse).collect(Collectors.toList());
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/district/by-zip-code")
	public ResponseEntity<DistrictResponse> getSubdistrictBySdtId(@RequestParam("zipCode") String zipCode){
		List<DistrictEntity> entities = districtRepository.findAll();
		if(CollectionUtils.isNotEmpty(entities)) {			
			Optional<DistrictEntity> response = entities.stream().filter(entity -> entity.getZipCode().equalsIgnoreCase(zipCode)).findFirst();
			if (response.isPresent()) {
				return ResponseEntity.ok(this.converToResponse(response.get()));
			} else {
				return ResponseEntity.badRequest().body(null);
			}
			
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/district/{DistrictId}")
	public ResponseEntity<DistrictResponse> getDistrictByDistrictId(@PathVariable("DistrictId") Integer DistrictId) {
		Optional<DistrictEntity> entity = districtRepository.findById(DistrictId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(this.converToResponse(entity.get()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}
	@PostMapping("/district/save")
	public ResponseEntity<DistrictEntity> saveDistrict(@RequestBody DistrictEntity request) {
		if (request != null) {
			DistrictEntity entity = new DistrictEntity();
			entity.setDistrictId(request.getDistrictId());
			entity.setZipCode(request.getZipCode());
			entity.setDistrictNameTh(request.getDistrictNameTh());
			entity.setDistrictNameEn(request.getDistrictNameEn());
			entity.setAmphurId(request.getAmphurId());
			return ResponseEntity.ok(districtRepository.save(entity));

		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}

	@PostMapping("/district/update")
	public ResponseEntity<DistrictEntity> updateDistrict(@RequestBody DistrictEntity request) {
		if (request.getDistrictId() != null) {
			Optional<DistrictEntity> entity = districtRepository.findById(request.getDistrictId());
			if (entity.isPresent()) {
				// set update data form request
				DistrictEntity updateEntity = entity.get();
				updateEntity.setZipCode(request.getZipCode());
				updateEntity.setDistrictNameTh(request.getDistrictNameTh());
				updateEntity.setDistrictNameEn(request.getDistrictNameEn());
				updateEntity.setAmphurId(request.getAmphurId());
				return ResponseEntity.ok(districtRepository.save(updateEntity));
			} else {
				return ResponseEntity.badRequest().body(null);
			}

		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}

}//

