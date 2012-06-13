package com.temula.location;

public class Space {
	int spaceId;
	String name;
	int numSingleACRooms=0;
	int numDoubleACRooms=0;
	int numNonACRooms=0;
	double proximityToTempleKM;
	boolean hasMosquitoCoils=false;
	boolean hasTapWater=false;
	boolean hasCleanSheets=false;
	boolean hasWesternToilet=false;
	boolean hasIndianToilet=false;
	boolean hasHotWater247=false;
	boolean hasSoap=false;
	boolean hasBroom=false;
	boolean hasDoormat=false;
	boolean hasLocksOnDoor=false;
	boolean hasWatchman=false;
	String breakfastOptions;
	String lunchOptions;
	String dinnerOptions;
	String bedOptions;
	boolean hasTV=false;
	boolean isFamilyFriendly=false;
	boolean hasTowels=false;
	boolean hasPoojaBookingOption=false;
	boolean hasBackupPower=false;
	boolean hasElevator=false;
	boolean hasWheelChair=false;
	boolean hasBottledWater=false;
	int smallImageId=0;
	int largeImageId=0;
	
	private boolean stringEquals(String a,String b){
		if(a==null && b!=null)return false;
		if(a!=null && b==null)return false;
		if(a!=null && b!=null && a.equals(b)==false)return false;
		return true;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Space == false)return false;


		Space sp = (Space)obj;
		if( sp.getHasBackupPower() != this.getHasBackupPower() )return false;
		if( sp.getHasBroom() != this.getHasBroom() )return false;
		if( sp.getHasCleanSheets() != this.getHasCleanSheets() )return false;
		if( sp.getHasDoormat() != this.getHasDoormat() )return false;
		if( sp.getHasElevator() != this.getHasElevator() )return false;
		if( sp.getHasHotWater247() != this.getHasHotWater247() )return false;
		if( sp.getHasIndianToilet() != this.getHasIndianToilet() )return false;
		if( sp.getHasLocksOnDoor() != this.getHasLocksOnDoor())return false;
		if( sp.getHasMosquitoCoils() != this.getHasMosquitoCoils())return false;
		if( sp.getHasPoojaBookingOption() != this.getHasPoojaBookingOption())return false;
		if( sp.getHasSoap() != this.getHasSoap())return false;
		if( sp.getHasTapWater() != this.getHasTapWater())return false;
		if( sp.getHasTowels() != this.getHasTowels())return false;
		if( sp.getHasTV() != this.getHasTV())return false;
		if( sp.getHasWatchman() != this.getHasWatchman())return false;
		if( sp.getHasWesternToilet() != this.getHasWesternToilet())return false;
		if( sp.getHasWheelChair() != this.getHasWheelChair())return false;
		if( sp.getTotalRooms()!=this.getTotalRooms())return false;
		if( sp.getSpaceId() != this.getSpaceId())return false;
		if( sp.getProximityToTempleKM() != this.getProximityToTempleKM() )return false;
		if( sp.getNumSingleACRooms() != this.getNumSingleACRooms())return false;
		if( sp.getNumDoubleACRooms() != this.getNumDoubleACRooms())return false;

		if( this.stringEquals(sp.getName(), this.getName()) ==false)return false;
		if( this.stringEquals(sp.getLunchOptions(), this.getLunchOptions()) ==false)return false;
		if( this.stringEquals(sp.getBreakfastOptions(), this.getBreakfastOptions()) ==false)return false;
		if( this.stringEquals(sp.getDinnerOptions(), this.getDinnerOptions()) ==false)return false;
		if( this.stringEquals(sp.getBedOptions(), this.getBedOptions()) ==false)return false;

