package com.temula.location;

public class Space {
	int spaceId;
	String name;
	String proximityToTempleKM;

	int numAvailableRooms=0;
	
	boolean hasMosquitoCoils=false;
	boolean hasBedBugs=false;
	boolean hasRunningWater=false;
	boolean hasCleanSheets=false;
	boolean hasWesternToilet=false;
	boolean hasIndianToilet=false;
	boolean hasGeyser=false;
	boolean hasSoap=false;
	boolean hasBroom=false;
	boolean hasDoormat=false;
	boolean hasLocksOnDoor=false;
	boolean hasLatch=false;
	boolean hasWatchman=false;
	boolean hasShopsNearby=false;
	boolean hasVegBreakfast=false;
	boolean hasNonVegBreakfast=false;
	boolean hasNearbyVeg=false;
	boolean hasNearbyNonVeg=false;
	boolean hasTV=false;
	boolean isFamilyFriendly=false;
	boolean isBachelorFriendly=false;
		
	public String toString(){
		return name;
	}

	public String getProximityToTempleKM() {
		return proximityToTempleKM;
	}

	public void setProximityToTempleKM(String proximityToTempleKM) {
		this.proximityToTempleKM = proximityToTempleKM;
	}

	public int getNumAvailableRooms() {
		return numAvailableRooms;
	}

	public void setNumAvailableRooms(int numAvailableRooms) {
		this.numAvailableRooms = numAvailableRooms;
	}

	public boolean isHasMosquitoCoils() {
		return hasMosquitoCoils;
	}

	public void setHasMosquitoCoils(boolean hasMosquitoCoils) {
		this.hasMosquitoCoils = hasMosquitoCoils;
	}

	public boolean isHasBedBugs() {
		return hasBedBugs;
	}

	public void setHasBedBugs(boolean hasBedBugs) {
		this.hasBedBugs = hasBedBugs;
	}

	public boolean isHasRunningWater() {
		return hasRunningWater;
	}

	public void setHasRunningWater(boolean hasRunningWater) {
		this.hasRunningWater = hasRunningWater;
	}

	public boolean isHasCleanSheets() {
		return hasCleanSheets;
	}

	public void setHasCleanSheets(boolean hasCleanSheets) {
		this.hasCleanSheets = hasCleanSheets;
	}

	public boolean isHasWesternToilet() {
		return hasWesternToilet;
	}

	public void setHasWesternToilet(boolean hasWesternToilet) {
		this.hasWesternToilet = hasWesternToilet;
	}

	public boolean isHasIndianToilet() {
		return hasIndianToilet;
	}

	public void setHasIndianToilet(boolean hasIndianToilet) {
		this.hasIndianToilet = hasIndianToilet;
	}

	public boolean isHasGeyser() {
		return hasGeyser;
	}

	public void setHasGeyser(boolean hasGeyser) {
		this.hasGeyser = hasGeyser;
	}

	public boolean isHasSoap() {
		return hasSoap;
	}

	public void setHasSoap(boolean hasSoap) {
		this.hasSoap = hasSoap;
	}

	public boolean isHasBroom() {
		return hasBroom;
	}

	public void setHasBroom(boolean hasBroom) {
		this.hasBroom = hasBroom;
	}

	public boolean isHasDoormat() {
		return hasDoormat;
	}

	public void setHasDoormat(boolean hasDoormat) {
		this.hasDoormat = hasDoormat;
	}

	public boolean isHasLocksOnDoor() {
		return hasLocksOnDoor;
	}

	public void setHasLocksOnDoor(boolean hasLocksOnDoor) {
		this.hasLocksOnDoor = hasLocksOnDoor;
	}

	public boolean isHasLatch() {
		return hasLatch;
	}

	public void setHasLatch(boolean hasLatch) {
		this.hasLatch = hasLatch;
	}

	public boolean isHasWatchman() {
		return hasWatchman;
	}

	public void setHasWatchman(boolean hasWatchman) {
		this.hasWatchman = hasWatchman;
	}

	public boolean isHasShopsNearby() {
		return hasShopsNearby;
	}

	public void setHasShopsNearby(boolean hasShopsNearby) {
		this.hasShopsNearby = hasShopsNearby;
	}

	public boolean isHasVegBreakfast() {
		return hasVegBreakfast;
	}

	public void setHasVegBreakfast(boolean hasVegBreakfast) {
		this.hasVegBreakfast = hasVegBreakfast;
	}

	/** This seems to be a requirement for hibernate...a hack but needed */
	public boolean gethasNonVegBreakfast() {
		return hasNonVegBreakfast;
	}
	public boolean isHasNonVegBreakfast() {
		return hasNonVegBreakfast;
	}

	public void setHasNonVegBreakfast(boolean hasNonVegBreakfast) {
		this.hasNonVegBreakfast = hasNonVegBreakfast;
	}

	public boolean getHasNearbyVeg() {
		return hasNearbyVeg;
	}

	public void setHasNearbyVeg(boolean hasNearbyVeg) {
		this.hasNearbyVeg = hasNearbyVeg;
	}

	public boolean getHasNearbyNonVeg() {
		return hasNearbyNonVeg;
	}

	public void setHasNearbyNonVeg(boolean hasNearbyNonVeg) {
		this.hasNearbyNonVeg = hasNearbyNonVeg;
	}

	public boolean getHasTV() {
		return hasTV;
	}

	public void setHasTV(boolean hasTV) {
		this.hasTV = hasTV;
	}

	public boolean getIsFamilyFriendly() {
		return isFamilyFriendly;
	}

	public void setIsFamilyFriendly(boolean isFamilyFriendly) {
		this.isFamilyFriendly = isFamilyFriendly;
	}

	public boolean getIsBachelorFriendly() {
		return isBachelorFriendly;
	}

	public void setIsBachelorFriendly(boolean isBachelorFriendly) {
		this.isBachelorFriendly = isBachelorFriendly;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}

}
