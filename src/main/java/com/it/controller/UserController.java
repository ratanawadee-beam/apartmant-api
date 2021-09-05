package com.it.Controller;

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
import com.it.Entity.RoleEntity;
import com.it.Entity.UserEntity;
import com.it.Repository.RoleRepository;
import com.it.Repository.UserRepository;
import com.it.dto.UserDto;
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
	
	@Autowired
	private RentController rentController;
	
	
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
	public ResponseEntity<UserEntity> getUserByuserId(@PathVariable("userId") Integer userId){
		Optional<UserEntity> entity = userRepository.findById(userId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
			
	}
	
	@PostMapping("/user/save")
	public ResponseEntity<UserEntity> saveRole(@RequestBody UserDto request){
		if (request != null)  {
			String[] parts = request.getUserEmail().split("@");
			UserEntity entity = new UserEntity();
			entity.setUserId(request.getUserId());
			entity.setUserUsername(parts[0]);
			entity.setUserPassword(PasswordEncryptorUtils.passwordEncryptor(request.getUserPhone()));
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
//			rentController.saveRent(request)
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
				return ResponseEntity.ok(userRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
			}else {
				return ResponseEntity.badRequest().body(null);
			}		
		}
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<String> deleteUserByUserId(@PathVariable("userId") String userId) {
		userRepository.deleteById(Integer.valueOf(userId));
		return ResponseEntity.ok("SUCCESS");
	}
}//end
