package com.temula.location;

import junit.framework.TestCase;

public class TestAvailability extends TestCase {
	
	public void testGetSpaceAvailability(){
		SearchResource rr = new SearchResource();
		String destination=null,checkin=null,checkout=null, numrooms=null,numadults=null,numminors=null;
		System.out.println(rr.searchSpaceAvailability(destination,checkin,checkout,numrooms,numadults,numminors));
	}
}
