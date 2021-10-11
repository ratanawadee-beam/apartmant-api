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

import com.it.Entity.ContactEntity;
import com.it.Entity.InvoiceEntity;
import com.it.Entity.PaymentEntity;
import com.it.Entity.RoomEntity;
import com.it.Repository.ContactRepository;
import com.it.utils.ReportUtils;

@RestController
public class ContactController {

	@Autowired
	private ContactRepository contactRepository;
	
	
	@Value("${file.receipt.path}")
	private String FILE_RECEIPT_PATH;
	
	@GetMapping("/contact")
	public ResponseEntity<List<ContactEntity>> getAllcontact(){
		return ResponseEntity.ok(contactRepository.findAll());
	}
	
	@GetMapping("/contact/{contactId}")
	public ResponseEntity<ContactEntity> getAllBycontactId(@PathVariable("contactId") Integer contactId){
		Optional<ContactEntity> entity = contactRepository.findById(contactId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
			
	}
	
	@PostMapping("/contact/save")
	public ResponseEntity<ContactEntity> saveContact(@RequestBody ContactEntity request){
		if (request != null)  {
			ContactEntity entity = new ContactEntity();
			
			entity.setConName(request.getConName());
			entity.setConLastname(request.getConLastname());
			entity.setConPhone(request.getConPhone());
			entity.setConCategory(request.getConCategory());
			entity.setConText(request.getConText());
			entity.setRoomId(request.getRoomId());
			entity.setUserId(request.getUserId());
			
			contactRepository.save(entity);
			return ResponseEntity.ok(entity);

		} else {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	
	@PostMapping("/contact/update")
	public ResponseEntity<ContactEntity> updateContact(@RequestBody ContactEntity request) {
		if (request.getConId() != null) {
			Optional<ContactEntity> entity = contactRepository.findById(request.getConId());
			if (entity.isPresent()) {
				//set update data form request
				ContactEntity updateEntity = entity.get();
				updateEntity.setConName(request.getConName());
				updateEntity.setConLastname(request.getConLastname());
				updateEntity.setConPhone(request.getConPhone());
				updateEntity.setConCategory(request.getConCategory());
				updateEntity.setConText(request.getConText());
				updateEntity.setRoomId(request.getRoomId());
				updateEntity.setUserId(request.getUserId());
				
				return ResponseEntity.ok(contactRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
			}else {
				return ResponseEntity.badRequest().body(null);
			}
			
		}


	@PostMapping("/uploadFiles")
	public Object uploadFile(@RequestParam("multipartFile") MultipartFile multipartFile,
			@RequestParam("conId") Integer conId) {
		Format formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = formatter.format(new Date());
		String conFilename = String.valueOf(conId) + "_" + s + "." + multipartFile.getOriginalFilename().split("\\.")[1];
		File file = new File(FILE_RECEIPT_PATH + conFilename);

		try (OutputStream os = new FileOutputStream(file)) {
			os.write(multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Optional<ContactEntity> entity = contactRepository.findById(conId);
		if (null != entity) {
			ContactEntity contact = entity.get();
			contact.setConFilename(conFilename);
			contactRepository.save(contact);
		}
		return "SUCCESS";
	}

	@GetMapping(path = "/downLoadFiles")
	public ResponseEntity<InputStreamResource> downLoadFile(@RequestParam("conId") Integer conId) throws IOException {
		ResponseEntity<InputStreamResource> response = null;
		Optional<ContactEntity> entity = contactRepository.findById(conId);
		if (null != entity) {
			ContactEntity contact = entity.get();
			byte[] array = Files.readAllBytes(Paths.get(FILE_RECEIPT_PATH + contact.getConFilename()));
			response = new ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(array)),
					ReportUtils.createResponseHeader(MediaType.TEXT_PLAIN, "contact.getConFilename()", null), HttpStatus.OK);
		}

		return response;
	}

	
}//end
