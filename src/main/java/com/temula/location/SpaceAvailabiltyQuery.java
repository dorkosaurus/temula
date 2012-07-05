package com.temula.location;

import java.util.Date;

public class SpaceAvailabiltyQuery {
	String destination=null;
	Date checkin=null;
	Date checkout=null;
	int numrooms=0;
	int numadults=0;
	int numminors=0;
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getCheckin() {
		return checkin;
	}
	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}
	public Date getCheckout() {
		return checkout;
	}
	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}
	public int getNumrooms() {
		return numrooms;
	}
	public void setNumrooms(int numrooms) {
		this.numrooms = numrooms;
	}
	public int getNumadults() {
		return numadults;
	}
	public void setNumadults(int numadults) {
		this.numadults = numadults;
	}
	public int getNumminors() {
		return numminors;
	}
	public void setNumminors(int numminors) {
		this.numminors = numminors;
	};


}
