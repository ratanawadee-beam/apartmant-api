package com.it.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.service.SendEmailService;
import com.it.utils.SendEmailUtils;

@RestController
public class SendEmailController {
	private static final Logger log = LoggerFactory.getLogger(SendEmailController.class);
	
	@Autowired
	private SendEmailUtils sendEmailUtils;
	
	@Autowired
	private SendEmailService sendEmailService;
	
	@GetMapping("/email-send")
	public ResponseEntity<String> sendEmail(@RequestParam(required = true) String sendTo, @RequestParam(required = true) String subject, @RequestParam(required = false) String text) {
		sendEmailUtils.sendSimpleMessage(sendTo, subject, text);
		return ResponseEntity.ok("");
	}
	
	@GetMapping("/send-email/register")
	public ResponseEntity<String> sendEmailRegiser(@RequestParam(required = true) Integer rentId) {
		sendEmailService.sendEmailRegister(rentId);
		return ResponseEntity.ok("SUCCESS");
	}
	
	@GetMapping("/send-email/payment")
	public ResponseEntity<String> sendEmailPayment(@RequestParam(required = true) Integer inId) {
		log.info("sendEmailPayment : Start :: inId : " + inId);
		sendEmailService.sendEmailPayment(inId);
		return ResponseEntity.ok("SUCCESS");
	}

}
