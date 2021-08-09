package com.it.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

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
	private UserResponse user;
	private RoomResponse room;
	
	
	
}
