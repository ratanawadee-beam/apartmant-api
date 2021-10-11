package com.it.Entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name="tb_invoice")
	
public class InvoiceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  InId;
	private String InStatus;
	private String InStart;
	private String InEnd;
	private Integer InTotal;
	private Integer rentId;
	private String roomId;
	private String userId;
	public Integer getInId() {
		return InId;
	}
	public void setInId(Integer inId) {
		InId = inId;
	}
	public String getInStatus() {
		return InStatus;
	}
	public void setInStatus(String inStatus) {
		InStatus = inStatus;
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
	public Integer getInTotal() {
		return InTotal;
	}
	public void setInTotal(Integer inTotal) {
		InTotal = inTotal;
	}
	public Integer getRentId() {
		return rentId;
	}
	public void setRentId(Integer rentId) {
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
