package com.it.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_amphur")
public class AmphurEntity {

	@Id
	private Integer AmphurId;
	private String AmphurCode;
	private String AmphurNameTh;
	private String AmphurNameEn;
	private Integer ProvinceId;
	public Integer getAmphurId() {
		return AmphurId;
	}
	public void setAmphurId(Integer amphurId) {
		AmphurId = amphurId;
	}
	public String getAmphurCode() {
		return AmphurCode;
	}
	public void setAmphurCode(String amphurCode) {
		AmphurCode = amphurCode;
	}
	public String getAmphurNameTh() {
		return AmphurNameTh;
	}
	public void setAmphurNameTh(String amphurNameTh) {
		AmphurNameTh = amphurNameTh;
	}
	public String getAmphurNameEn() {
		return AmphurNameEn;
	}
	public void setAmphurNameEn(String amphurNameEn) {
		AmphurNameEn = amphurNameEn;
	}
	public Integer getProvinceId() {
		return ProvinceId;
	}
	public void setProvinceId(Integer provinceId) {
		ProvinceId = provinceId;
	}
	
}
