package model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;

public class Name implements Serializable {
	
	
	// change to "nullable = false" later..
	@Column(name = "fullName", unique = false, nullable = false)
	private String fullName;
	
	@Column(name = "firstName", unique = false, nullable = false)
	private String firstName;
	
	@Column(name = "middleName", unique = false, nullable = true)
	private String middleName;
	
	@Column(name = "lastName", unique = false, nullable = false)
	private String lastName;

	public Name(String firstName, String middleName, String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	
	public Name(){
		
		this.firstName = "";
		this.middleName = "";
		this.lastName = "";		
	}

	@Access(AccessType.FIELD)
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Access(AccessType.FIELD)
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	@Access(AccessType.FIELD)
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Access(AccessType.FIELD)
	public String getFullNameString() {
		if (middleName.equals(null)) {
			fullName = firstName + " " + lastName;
		} else {
			fullName = firstName + " " + middleName + " " + lastName;
		}
		return fullName;
	}
	

	
//	@Override
//	public String toString() {
//		return firstName + "" + middleName + "" + lastName;
//	}
	
	
	
	
}
