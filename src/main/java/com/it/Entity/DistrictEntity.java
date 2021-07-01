package com.it.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_district")
public class DistrictEntity {

	@Id
	private Integer  DistrictId;
	private String zipCode;
	private String DistrictNameTh;
	private String DistrictNameEn;
	private Integer AmphurId;
	public Integer getDistrictId() {
		return DistrictId;
	}
	public void setDistrictId(Integer districtId) {
		DistrictId = districtId;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getDistrictNameTh() {
		return DistrictNameTh;
	}
	public void setDistrictNameTh(String districtNameTh) {
		DistrictNameTh = districtNameTh;
	}
	public String getDistrictNameEn() {
		return DistrictNameEn;
	}
	public void setDistrictNameEn(String districtNameEn) {
		DistrictNameEn = districtNameEn;
	}
	public Integer getAmphurId() {
		return AmphurId;
	}
	public void setAmphurId(Integer amphurId) {
		AmphurId = amphurId;
	}
	
}
