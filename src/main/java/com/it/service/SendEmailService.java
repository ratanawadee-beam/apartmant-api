package com.it.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.Entity.RentEntity;
import com.it.Entity.UserEntity;
import com.it.Repository.RentRepository;
import com.it.Repository.UserRepository;
import com.it.utils.SendEmailUtils;

@Service
public class SendEmailService {
	
	@Autowired
	private RentRepository rentRepository;
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SendEmailUtils sendEmailUtils;


	public void sendEmailRegister(Integer rentId) {
		Optional<RentEntity> entity = rentRepository.findById(rentId);
		try {
			ByteArrayOutputStream out = reportService.generateBilldrugReport(rentId);			
			Path tempFile = Files.createTempFile( "Report",".pdf");
			out.toByteArray();
			if(Files.exists(tempFile)) {
				Files.delete(tempFile);
				
				Files.write(tempFile, out.toByteArray());
			}else {
				Files.write(tempFile, out.toByteArray());
			}

			Optional<UserEntity> opUser = userRepository.findById(entity.get().getUserId());
			UserEntity user = opUser.get();
			String subject = "";
			
			StringBuilder text = new StringBuilder();
			text.append("UserName : " + user.getUserName());
			text.append("PassWord : " + user.getUserPhone());
			sendEmailUtils.sendMail(user.getUserEmail(), subject, text.toString(), tempFile.toFile());
			Files.delete(tempFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
