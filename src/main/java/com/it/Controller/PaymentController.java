package com.it.Controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.it.Entity.InvoiceEntity;
import com.it.Entity.PaymentEntity;
import com.it.Entity.RentEntity;
import com.it.Entity.RoomEntity;
import com.it.Entity.UserEntity;
import com.it.Repository.InvoiceRepository;
import com.it.Repository.PaymentRepository;
import com.it.Repository.RentRepository;
import com.it.Repository.RoomRepository;
import com.it.Repository.UserRepository;
import com.it.model.InvoiceResponse;
import com.it.model.PaymentResponse;
import com.it.model.RentResponse;
import com.it.model.RoomResponse;
import com.it.model.UserResponse;
import com.it.service.SendEmailService;
import com.it.utils.ReportUtils;

@RestController
public class PaymentController {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private SendEmailService sendEmailService;

	@Value("${file.receipt.path}")
	private String FILE_RECEIPT_PATH;

	@Autowired
	private RentRepository rentRepository;

	private PaymentResponse convertToResponse(PaymentEntity entity) {
		PaymentResponse response = new PaymentResponse();
		if (ObjectUtils.isNotEmpty(entity)) {
			response = modelMapper.map(entity, PaymentResponse.class);

			// set invoice
			Optional<InvoiceEntity> invoiceEntity = invoiceRepository.findById(Integer.valueOf(entity.getInId()));
			if (invoiceEntity.isPresent()) {
				response.setInvoice(modelMapper.map(invoiceEntity.get(), InvoiceResponse.class));

				// set rent
				Optional<RentEntity> rentEntity = rentRepository.findById(invoiceEntity.get().getRentId());
				if (rentEntity.isPresent()) {

					response.getInvoice().setRent(modelMapper.map(rentEntity.get(), RentResponse.class));
					RentResponse rent = response.getInvoice().getRent();
					// set user
					Optional<UserEntity> userEntity = userRepository.findById(invoiceEntity.get().getUserId());
					if (userEntity.isPresent()) {
						rent.setUser(modelMapper.map(userEntity.get(), UserResponse.class));
					}

					// set room
					Optional<RoomEntity> roomEntity = roomRepository.findById(invoiceEntity.get().getRoomId());
					if (roomEntity.isPresent()) {
						rent.setRoom(modelMapper.map(roomEntity.get(), RoomResponse.class));
					}
				}

			}

		}
		return response;
	}

	@GetMapping("/payments")
	public ResponseEntity<List<PaymentResponse>> getAllPayment() {
		List<PaymentEntity> entities = paymentRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/payment/{payId}")
	public ResponseEntity<PaymentResponse> getPaymentBypayId(@PathVariable("payId") Integer payId) {
		Optional<PaymentEntity> entity = paymentRepository.findById(payId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(this.convertToResponse(entity.get()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}

	@PostMapping("/payment/save")
	public ResponseEntity<PaymentEntity> savePayment(@RequestBody PaymentEntity request) {
		if (request != null) {
			PaymentEntity entity = new PaymentEntity();
			entity.setPayId(request.getPayId());
			entity.setPayDate(request.getPayDate());
			entity.setPayTotal(request.getPayTotal());
			entity.setInId(request.getInId());
			paymentRepository.save(entity);

			sendEmailService.sendEmailPayment(request.getInId());

			return ResponseEntity.ok(entity);

		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}

	@PostMapping("/payment/update")
	public ResponseEntity<PaymentEntity> updatePayment(@RequestBody PaymentEntity request) {
		if (request.getPayId() != null) {
			Optional<PaymentEntity> entity = paymentRepository.findById(request.getPayId());
			if (entity.isPresent()) {
				// set update data form request
				PaymentEntity updateEntity = entity.get();
				updateEntity.setPayTotal(request.getPayTotal());
				updateEntity.setInId(request.getInId());
				updateEntity.setPayDate(request.getPayDate());
				return ResponseEntity.ok(paymentRepository.save(updateEntity));
			} else {
				return ResponseEntity.badRequest().body(null);
			}

		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}

	@PostMapping("/uploadFile")
	public Object uploadFile(@RequestParam("multipartFile") MultipartFile multipartFile,
			@RequestParam("inId") Integer inId) {
		Format formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = formatter.format(new Date());
		String fileName = String.valueOf(inId) + "_" + s + "." + multipartFile.getOriginalFilename().split("\\.")[1];
		File file = new File(FILE_RECEIPT_PATH + fileName);

		try (OutputStream os = new FileOutputStream(file)) {
			os.write(multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		Optional<InvoiceEntity> entity = invoiceRepository.findById(inId);
		if (entity.isPresent()) {
			// set update data form request
			InvoiceEntity updateEntity = entity.get();
			updateEntity.setInStatus("2");
			invoiceRepository.save(updateEntity);

			List<PaymentEntity> opPayment = paymentRepository.findByInId(inId);
			if (null != opPayment) {
				PaymentEntity payment = opPayment.get(0);
				payment.setFileName(fileName);
				paymentRepository.save(payment);
			}
		}

		return "SUCCESS";
	}

	@GetMapping(path = "/downLoadFile")
	public ResponseEntity<InputStreamResource> downLoadFile(@RequestParam("inId") Integer inId) throws IOException {
		ResponseEntity<InputStreamResource> response = null;
		List<PaymentEntity> opPayment = paymentRepository.findByInId(inId);
		if (null != opPayment) {
			PaymentEntity payment = opPayment.get(0);
			byte[] array = Files.readAllBytes(Paths.get(FILE_RECEIPT_PATH + payment.getFileName()));
			response = new ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(array)),
					ReportUtils.createResponseHeader(MediaType.TEXT_PLAIN, "payment.getFileName()", null), HttpStatus.OK);
		}

		return response;
	}
}
