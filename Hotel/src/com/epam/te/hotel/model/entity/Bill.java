package com.epam.te.hotel.model.entity;

import java.util.Date;

import com.epam.te.hotel.model.entity.enumeration.BillStatus;

public class Bill {

	private int id;
	
	private BillStatus status;
	
	// The bill date
	private Date dateBill;
	
	private int totalPrice;
	
	private Order order;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public BillStatus getStatus() {
		return status;
	}
	
	public void setStatus(BillStatus status) {
		this.status = status;
	}
	
	public Date getDateBill() {
		return dateBill;
	}
	
	public void setDateBill(Date datebill) {
		this.dateBill= datebill;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateBill == null) ? 0 : dateBill.hashCode());
		result = prime * result + id;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + totalPrice;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bill other = (Bill) obj;
		if (dateBill == null) {
			if (other.dateBill != null)
				return false;
		} else if (!dateBill.equals(other.dateBill))
			return false;
		if (id != other.id)
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (status != other.status)
			return false;
		if (totalPrice != other.totalPrice)
			return false;
		return true;
	}
	
	
}
