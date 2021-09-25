package com.it.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

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
	
	private Integer deId;
	private String deWaold;
	private String deLiold;
	private Integer deWaNew;
	private Integer deLiNew;
	private Integer totalunitLi;
	private Integer totalunitWa;
	private String totalRoom;
	private String totalLi;
	private String totalWa;
	private String deTotal;
	private String InStart;
	private String InEnd;
	private String InId;
	private InvoiceResponse invoice;
}
