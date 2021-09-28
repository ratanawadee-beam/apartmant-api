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

import com.it.Entity.RoleEntity;
import com.it.Repository.RoleRepository;

@RestController
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/role")
	public ResponseEntity<List<RoleEntity>> getAllRole(){
		return ResponseEntity.ok(roleRepository.findAll());
	}
	
	@GetMapping("/role/{RoleId}")
	public ResponseEntity<RoleEntity> getRoleByRoleId(@PathVariable("RoleId") String RoleId){
		Optional<RoleEntity> entity = roleRepository.findById(RoleId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
			
	}
	
	@PostMapping("/role/save")
	public ResponseEntity<RoleEntity> saveRole(@RequestBody RoleEntity request){
		if (request != null)  {
			RoleEntity entity = new RoleEntity();
			entity.setRoleId(request.getRoleId());
			entity.setRoleName(request.getRoleName());
			entity.setRoleDescription(request.getRoleDescription());
			entity.setRoleStatus(request.getRoleStatus());
			
	return ResponseEntity.ok(roleRepository.save(entity));

		} else {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	
	@PostMapping("/role/update")
	public ResponseEntity<RoleEntity> updateRole(@RequestBody RoleEntity request) {
		if (request.getRoleId() != null) {
			Optional<RoleEntity> entity = roleRepository.findById(request.getRoleId());
			if (entity.isPresent()) {
				//set update data form request
				RoleEntity updateEntity = entity.get();
				updateEntity.setRoleName(request.getRoleName());
				updateEntity.setRoleDescription(request.getRoleDescription());
				updateEntity.setRoleStatus(request.getRoleStatus());
				return ResponseEntity.ok(roleRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
		}
}
