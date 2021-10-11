package com.it.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_contact")
public class ContactEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer conId;
	private String conName;
	private String conLastname;
	private String conPhone;
	private String conCategory;
	private String conText;
	private String conFilename;
	private String roomId;
	private String userId;
	public Integer getConId() {
		return conId;
	}
	public void setConId(Integer conId) {
		this.conId = conId;
	}
	public String getConName() {
		return conName;
	}
	public void setConName(String conName) {
		this.conName = conName;
	}
	public String getConLastname() {
		return conLastname;
	}
	public void setConLastname(String conLastname) {
		this.conLastname = conLastname;
	}
	public String getConPhone() {
		return conPhone;
	}
	public void setConPhone(String conPhone) {
		this.conPhone = conPhone;
	}
	public String getConCategory() {
		return conCategory;
	}
	public void setConCategory(String conCategory) {
		this.conCategory = conCategory;
	}
	public String getConText() {
		return conText;
	}
	public void setConText(String conText) {
		this.conText = conText;
	}
	public String getConFilename() {
		return conFilename;
	}
	public void setConFilename(String conFilename) {
		this.conFilename = conFilename;
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
