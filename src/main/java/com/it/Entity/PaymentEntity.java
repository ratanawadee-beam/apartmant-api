package com.it.Entity;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_payment")
public class PaymentEntity {

	@Id
	private Integer  payId;
	private String payDate;
	private String payTotal;
	private Integer InId;
	private String fileName;
	public Integer getPayId() {
		return payId;
	}
	public void setPayId(Integer payId) {
		this.payId = payId;
	}
	
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getPayTotal() {
		return payTotal;
	}
	public void setPayTotal(String payTotal) {
		this.payTotal = payTotal;
	}
	public Integer getInId() {
		return InId;
	}
	public void setInId(Integer inId) {
		InId = inId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
