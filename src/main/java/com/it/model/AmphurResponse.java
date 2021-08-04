

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
public class AmphurResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer AmphurId;
	private String AmphurCode;
	private String AmphurNameTh;
	private String AmphurNameEn;
	private Integer ProvinceId;
}
