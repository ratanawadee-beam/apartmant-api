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
	
	private Integer  InvoiceId;
	private String InvoiceStetus;
	private String InvoiceNote;
	private Date InvoiceStart;
	private Date InvoiceEnd;
	private String rentId;
	private String roomId;
	private String userId;
	private RentResponse rent;
	private RoomResponse room;
	private UserResponse user;

}
