package com.temula.location;

import java.util.Date;

public class SpaceAvailability {
	Date week;
	int spaceId;
	String spaceName;
	int numDoubleACRooms;
	int numSingleACRooms;
	int numNonACRooms;
	float rupeesDoubleACRoom;
	float rupeesSingleACRoom;
	float rupeesNonACRoom;
	public Date getWeek() {
		return week;
	}
	public void setWeek(Date week) {
		this.week = week;
	}
	public int getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}
	public String getSpaceName() {
		return spaceName;
	}
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	public int getNumDoubleACRooms() {
		return numDoubleACRooms;
	}
	public void setNumDoubleACRooms(int numDoubleACRooms) {
		this.numDoubleACRooms = numDoubleACRooms;
	}
	public int getNumSingleACRooms() {
		return numSingleACRooms;
	}
	public void setNumSingleACRooms(int numSingleACRooms) {
		this.numSingleACRooms = numSingleACRooms;
	}
	public int getNumNonACRooms() {
		return numNonACRooms;
	}
	public void setNumNonACRooms(int numNonACRooms) {
		this.numNonACRooms = numNonACRooms;
	}
	public float getRupeesDoubleACRoom() {
		return rupeesDoubleACRoom;
	}
	public void setRupeesDoubleACRoom(float rupeesDoubleACRoom) {
		this.rupeesDoubleACRoom = rupeesDoubleACRoom;
	}
	public float getRupeesSingleACRoom() {
		return rupeesSingleACRoom;
	}
	public void setRupeesSingleACRoom(float rupeesSingleACRoom) {
		this.rupeesSingleACRoom = rupeesSingleACRoom;
	}
	public float getRupeesNonACRoom() {
		return rupeesNonACRoom;
	}
	public void setRupeesNonACRoom(float rupeesNonACRoom) {
		this.rupeesNonACRoom = rupeesNonACRoom;
	}
	
}
