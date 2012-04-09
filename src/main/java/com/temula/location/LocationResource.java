package com.temula.location;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import com.temula.StringTemplateProcessor;

@Path("location")
public class LocationResource {
	private static final char TEMPLATE_START_CHAR='^';
	private static final char TEMPLATE_END_CHAR='$';

	
	
	@GET
	@Produces("text/plain")
	public String getLocationList(){
		return "testme";
	}
	
	@GET 
	@Path("space")
	@Produces("text/html")
	public String getTempleList(){
		String path2File = this.getClass().getResource("/templates/location/space.stg").getPath();
		STGroup g = new STGroupFile(path2File,TEMPLATE_START_CHAR,TEMPLATE_END_CHAR);
		ST st = g.getInstanceOf("list");
		List<Space> list = new ArrayList<Space>();
		for(int i=0;i<10;i++){
			Space space = new Space();
			space.setName("Space"+i);
			space.setProximityToTemple(""+i+" km");
			space.setNumAvailableRooms(i*2);
			list.add(space);
		}

		StringTemplateProcessor stp = new StringTemplateProcessor();
		String ret = stp.bind(list, st, "list");
		return ret;
	}
	

	@POST
	@Path("space")
	@Consumes("text/html")
	public void postTemplate(String html){
		
	}
	
}
