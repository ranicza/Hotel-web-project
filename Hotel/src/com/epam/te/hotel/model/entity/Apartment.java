package com.epam.te.hotel.model.entity;

import com.epam.te.hotel.model.entity.enumeration.ApartmentCategory;

public class Apartment {

	private int id;
	
	private int roomNumber;
	
	//Amount of places for sleep
	private int positions;	
	
	//Category of the room 
	private ApartmentCategory category;
	
	//The cost per day
	private int cost;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getRoomNumber() {
		return this.roomNumber;
	}
	
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public int getPositions() {
		return positions;
	}
	
	public void setPositions(int positions) {
		this.positions = positions;
	}
	
	public ApartmentCategory getCategory() {
		return category;
	}
	
	public void setCategory(ApartmentCategory category) {
		this.category = category;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + cost;
		result = prime * result + id;
		result = prime * result + positions;
		result = prime * result + roomNumber;
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
		Apartment other = (Apartment) obj;
		if (category != other.category)
			return false;
		if (cost != other.cost)
			return false;
		if (id != other.id)
			return false;
		if (positions != other.positions)
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		return true;
	}
	
	
}
