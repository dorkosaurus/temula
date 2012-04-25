package com.temula.location;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import com.temula.StringTemplateProcessor;

public class TestPersonParserVTDXML extends TestCase{
	
	 public void testListParseEdgeCases()throws Exception{
		 BufferedInputStream bis = new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream("test.html"));
		 StringBuffer buff = new StringBuffer("");
		 int c = bis.read();
		 while(c!=-1){
			 buff.append((char)c);
			 c = bis.read();
		 }
		 System.out.println(buff.toString());
		 bis.close();
 		 SpaceParser parser = new SpaceParserVTDXML();
 		 List<Space>spaces = parser.parseXHTML(buff.toString());
		 assertNotNull(spaces);
		 assertEquals(spaces.size(),98);
		 for(int i=0;i<spaces.size();i++){
			 Space space = spaces.get(i);
			 assertNotNull(space);
			 TestCase.assertNotNull("space name "+i, space.getName());
			 assertTrue("space name "+i,space.getName().trim().length()>0);
		 }
	 }
	 public void testListParse()throws Exception{
		 List<Space>list = new ArrayList<Space>();
		 for(int i=0;i<100;i++){
			 Space space = new Space();
			 space.setName("Space"+i);
			 space.setSpaceId(i);
			 space.setProximityToTempleKM(""+i+" km");
			 space.setNumAvailableRooms(i*2);
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
			 assertNotNull(space);
			 assertNotNull(space.getName());
			 assertEquals(space.getName(),"Space"+i);
			 assertEquals(space.getNumAvailableRooms(), i*2);
		 }
	 }
	 
	 public void testInstanceParse()throws Exception{
		 List<Space>list = new ArrayList<Space>();
		 for(int i=0;i<1000;i++){
			 Space space = new Space();
			 space.setName("Space"+i);
			 space.setProximityToTempleKM(""+i+" km");
			 space.setNumAvailableRooms(i*2);
			 list.add(space);
		 }	
		 char TEMPLATE_START_CHAR='^';
		 char TEMPLATE_END_CHAR='$';

		 String path2File = this.getClass().getResource("/templates/location/space.stg").getPath();
		 STGroup g = new STGroupFile(path2File,TEMPLATE_START_CHAR,TEMPLATE_END_CHAR);
		 ST st = g.getInstanceOf("instance");
		 StringTemplateProcessor stp = new StringTemplateProcessor();
		 String ret = stp.bind(list, st, "p");
		 SpaceParser parser = new SpaceParserVTDXML();
		 Clock clock = new Clock();
		 List<Space>spaces= parser.parseXHTML(ret);
		 clock.stop();
		 assertNotNull(spaces);
		 assertTrue(spaces.size()>0);
		 for(int i=0;i<10;i++){
			 Space space = list.get(i);
			 assertNotNull(space);
			 assertNotNull(space.getName());
			 assertEquals(space.getName(),"Space"+i);
			 assertEquals(space.getNumAvailableRooms(), i*2);

		 }
		 
	 }
}
