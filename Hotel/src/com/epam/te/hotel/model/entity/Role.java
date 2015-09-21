package com.epam.te.hotel.model.entity;

/**
 * User's role (access level).
 */
public enum Role {

	ADMIN(1),
	CLIENT(2),
	GUEST(3);
	
	private int id;
	
	private Role(int id){
		this.id= id;
	}
	
	/**
	 * gets user's role (access level) by his id.
	 * @param id
	 * @return user's role
	 */
	public static Role getRoleById(int id){
		for (Role r : Role.values()){
			if (r.getId() == id ) return r;
		}
		return null;
	}
	
	public int getId() {
		return id;
	}

	/**
	 * Gets user's id by his role.
	 * @return id
	 */
	public int getIdByRole(){
		return getId();
	}
	
	

}
