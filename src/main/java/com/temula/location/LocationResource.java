package com.temula.location;



import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import com.temula.StringTemplateProcessor;
import com.temula.dataprovider.HibernateDataProvider;

@Path("location")
public class LocationResource {
	private static final char TEMPLATE_START_CHAR='^';
	private static final char TEMPLATE_END_CHAR='$';

	/** Making this an instance variable so it can be reused**/
	private SpaceParser parser = new SpaceParserVTDXML();
	private HibernateDataProvider<Space> dataProvider = new HibernateDataProvider<Space>();
	
	static final Logger logger = Logger.getLogger(LocationResource.class.getName());
	

	
	@GET
	@Produces("text/plain")
	public String getLocationList(){
		return "testme";
	}
	
	@GET 
	@Path("space")
	@Produces("text/html")
	public String getSpaceList(){
		String path2File = this.getClass().getResource("/templates/location/space.stg").getPath();
		STGroup g = new STGroupFile(path2File,TEMPLATE_START_CHAR,TEMPLATE_END_CHAR);
		ST st = g.getInstanceOf("list");
		List<Space> list = this.dataProvider.get(new Space());
		StringTemplateProcessor stp = new StringTemplateProcessor();
		String ret = stp.bind(list, st, "list");
		return ret;
	}
	
	@GET
	@Path("space/{spaceId}")
	@Produces("text/html")
	public Response getSpace(@PathParam("spaceId") String spaceId){
		try{
			int spaceId_ = Integer.parseInt(spaceId);
			Space space = new Space();
			space.setSpaceId(spaceId_);
			List<Space> spaceList =this.dataProvider.get(space);

			String path2File = this.getClass().getResource("/templates/location/space.stg").getPath();
			STGroup g = new STGroupFile(path2File,TEMPLATE_START_CHAR,TEMPLATE_END_CHAR);
			ST st = g.getInstanceOf("instance");
			StringTemplateProcessor stp = new StringTemplateProcessor();
			String ret = stp.bind(spaceList.get(0), st, "p");
			return Response.ok().entity(ret).build();
		}
		catch(Exception e){
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	@POST
	@Path("space")
	@Consumes("text/html")
	public Response postTemplate(String html){
		Clock clock = new Clock();
		try{
			
			List<Space>spaces = parser.parseXHTML(html);
			clock.lap("parsed html");
			this.dataProvider.post(spaces);
			clock.lap("saved data");
			return Response.ok().build();
		}
		catch(Exception e){
			logger.severe(html);
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	

}