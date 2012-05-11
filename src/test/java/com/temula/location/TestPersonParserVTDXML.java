package com.temula.location;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import junit.framework.TestCase;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import com.temula.StringTemplateProcessor;

public class TestPersonParserVTDXML extends TestCase{
	static final Logger logger = Logger.getLogger(TestLocation.class.getName());
	
	public void testListParse()throws Exception{
		List<Space>list = new ArrayList<Space>();
		for(int i=0;i<100;i++){
			boolean bool = (i%2==0)?true:false;
			Space space = new Space();
			space.setHasPoojaBookingOption(true);
			space.setBedOptions("bedOptions"+i);
			space.setBreakfastOptions("breakfastOptions"+i);
			space.setLunchOptions("lunchOptions"+i);
			space.setDinnerOptions("dinnerOptions"+i);
			space.setHasBackupPower(bool);
			space.setHasBottledWater(bool);
			space.setHasBroom(bool);
			space.setHasCleanSheets(bool);
			space.setHasDoormat(bool);
			space.setHasElevator(bool);
			space.setHasHotWater247(bool);
			space.setHasIndianToilet(bool);
			space.setHasLocksOnDoor(bool);
			space.setHasMosquitoCoils(bool);
			space.setHasPoojaBookingOption(bool);
			space.setHasSoap(bool);
			space.setHasTapWater(bool);
			space.setHasTV(bool);
			space.setHasTowels(bool);
			space.setHasWatchman(bool);
			space.setHasWheelChair(bool);
			space.setHasWesternToilet(bool);
			space.setIsFamilyFriendly(bool);
			space.setLunchOptions("lunchOptions"+i);
			space.setName("name"+i);
			space.setNumDoubleACRooms( 1 * (1+i));
			space.setNumNonACRooms(2  * (1+i));
			space.setNumSingleACRooms(3  * (1+i));
			space.setProximityToTempleKM(0.5  * (1+i));
			space.setSpaceId(i);
			list.add(space);
		}	
		char TEMPLATE_START_CHAR='^';
		char TEMPLATE_END_CHAR='$';

		String path2File = this.getClass().getResource("/templates/location/space.stg").getPath();
		STGroup g = new STGroupFile(path2File,TEMPLATE_START_CHAR,TEMPLATE_END_CHAR);
		ST st = g.getInstanceOf("list");
		StringTemplateProcessor stp = new StringTemplateProcessor();
		String ret = stp.bind(list, st, "list");

		SpaceParser parser = new SpaceParserVTDXML();
		Clock clock = new Clock();
		List<Space>spaces = parser.parseXHTML(ret);
		clock.stop();
		assertNotNull(spaces);
		assertEquals(spaces.size(),100);
		for(int i=0;i<spaces.size();i++){
			Space space = spaces.get(i);
			boolean found=false;
			for(int j=0;j<list.size();j++){
				if(space.getSpaceId()==list.get(j).getSpaceId())found=true;
			}
			assertTrue(found);
		}
	}

	public void testInstanceParse()throws Exception{
		String path2File = this.getClass().getResource("/templates/location/space.stg").getPath();
		char TEMPLATE_START_CHAR='^';
		char TEMPLATE_END_CHAR='$';
		STGroup g = new STGroupFile(path2File,TEMPLATE_START_CHAR,TEMPLATE_END_CHAR);
		ST st = g.getInstanceOf("instance");
		Space space = new Space();
		List<Space>list = new ArrayList<Space>();
		list.add(space);
		space.setHasPoojaBookingOption(true);
		space.setBedOptions("bedOptions");
		space.setBreakfastOptions("breakfastOptions");
		space.setLunchOptions("lunchOptions");
		space.setDinnerOptions("dinnerOptions");
		space.setHasBackupPower(true);
		space.setHasBottledWater(true);
		space.setHasBroom(true);
		space.setHasCleanSheets(true);
		space.setHasDoormat(true);
		space.setHasElevator(true);
		space.setHasHotWater247(true);
		space.setHasIndianToilet(true);
		space.setHasLocksOnDoor(true);
		space.setHasMosquitoCoils(true);
		space.setHasPoojaBookingOption(true);
		space.setHasSoap(true);
		space.setHasTapWater(true);
		space.setHasTV(true);
		space.setHasTowels(true);
		space.setHasWatchman(true);
		space.setHasWheelChair(true);
		space.setHasWesternToilet(true);
		space.setIsFamilyFriendly(true);
		space.setLunchOptions("lunchOptions");
		space.setName("name");
		space.setNumDoubleACRooms(1);
		space.setNumNonACRooms(2);
		space.setNumSingleACRooms(3);
		space.setProximityToTempleKM(0.5);
		space.setSpaceId(1234);
		StringTemplateProcessor stp = new StringTemplateProcessor();
		String ret = stp.bind(space, st, "p");

		SpaceParser parser = new SpaceParserVTDXML();
		Clock clock = new Clock();
		List<Space>spaces= parser.parseXHTML(ret);
		clock.stop();
		assertNotNull(spaces);
		assertTrue(spaces.size()==1);
		assertTrue(spaces.contains(space));
	}
}
