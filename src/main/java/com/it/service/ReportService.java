package com.it.service;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.it.Controller.RentController;
import com.it.Entity.InvoiceEntity;
import com.it.Entity.InvoicedetailEntity;
import com.it.Entity.RentEntity;
import com.it.Repository.InvoiceRepository;
import com.it.Repository.InvoicedetailRepository;
import com.it.Repository.RentRepository;
import com.it.model.InvoiceResponse;
import com.it.model.RentResponse;
import com.it.utils.QRPromptPayUtils;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ReportService {

	private static final Logger log = LoggerFactory.getLogger(ReportService.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RentRepository rentRepository;

	@Autowired
	private RentController rentController;

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private InvoicedetailRepository invoicedetailRepository;
	
	@Autowired
	private QRPromptPayUtils qrPromptPayUtils;

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

				// parameter
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
				// list bean
				// JRBeanCollectionDataSource beanColDataSource = new
				// JRBeanCollectionDataSource();

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

	public ByteArrayOutputStream generateBillPaymentReport(Integer InId) throws IOException {
		log.info("generateBillPaymentReport : Start :: inId : " + InId);
		ByteArrayOutputStream out = null;
		try {
			Optional<InvoiceEntity> invoiceOptional = invoiceRepository.findById(InId);
			InvoiceEntity invoice = invoiceOptional.get();
			
			Optional<InvoicedetailEntity> invoiceDetailOptional = invoicedetailRepository.findByInId(InId);
			InvoicedetailEntity invoiceDetail = invoiceDetailOptional.get();
			
			Optional<RentEntity> entity = rentRepository.findById(invoice.getRentId());
			if (entity.isPresent()) {
				RentResponse rent = rentController.convertToResponse(entity.get());

				ClassPathResource reportFile = new ClassPathResource("report/bill.jasper");
				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getInputStream());

				// parameter
				Map<String, Object> parameters = new HashMap<>();
				parameters.put("roomId", rent.getRoom().getRoomId());
				parameters.put("roomTypename", rent.getRoom().getRoomTypename());
				parameters.put("userName", rent.getUser().getUserName());
				parameters.put("userLasname", rent.getUser().getUserLasname());
				parameters.put("inStart", invoice.getInStart());
				parameters.put("inEnd", invoice.getInEnd());
				parameters.put("inId", invoice.getInId().toString());
				parameters.put("roomLight", rent.getRoom().getRoomLight());
				parameters.put("deLinew", invoiceDetail.getDeLinew().toString());
				parameters.put("totalunitLi", invoiceDetail.getTotalunitLi().toString());
				parameters.put("rentLi", rent.getRentLi());
				parameters.put("roomPrice", rent.getRoom().getRoomPrice().toString());
				parameters.put("totalLi", invoiceDetail.getTotalLi());
				parameters.put("totalWa", invoiceDetail.getTotalWa());
				parameters.put("deTotal", invoiceDetail.getDeTotal());
				
//				byte[] array = Files.readAllBytes(Paths.get("C:/Users/Beam/Desktop/QRCode.png"));
				
				byte[] array = qrPromptPayUtils.generateQRCodeToByteArray(new BigDecimal(invoiceDetail.getDeTotal()));
				parameters.put("imageQRCode", new ByteArrayInputStream(array));
				
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
				out = new ByteArrayOutputStream();

				JasperExportManager.exportReportToPdfStream(jasperPrint, out);
			}

		} catch (Exception e) {
			log.error("generateBillPaymentReport Error : {} ", e);
		}
		log.info("generateBillPaymentReport : End");

//		try(OutputStream outputStream = new FileOutputStream("D:\\File-report\\test.pdf")) {
//		    out.writeTo(outputStream);
//		}
		return out;
	}

//	private Integer getInt(String str) {
//		if (StringUtils.isNoneBlank(str)) {
//			return new Integer(str);
//		}
//		return 0;
//	}
	public byte[] extractBytes (String ImageName) throws IOException {
		 // open image
		 File imgPath = new File(ImageName);
		 BufferedImage bufferedImage = ImageIO.read(imgPath);

		 // get DataBufferBytes from Raster
		 WritableRaster raster = bufferedImage .getRaster();
		 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

		 return ( data.getData() );
		}
}
