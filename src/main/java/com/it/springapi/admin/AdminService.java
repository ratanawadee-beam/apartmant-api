package com.it.springapi.admin;

import java.util.List;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;



@Service
public class AdminService {
	
	private AdminRepository adminRepository;
	
	@Autowired
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	public List<Admin> getAll() {
				
		return adminRepository.findAll();
	}
	public Admin createUserAdmin(Admin body) {
		body.setId(null);
		return adminRepository.save(body);
	}

	
	
	
	public boolean deleteUserAdmin(Long id) {
		// TODO Auto-generated method stub
		 try {
	            adminRepository.deleteById(id);
	            return true;
	        } catch (EmptyResultDataAccessException e) {
	            return false;
	        }

}
	public Optional updateUserAdmin(Long id, Admin customer) {
		// TODO Auto-generated method stub
		Optional<Admin> customerupdate = adminRepository.findById(id);
        if(!customerupdate.isPresent()) {
            return customerupdate;
        }
        
       customer.setId(id);
        return Optional.of(adminRepository.save(customer));
	}
	public Admin getByid(Long id) {	
			Optional<Admin> customer = adminRepository.findById(id);
			if (customer.isPresent()) {
				return new Admin(id, customer.get().getName(), customer.get().getLastname());
			}
			return null;
		}
	public List<Admin> getByname(String name) {
		// TODO Auto-generated method stub
		  return adminRepository.findByname(name);
	}
	
	

}