package com.it.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_room")
public class RoomEntity {

	@Id
	private String roomId;
	private String roomTypename;
	private Integer roomPrice;
	private String roomLight;
	private String roomWater;
	private String roomStatus;
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomTypename() {
		return roomTypename;
	}
	public void setRoomTypename(String roomTypename) {
		this.roomTypename = roomTypename;
	}
	public Integer getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(Integer roomPrice) {
		this.roomPrice = roomPrice;
	}
	public String getRoomLight() {
		return roomLight;
	}
	public void setRoomLight(String roomLight) {
		this.roomLight = roomLight;
	}
	public String getRoomWater() {
		return roomWater;
	}
	public void setRoomWater(String roomWater) {
		this.roomWater = roomWater;
	}
	public String getRoomStatus() {
		return roomStatus;
	}
	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	
}
