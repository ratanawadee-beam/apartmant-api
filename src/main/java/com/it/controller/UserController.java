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


import com.it.Entity.UserEntity;
import com.it.Repository.UserRepository;
import com.it.utils.PasswordEncryptorUtils;

@RestController
public class UserController {

	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/user")
	public ResponseEntity<List<UserEntity>> getAllUser(){
		return ResponseEntity.ok(userRepository.findAll());
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserEntity> getUserByuserId(@PathVariable("userId") Integer userId){
		Optional<UserEntity> entity = userRepository.findById(userId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
			
	}
	
	@PostMapping("/user/save")
	public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity request){
		if (request != null)  {
			UserEntity entity = new UserEntity();
			entity.setUserId(request.getUserId());
			entity.setUserUsername(request.getUserUsername());
			entity.setUserPassword(PasswordEncryptorUtils.passwordEncryptor(request.getUserPassword()));
			entity.setUserTitle(request.getUserTitle());
			entity.setUserName(request.getUserName());
			entity.setUserLasname(request.getUserLasname());
			entity.setUserBirthday(request.getUserBirthday());
			entity.setUserIdcard(request.getUserIdcard());
			entity.setUserPhone(request.getUserPhone());
			entity.setUserGender(request.getUserGender());
			entity.setUserAddress(request.getUserAddress());
			entity.setUserEmail(request.getUserEmail());
			entity.setRoleId(request.getRoleId());
			entity.setZipCode(request.getZipCode());
			entity.setProvinceNameTh(request.getProvinceNameTh());
			entity.setAmphurNameTh(request.getAmphurNameTh());
			entity.setDistrictNameTh(request.getDistrictNameTh());
			entity.setRoomName(request.getRoomName());
			
	return ResponseEntity.ok(userRepository.save(entity));

		} else {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	
	@PostMapping("/user/update")
	public ResponseEntity<UserEntity> updateRole(@RequestBody UserEntity request) {
		if (request.getUserId() != null) {
			Optional<UserEntity> entity = userRepository.findById(request.getUserId());
			if (entity.isPresent()) {
				//set update data form request
				UserEntity updateEntity = entity.get();
				updateEntity.setUserUsername(request.getUserUsername());
				updateEntity.setUserPassword(request.getUserPassword());
				updateEntity.setUserTitle(request.getUserTitle());
				updateEntity.setUserName(request.getUserName());
				updateEntity.setUserLasname(request.getUserLasname());
				updateEntity.setUserIdcard(request.getUserIdcard());
				updateEntity.setUserPhone(request.getUserPhone());
				updateEntity.setUserGender(request.getUserGender());
				updateEntity.setUserAddress(request.getUserAddress());
				updateEntity.setUserEmail(request.getUserEmail());
				updateEntity.setRoleId(request.getRoleId());
				updateEntity.setZipCode(request.getZipCode());
				updateEntity.setAmphurNameTh(request.getAmphurNameTh());
				updateEntity.setDistrictNameTh(request.getDistrictNameTh());
				updateEntity.setProvinceNameTh(request.getProvinceNameTh());
				updateEntity.setRoomName(request.getRoomName());
				
				if (request.getUserBirthday() != null) {
					updateEntity.setUserBirthday(request.getUserBirthday());
				}
				return ResponseEntity.ok(userRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
		}
}//
