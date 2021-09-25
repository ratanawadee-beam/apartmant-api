package com.it.Entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name="tb_invoice")
	
public class InvoiceEntity {

	@Id
	private Integer  InId;
	private String InStetus;
	private String InStart;
	private String InEnd;
	private String rentId;
	public Integer getInId() {
		return InId;
	}
	public void setInId(Integer inId) {
		InId = inId;
	}
	public String getInStetus() {
		return InStetus;
	}
	public void setInStetus(String inStetus) {
		InStetus = inStetus;
	}
	public String getInStart() {
		return InStart;
	}
	public void setInStart(String inStart) {
		InStart = inStart;
	}
	public String getInEnd() {
		return InEnd;
	}
	public void setInEnd(String inEnd) {
		InEnd = inEnd;
	}
	public String getRentId() {
		return rentId;
	}
	public void setRentId(String rentId) {
		this.rentId = rentId;
	}
	
}
