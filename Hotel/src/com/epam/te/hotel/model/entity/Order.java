package com.epam.te.hotel.model.entity;

import java.util.Date;

import com.epam.te.hotel.model.entity.enumeration.OrderStatus;

public class Order {
	private int id;
	
	private Date dateArrival;
	
	private Date dateDeparture;
	
	private OrderStatus status;
	
	// The date order
	private Date dateOrder;
	
	private Apartment apartment;
	
	private User user;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDateArrival() {
		return dateArrival;
	}
	
	public void setDateArrival(Date dateArrival) {
		this.dateArrival = dateArrival;
	}
	
	public Date getDateDeparture() {
		return dateDeparture;
	}
	
	public void setDateDeparture(Date dateDeparture) {
		this.dateDeparture = dateDeparture;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public Date getDateOrder() {
		return dateOrder;
	}
	
	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}
	
	public Apartment getApartment() {
		return apartment;
	}
	
	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateArrival == null) ? 0 : dateArrival.hashCode());
		result = prime * result + ((dateDeparture == null) ? 0 : dateDeparture.hashCode());
		result = prime * result + ((dateOrder == null) ? 0 : dateOrder.hashCode());
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Order other = (Order) obj;
		if (dateArrival == null) {
			if (other.dateArrival != null)
				return false;
		} else if (!dateArrival.equals(other.dateArrival))
			return false;
		if (dateDeparture == null) {
			if (other.dateDeparture != null)
				return false;
		} else if (!dateDeparture.equals(other.dateDeparture))
			return false;
		if (dateOrder == null) {
			if (other.dateOrder != null)
				return false;
		} else if (!dateOrder.equals(other.dateOrder))
			return false;
		if (id != other.id)
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
}
