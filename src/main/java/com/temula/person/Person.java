package com.temula.person;

public class Person {
	int personId;
	String firstName;
	String lastName;

	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String toString(){
		return "first name="+getFirstName()+"; last name="+getLastName()+"; person id="+getPersonId();
	}
	
}
