package com.it.Entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_invoicedetail")
public class InvoicedetailEntity {

	@Id
	private Integer  deId;
	private Date deStartdate;
	private Date deEnddate;
	private Integer deWaOld;
	private Integer deWaNew;
	private Integer deLiOld;
	private Integer deLiNew;
	private Integer deTotalunitLi;
	private Integer deTotalunitWa;
	private String deTotalroom;
	private String deTotalLi;
	private String deTotalWa;
	private String deTotal;
	private String deUnpaid;
	private String rentId;
	public Integer getDeId() {
		return deId;
	}
	public void setDeId(Integer deId) {
		this.deId = deId;
	}
	public Date getDeStartdate() {
		return deStartdate;
	}
	public void setDeStartdate(Date deStartdate) {
		this.deStartdate = deStartdate;
	}
	public Date getDeEnddate() {
		return deEnddate;
	}
	public void setDeEnddate(Date deEnddate) {
		this.deEnddate = deEnddate;
	}
	public Integer getDeWaOld() {
		return deWaOld;
	}
	public void setDeWaOld(Integer deWaOld) {
		this.deWaOld = deWaOld;
	}
	public Integer getDeWaNew() {
		return deWaNew;
	}
	public void setDeWaNew(Integer deWaNew) {
		this.deWaNew = deWaNew;
	}
	public Integer getDeLiOld() {
		return deLiOld;
	}
	public void setDeLiOld(Integer deLiOld) {
		this.deLiOld = deLiOld;
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
	public String getDeTotalroom() {
		return deTotalroom;
	}
	public void setDeTotalroom(String deTotalroom) {
		this.deTotalroom = deTotalroom;
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
	public String getDeUnpaid() {
		return deUnpaid;
	}
	public void setDeUnpaid(String deUnpaid) {
		this.deUnpaid = deUnpaid;
	}
	public String getRentId() {
		return rentId;
	}
	public void setRentId(String rentId) {
		this.rentId = rentId;
	}
	
}



