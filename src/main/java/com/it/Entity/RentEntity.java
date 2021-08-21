package com.it.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_rent")
public class RentEntity {

	@Id
	private Integer rentId;
	private String rentStart;
	private String rentEnd;
	private Integer rentInsurance;
	private String rentTotalprice;
	private String rentOther;
	private String userId;
	private String roomId;
	public Integer getRentId() {
		return rentId;
	}
	public void setRentId(Integer rentId) {
		this.rentId = rentId;
	}
	public String getRentStart() {
		return rentStart;
	}
	public void setRentStart(String rentStart) {
		this.rentStart = rentStart;
	}
	public String getRentEnd() {
		return rentEnd;
	}
	public void setRentEnd(String rentEnd) {
		this.rentEnd = rentEnd;
	}
	public Integer getRentInsurance() {
		return rentInsurance;
	}
	public void setRentInsurance(Integer rentInsurance) {
		this.rentInsurance = rentInsurance;
	}
	public String getRentTotalprice() {
		return rentTotalprice;
	}
	public void setRentTotalprice(String rentTotalprice) {
		this.rentTotalprice = rentTotalprice;
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
