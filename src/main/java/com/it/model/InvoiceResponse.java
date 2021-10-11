package com.it.model;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer  InId;
	private String InStatus;
	private String InStart;
	private String InEnd;
	private Integer InTotal;
	private Integer rentId;
	private String roomId;
	private String userId;
	private RentResponse rent;
	private UserResponse user;
	private RoomResponse room;

}
