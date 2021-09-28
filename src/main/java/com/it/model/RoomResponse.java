package com.it.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String  roomId;
	private String roomTypename;
	private Integer roomPrice;
	private String  roomLight;
	private String roomWater;
	private String roomStatus;
	
	
	
}
