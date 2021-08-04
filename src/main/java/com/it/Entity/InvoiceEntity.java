package com.it.Entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name="tb_invoice")
	
public class InvoiceEntity {

	@Id
	private Integer  InvoiceId;
	private String InvoiceStetus;
	private String InvoiceNote;
	private Date InvoiceStart;
	private Date InvoiceEnd;
	private String rentId;
	private String roomId;
	private String userId;
	public Integer getInvoiceId() {
		return InvoiceId;
	}
	public void setInvoiceId(Integer invoiceId) {
		InvoiceId = invoiceId;
	}
	public String getInvoiceStetus() {
		return InvoiceStetus;
	}
	public void setInvoiceStetus(String invoiceStetus) {
		InvoiceStetus = invoiceStetus;
	}
	public String getInvoiceNote() {
		return InvoiceNote;
	}
	public void setInvoiceNote(String invoiceNote) {
		InvoiceNote = invoiceNote;
	}
	public Date getInvoiceStart() {
		return InvoiceStart;
	}
	public void setInvoiceStart(Date invoiceStart) {
		InvoiceStart = invoiceStart;
	}
	public Date getInvoiceEnd() {
		return InvoiceEnd;
	}
	public void setInvoiceEnd(Date invoiceEnd) {
		InvoiceEnd = invoiceEnd;
	}
	public String getRentId() {
		return rentId;
	}
	public void setRentId(String rentId) {
		this.rentId = rentId;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
