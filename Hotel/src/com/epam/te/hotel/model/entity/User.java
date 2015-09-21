package com.epam.te.hotel.model.entity;

public class User {
	
	//User's identifier
	private int id;
	
	private String login;

	private int password;

	private String email;
	
	private String name;           
	
	private String surname;  
	
	private String patronymic; 
	
	//Number & serial of the user's passport 
	private String passport;		
	
	//User's role (access level)
    private Role role;	
    
    private int idRole;

    public User() {}    

	public int getId() {
		return id;
	}
	

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

	public String getEmail() {
		return email;
	}
    

    public void setEmail(String email) {
        this.email = email;
    }

	 public String getName() {
	     return name;
	 }
	  
     public void setName(String name){
		 this.name = name;
	 }

	 public String getSurname() {
	     return surname;
	 } 

	 public void setSurname(String surname){
		 this.surname = surname;
	 }

	 public String getPatronymic() {
	     return patronymic;
	 } 

	 public void setPatronymic(String patronymic){
		 this.patronymic = patronymic;
	 }

	 public String getPassport() {
	     return passport;
	 }

	 public void setPassport(String passport){
		 this.passport = passport;
	 }

	 public Role getRole() {
	     return  role;
	 }
	 
	public void setRole(Role role){
		this.role = role;
	}

 	public int getIdRole() {
		return idRole;
	}

    public void setIdRole(int id) {
        this.idRole = id;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + idRole;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((passport == null) ? 0 : passport.hashCode());
		result = prime * result + password;
		result = prime * result + ((patronymic == null) ? 0 : patronymic.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (idRole != other.idRole)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (passport == null) {
			if (other.passport != null)
				return false;
		} else if (!passport.equals(other.passport))
			return false;
		if (password != other.password)
			return false;
		if (patronymic == null) {
			if (other.patronymic != null)
				return false;
		} else if (!patronymic.equals(other.patronymic))
			return false;
		if (role != other.role)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
    
    
}
