package com.it.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_province")
public class ProvinceEntity {

	@Id
	private Integer  ProvinceId;
	private String ProvinceCode;
	private String ProvinceNameTh;
	private String ProvinceNameEn;
	private String Area;
	public Integer getProvinceId() {
		return ProvinceId;
	}
	public void setProvinceId(Integer provinceId) {
		ProvinceId = provinceId;
	}
	public String getProvinceCode() {
		return ProvinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		ProvinceCode = provinceCode;
	}
	public String getProvinceNameTh() {
		return ProvinceNameTh;
	}
	public void setProvinceNameTh(String provinceNameTh) {
		ProvinceNameTh = provinceNameTh;
	}
	public String getProvinceNameEn() {
		return ProvinceNameEn;
	}
	public void setProvinceNameEn(String provinceNameEn) {
		ProvinceNameEn = provinceNameEn;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	

}