		return true;
	}
	
	
	public int getTotalRooms(){
		return this.numDoubleACRooms+this.numNonACRooms+this.numSingleACRooms;
	}
	
	

	public String toString(){
		return name;
	}

	public int getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumSingleACRooms() {
		return numSingleACRooms;
	}

	public void setNumSingleACRooms(int numSingleACRooms) {
		this.numSingleACRooms = numSingleACRooms;
	}

	public int getNumDoubleACRooms() {
		return numDoubleACRooms;
	}

	public void setNumDoubleACRooms(int numDoubleACRooms) {
		this.numDoubleACRooms = numDoubleACRooms;
	}

	public int getNumNonACRooms() {
		return numNonACRooms;
	}

	public void setNumNonACRooms(int numNonACRooms) {
		this.numNonACRooms = numNonACRooms;
	}

	public double getProximityToTempleKM() {
		return proximityToTempleKM;
	}

	public void setProximityToTempleKM(double proximityToTempleKM) {
		this.proximityToTempleKM = proximityToTempleKM;
	}

	public boolean getHasMosquitoCoils() {
		return hasMosquitoCoils;
	}

	public void setHasMosquitoCoils(boolean hasMosquitoCoils) {
		this.hasMosquitoCoils = hasMosquitoCoils;
	}

	public boolean getHasTapWater() {
		return hasTapWater;
	}

	public void setHasTapWater(boolean hasTapWater) {
		this.hasTapWater = hasTapWater;
	}

	public boolean getHasCleanSheets() {
		return hasCleanSheets;
	}

	public void setHasCleanSheets(boolean hasCleanSheets) {
		this.hasCleanSheets = hasCleanSheets;
	}

	public boolean getHasWesternToilet() {
		return hasWesternToilet;
	}

	public void setHasWesternToilet(boolean hasWesternToilet) {
		this.hasWesternToilet = hasWesternToilet;
	}

	public boolean getHasIndianToilet() {
		return hasIndianToilet;
	}

	public void setHasIndianToilet(boolean hasIndianToilet) {
		this.hasIndianToilet = hasIndianToilet;
	}

	public boolean getHasHotWater247() {
		return hasHotWater247;
	}

	public void setHasHotWater247(boolean hasHotWater247) {
		this.hasHotWater247 = hasHotWater247;
	}

	public boolean getHasSoap() {
		return hasSoap;
	}

	public void setHasSoap(boolean hasSoap) {
		this.hasSoap = hasSoap;
	}

	public boolean getHasBroom() {
		return hasBroom;
	}

	public void setHasBroom(boolean hasBroom) {
		this.hasBroom = hasBroom;
	}

	public boolean getHasDoormat() {
		return hasDoormat;
	}

	public void setHasDoormat(boolean hasDoormat) {
		this.hasDoormat = hasDoormat;
	}

	public boolean getHasLocksOnDoor() {
		return hasLocksOnDoor;
	}

	public void setHasLocksOnDoor(boolean hasLocksOnDoor) {
		this.hasLocksOnDoor = hasLocksOnDoor;
	}

	public boolean getHasWatchman() {
		return hasWatchman;
	}

	public void setHasWatchman(boolean hasWatchman) {
		this.hasWatchman = hasWatchman;
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


	public boolean getHasTowels() {
		return hasTowels;
	}

	public void setHasTowels(boolean hasTowels) {
		this.hasTowels = hasTowels;
	}

	public boolean getHasPoojaBookingOption() {
		return hasPoojaBookingOption;
	}

	public void setHasPoojaBookingOption(boolean hasPoojaBookingOption) {
		this.hasPoojaBookingOption = hasPoojaBookingOption;
	}

	public boolean getHasBackupPower() {
		return hasBackupPower;
	}

	public void setHasBackupPower(boolean hasBackupPower) {
		this.hasBackupPower = hasBackupPower;
	}

	public boolean getHasElevator() {
		return hasElevator;
	}

	public void setHasElevator(boolean hasElevator) {
		this.hasElevator = hasElevator;
	}

	public boolean getHasWheelChair() {
		return hasWheelChair;
	}

	public void setHasWheelChair(boolean hasWheelChair) {
		this.hasWheelChair = hasWheelChair;
	}

	public boolean isHasBottledWater() {
		return hasBottledWater;
	}

	public void setHasBottledWater(boolean hasBottledWater) {
		this.hasBottledWater = hasBottledWater;
	}
	public String getBreakfastOptions() {
		return breakfastOptions;
	}

	public void setBreakfastOptions(String breakfastOptions) {
		this.breakfastOptions = breakfastOptions;
	}

	public String getLunchOptions() {
		return lunchOptions;
	}

	public void setLunchOptions(String lunchOptions) {
		this.lunchOptions = lunchOptions;
	}

	public String getDinnerOptions() {
		return dinnerOptions;
	}

	public void setDinnerOptions(String dinnerOptions) {
		this.dinnerOptions = dinnerOptions;
	}

	public String getBedOptions() {
		return bedOptions;
	}

	public void setBedOptions(String bed) {
		this.bedOptions = bed;
	}

	public int getSmallImageId() {
		return smallImageId;
	}

	public void setSmallImageId(int smallImageId) {
		this.smallImageId = smallImageId;
	}

	public int getLargeImageId() {
		return largeImageId;
	}

	public void setLargeImageId(int largeImageId) {
		this.largeImageId = largeImageId;
	}

}
