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
public class InvoicedetailResponse  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer  deId;
	private String deStartdate;
	private String deEnddate;
	private Integer deWaNew;
	private Integer deLiNew;
	private Integer deTotalunitLi;
	private Integer deTotalunitWa;
	private String deTotalLi;
	private String deTotalWa;
	private String deTotal;
	private String deUnpaid;
	private String rentId;
	private RentResponse rent;
	
	
}
