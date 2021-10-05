package com.it.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.Entity.InvoiceEntity;
import com.it.Entity.RentEntity;
import com.it.Entity.UserEntity;
import com.it.Repository.InvoiceRepository;
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

	@Autowired
	private InvoiceRepository invoiceRepository;

	public void sendEmailRegister(Integer rentId) {
		Optional<RentEntity> entity = rentRepository.findById(rentId);
		try {
			ByteArrayOutputStream out = reportService.generateBilldrugReport(rentId);
			Path tempFile = Files.createTempFile("Report", ".pdf");
			out.toByteArray();
			if (Files.exists(tempFile)) {
				Files.delete(tempFile);

				Files.write(tempFile, out.toByteArray());
			} else {
				Files.write(tempFile, out.toByteArray());
			}

			Optional<UserEntity> opUser = userRepository.findById(entity.get().getUserId());
			UserEntity user = opUser.get();
			String subject = "หนังสือสัญญาอพาร์ตเมนต์";

			StringBuilder text = new StringBuilder();
			text.append("UserName : " + user.getUserUsername());

			text.append("PassWord : " + user.getUserPhone());
			sendEmailUtils.sendMail(user.getUserEmail(), subject, text.toString(), tempFile.toFile());
			Files.delete(tempFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendEmailPayment(Integer inId) {
		try {
			ByteArrayOutputStream out = reportService.generateBillPaymentReport(inId);
			Path tempFile = Files.createTempFile("Report", ".pdf");
			out.toByteArray();
			if (Files.exists(tempFile)) {
				Files.delete(tempFile);

				Files.write(tempFile, out.toByteArray());
			} else {
				Files.write(tempFile, out.toByteArray());
			}

			Optional<InvoiceEntity> invoiceOptional = invoiceRepository.findById(inId);
			InvoiceEntity invoice = invoiceOptional.get();

			Optional<UserEntity> opUser = userRepository.findById(invoice.getUserId());
			UserEntity user = opUser.get();
			String subject = "แจ้งหนี้";

			StringBuilder text = new StringBuilder();
			text.append("แจ้งหนี้");

			sendEmailUtils.sendMail(user.getUserEmail(), subject, text.toString(), tempFile.toFile());
			Files.delete(tempFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
