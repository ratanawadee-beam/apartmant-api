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
	private String InStetus;
	private String InStart;
	private String InEnd;
	private String rentId;
	private RentResponse rent;

}
