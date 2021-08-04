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
public class ProvinceResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer  ProvinceId;
	private String ProvinceCode;
	private String ProvinceNameTh;
	private String ProvinceNameEn;
	private String Area;
}
