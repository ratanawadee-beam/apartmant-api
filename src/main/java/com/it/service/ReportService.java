package com.it.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.it.Controller.RentController;
import com.it.Entity.RentEntity;
import com.it.Repository.RentRepository;
import com.it.model.RentResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ReportService{

	private static final Logger log = LoggerFactory.getLogger(ReportService.class);
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private RentRepository rentRepository;
	
	@Autowired
	private RentController rentController;

	public ByteArrayOutputStream generateReport() throws IOException {
		log.info("generateReport : Start");
		ByteArrayOutputStream out = null;
		try {
			ClassPathResource reportFile = new ClassPathResource("report/Test.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getInputStream());

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("TEST", "REPORT TEST");

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
			out = new ByteArrayOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint, out);

		} catch (Exception e) {
			log.error("generateReport Error : {} ", e);
		}
		log.info("generateReport : End");
		return out;
	}

	public ByteArrayOutputStream generateBilldrugReport(Integer rentId) throws IOException {
		log.info("generateBilldrugReport : Start :: rentId : " + rentId);
		ByteArrayOutputStream out = null;
		try {
			
			Optional<RentEntity> entity = rentRepository.findById(rentId);
			if (entity.isPresent()) {
				RentResponse rent = rentController.convertToResponse(entity.get());
				ClassPathResource reportFile = new ClassPathResource("report/Report.jasper");
				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getInputStream());

				//parameter
				Map<String, Object> parameters = new HashMap<>();
				parameters.put("userTitle", rent.getUser().getUserTitle());
				parameters.put("userName", rent.getUser().getUserName());
				parameters.put("userLasname", rent.getUser().getUserLasname());
				parameters.put("userIdcard", rent.getUser().getUserIdcard());
				parameters.put("userGender", rent.getUser().getUserGender());
				parameters.put("userBirthday", rent.getUser().getUserBirthday());
				parameters.put("userPhone", rent.getUser().getUserPhone());
				parameters.put("userEmail", rent.getUser().getUserEmail());
				parameters.put("rentStart", rent.getRentStart());
				parameters.put("rentEnd", rent.getRentEnd());
				parameters.put("roomId", rent.getRoomId());
				parameters.put("roomTypename", rent.getRoom().getRoomTypename());
				parameters.put("roomPrice", rent.getRoom().getRoomPrice().toString());
				parameters.put("rentOther", rent.getRentOther());
				parameters.put("rentInsurance", rent.getRentInsurance().toString());
				parameters.put("rentLi", rent.getRentLi());
				parameters.put("rentWa", rent.getRentWa());
				parameters.put("rentTotalprice", rent.getRentTotalprice());
				//list bean
			//	JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource();
				
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
				out = new ByteArrayOutputStream();

				JasperExportManager.exportReportToPdfStream(jasperPrint, out);
			}

		} catch (Exception e) {
			log.error("generateBilldrugReport Error : {} ", e);
		}
		log.info("generateBilldrugReport : End");

//		try(OutputStream outputStream = new FileOutputStream("D:\\File-report\\test.pdf")) {
//		    out.writeTo(outputStream);
//		}
		return out;
	}
	
	
//	private BilldrugResponse convertToResponse(BilldrugEntity entity) {
//		BilldrugResponse response = modelMapper.map(entity, BilldrugResponse.class);
//		
//		//set Treatment
//		Optional<TreatmentEntity> treatEntity = treatmentRepository.findById(entity.getTmId());
//		if (treatEntity.isPresent()) {
//			TreatmentResponse treatmentResponse = modelMapper.map(treatEntity.get(), TreatmentResponse.class);
//			Optional<UserEntity> userEntity = userRepository.findById(Integer.parseInt(treatEntity.get().getUserId()));
//			if (userEntity.isPresent()) {
//				treatmentResponse.setUser(modelMapper.map(userEntity.get(), UserResponse.class));
//			}
//			response.setTreatment(treatmentResponse);
//		}
//		
//		return response;
//	}
	
	private Integer getInt(String str) {
		if(StringUtils.isNoneBlank(str)) {
			return new Integer(str);
		}
		return 0;
	}

}
