package com.it.Entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_invoicedetail")
public class InvoicedetailEntity {

	@Id
	private Integer deId;
	private String deStartdate;
	private String deEnddate;
	private Integer deWaNew;
	private Integer deLiNew;
	private Integer deTotalunitLi;
	private Integer deTotalunitWa;
	private String deTotalLi;
	private String deTotalWa;
	private String deTotal;
	private String rentId;
	private String InvoiceId;
	public Integer getDeId() {
		return deId;
	}
	public void setDeId(Integer deId) {
		this.deId = deId;
	}
	public String getDeStartdate() {
		return deStartdate;
	}
	public void setDeStartdate(String deStartdate) {
		this.deStartdate = deStartdate;
	}
	public String getDeEnddate() {
		return deEnddate;
	}
	public void setDeEnddate(String deEnddate) {
		this.deEnddate = deEnddate;
	}
	public Integer getDeWaNew() {
		return deWaNew;
	}
	public void setDeWaNew(Integer deWaNew) {
		this.deWaNew = deWaNew;
	}
	public Integer getDeLiNew() {
		return deLiNew;
	}
	public void setDeLiNew(Integer deLiNew) {
		this.deLiNew = deLiNew;
	}
	public Integer getDeTotalunitLi() {
		return deTotalunitLi;
	}
	public void setDeTotalunitLi(Integer deTotalunitLi) {
		this.deTotalunitLi = deTotalunitLi;
	}
	public Integer getDeTotalunitWa() {
		return deTotalunitWa;
	}
	public void setDeTotalunitWa(Integer deTotalunitWa) {
		this.deTotalunitWa = deTotalunitWa;
	}
	public String getDeTotalLi() {
		return deTotalLi;
	}
	public void setDeTotalLi(String deTotalLi) {
		this.deTotalLi = deTotalLi;
	}
	public String getDeTotalWa() {
		return deTotalWa;
	}
	public void setDeTotalWa(String deTotalWa) {
		this.deTotalWa = deTotalWa;
	}
	public String getDeTotal() {
		return deTotal;
	}
	public void setDeTotal(String deTotal) {
		this.deTotal = deTotal;
	}
	public String getRentId() {
		return rentId;
	}
	public void setRentId(String rentId) {
		this.rentId = rentId;
	}
	public String getInvoiceId() {
		return InvoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		InvoiceId = invoiceId;
	}
	
}



