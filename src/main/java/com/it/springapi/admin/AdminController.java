package com.it.springapi.admin;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	//Get  All admin
	@GetMapping()
	public List<Admin> getAll() {
		
		
		return adminService.getAll();
	}
	
	// create user Admin
			@PostMapping()
		    public ResponseEntity<?> postUserAdmin(@RequestBody Admin body) {
		        Admin customer = adminService.createUserAdmin(body);
		        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
		    }
			//Get By Id
			@GetMapping("/{id}")
			public Admin getUserById(@PathVariable Long id) {
				
				Admin customer = adminService.getByid(id);
					return customer;
			}		
			//Get By Name
			   @GetMapping(params = "name")
			    public List<Admin> getCustomer(@RequestParam(value="name") String name) {
			        return adminService.getByname(name);
			    }
		

	//update user Admin
			@PutMapping("/update/{id}")
			public ResponseEntity<?> putUserAdmin(@PathVariable Long id ,  @RequestBody Admin body){
			    Optional customer = adminService.updateUserAdmin(id, body);
		        if(!customer.isPresent()) {
		            return ResponseEntity.notFound().build();
		        }
		        return ResponseEntity.ok().build();
			}
	//delete
			   @DeleteMapping("/delete/{id}")
			    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
			        if(!adminService.deleteUserAdmin(id)) {
			            return ResponseEntity.notFound().build();
			        }
			        return ResponseEntity.ok().build();
			    }
	
}