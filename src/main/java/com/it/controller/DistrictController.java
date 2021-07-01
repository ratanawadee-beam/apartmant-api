//package com.it.Controller;
//
//public interface DistrictController {
//
//	
//}
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


import com.it.Entity.DistrictEntity;
import com.it.Repository.DistrictRepository;

@RestController

public class DistrictController {

	@Autowired
	private DistrictRepository districtRepository;
	
	@GetMapping("/district")
	public ResponseEntity<List<DistrictEntity>> getAllDistrict(){
		return ResponseEntity.ok(districtRepository.findAll());
	}
	
	@GetMapping("/district/{DistrictId}")
	public ResponseEntity<DistrictEntity> getDistrictByDistrictId(@PathVariable("DistrictId") Integer DistrictId){
		Optional<DistrictEntity> entity = districtRepository.findById(DistrictId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
			
	}
	
	@PostMapping("/district/save")
	public ResponseEntity<DistrictEntity> saveDistrict(@RequestBody DistrictEntity request){
		if (request != null)  {
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
				//set update data form request
				DistrictEntity updateEntity = entity.get();
				updateEntity.setZipCode(request.getZipCode());
				updateEntity.setDistrictNameTh(request.getDistrictNameTh());
				updateEntity.setDistrictNameEn(request.getDistrictNameEn());
				updateEntity.setAmphurId(request.getAmphurId());
				return ResponseEntity.ok(districtRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
		}
	
	}//

