package com.temula.location;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
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
	@Path("temple")
	@Produces("text/plain")
	public String getTempleList(){
		String path2File = this.getClass().getResource("/templates/location/temple.stg").getPath();
		STGroup g = new STGroupFile(path2File,TEMPLATE_START_CHAR,TEMPLATE_END_CHAR);
		ST st = g.getInstanceOf("list");
		List<Temple> list = new ArrayList<Temple>();
		for(int i=0;i<3;i++){
			Temple temple = new Temple();
			temple.setName("Kanchi"+i);
			temple.setLatitude("latitude"+i);
			temple.setLongitude("longitude"+i);
			list.add(temple);
		}

		StringTemplateProcessor stp = new StringTemplateProcessor();
		String ret = stp.bind(list, st, "list");
		return ret;
	}
}
