package com.temula.representationalstate;

import com.temula.RepresentationalState;

public class PersonState implements RepresentationalState {
	String state;
	public String toString() {
		return this.state;
	}
	public PersonState(String state){
		this.state=state;
	}
	
}
