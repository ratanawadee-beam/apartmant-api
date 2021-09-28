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
	private String deWaold;
	private String deLiold;
	private Integer deWanew;
	private Integer deLinew;
	private Integer totalunitLi;
	private Integer totalunitWa;
	private String totalRoom;
	private String totalLi;
	private String totalWa;
	private String deTotal;
	private String InStart;
	private String InEnd;
	private Integer rentId;
	public Integer getDeId() {
		return deId;
	}
	public void setDeId(Integer deId) {
		this.deId = deId;
	}
	public String getDeWaold() {
		return deWaold;
	}
	public void setDeWaold(String deWaold) {
		this.deWaold = deWaold;
	}
	public String getDeLiold() {
		return deLiold;
	}
	public void setDeLiold(String deLiold) {
		this.deLiold = deLiold;
	}
	public Integer getDeWanew() {
		return deWanew;
	}
	public void setDeWanew(Integer deWanew) {
		this.deWanew = deWanew;
	}
	public Integer getDeLinew() {
		return deLinew;
	}
	public void setDeLinew(Integer deLinew) {
		this.deLinew = deLinew;
	}
	public Integer getTotalunitLi() {
		return totalunitLi;
	}
	public void setTotalunitLi(Integer totalunitLi) {
		this.totalunitLi = totalunitLi;
	}
	public Integer getTotalunitWa() {
		return totalunitWa;
	}
	public void setTotalunitWa(Integer totalunitWa) {
		this.totalunitWa = totalunitWa;
	}
	public String getTotalRoom() {
		return totalRoom;
	}
	public void setTotalRoom(String totalRoom) {
		this.totalRoom = totalRoom;
	}
	public String getTotalLi() {
		return totalLi;
	}
	public void setTotalLi(String totalLi) {
		this.totalLi = totalLi;
	}
	public String getTotalWa() {
		return totalWa;
	}
	public void setTotalWa(String totalWa) {
		this.totalWa = totalWa;
	}
	public String getDeTotal() {
		return deTotal;
	}
	public void setDeTotal(String deTotal) {
		this.deTotal = deTotal;
	}
	public String getInStart() {
		return InStart;
	}
	public void setInStart(String inStart) {
		InStart = inStart;
	}
	public String getInEnd() {
		return InEnd;
	}
	public void setInEnd(String inEnd) {
		InEnd = inEnd;
	}
	public Integer getRentId() {
		return rentId;
	}
	public void setRentId(Integer rentId) {
		this.rentId = rentId;
	}

	
}



