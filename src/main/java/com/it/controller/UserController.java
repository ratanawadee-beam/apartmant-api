package com.it.Controller;

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

import com.it.Entity.RoleEntity;
import com.it.Entity.UserEntity;
import com.it.Repository.RoleRepository;
import com.it.Repository.UserRepository;
import com.it.model.RoleResponse;
import com.it.model.UserResponse;
import com.it.utils.PasswordEncryptorUtils;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	private UserResponse convertToResponse(UserEntity entity) {
		UserResponse response = modelMapper.map(entity, UserResponse.class);
		
		//set role
		Optional<RoleEntity> roleEntity = roleRepository.findById(entity.getRoleId());
		if (roleEntity.isPresent()) {
			response.setRole(modelMapper.map(roleEntity.get(), RoleResponse.class));
		}
		return response;
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<UserResponse>> getAllUser(){
		List<UserEntity> entities = userRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserEntity> getUserByuserId(@PathVariable("userId") String userId){
		Optional<UserEntity> entity = userRepository.findById(userId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
			
	}
	
	@PostMapping("/user/save")
	public ResponseEntity<UserEntity> saveRole(@RequestBody UserEntity request){
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
			entity.setAmphurNameTh(request.getAmphurNameTh());
			entity.setDistrictNameTh(request.getDistrictNameTh());
			entity.setProvinceNameTh(request.getProvinceNameTh());
			entity.setRoleId(request.getRoleId());
			entity.setZipCode(request.getZipCode());
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
//				updateEntity.setUserPassword(request.getUserPassword());
				updateEntity.setUserTitle(request.getUserTitle());
				updateEntity.setUserName(request.getUserName());
				updateEntity.setUserLasname(request.getUserLasname());
				updateEntity.setUserBirthday(request.getUserBirthday());
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
				return ResponseEntity.ok(userRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
		}
}//
