package com.it.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_rent")
public class RentEntity {

	@Id
	private Integer rentId;
	private Date rentStart;
	private Date rentEnd;
	private Integer rentInsurance;
	private String rentWa;
	private String rentTotalprice;
	private String rentLi;
	private String rentOther;
	private String userId;
	private String roomId;
	public Integer getRentId() {
		return rentId;
	}
	public void setRentId(Integer rentId) {
		this.rentId = rentId;
	}
	public Date getRentStart() {
		return rentStart;
	}
	public void setRentStart(Date rentStart) {
		this.rentStart = rentStart;
	}
	public Date getRentEnd() {
		return rentEnd;
	}
	public void setRentEnd(Date rentEnd) {
		this.rentEnd = rentEnd;
	}
	public Integer getRentInsurance() {
		return rentInsurance;
	}
	public void setRentInsurance(Integer rentInsurance) {
		this.rentInsurance = rentInsurance;
	}
	public String getRentWa() {
		return rentWa;
	}
	public void setRentWa(String rentWa) {
		this.rentWa = rentWa;
	}
	public String getRentTotalprice() {
		return rentTotalprice;
	}
	public void setRentTotalprice(String rentTotalprice) {
		this.rentTotalprice = rentTotalprice;
	}
	public String getRentLi() {
		return rentLi;
	}
	public void setRentLi(String rentLi) {
		this.rentLi = rentLi;
	}
	public String getRentOther() {
		return rentOther;
	}
	public void setRentOther(String rentOther) {
		this.rentOther = rentOther;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	

}
