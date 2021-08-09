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
public class DistrictResponse  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer  DistrictId;
	private String zipCode;
	private String DistrictNameTh;
	private String DistrictNameEn;
	private Integer AmphurId;
	private AmphurResponse amphur;
	private ProvinceResponse province;
}
