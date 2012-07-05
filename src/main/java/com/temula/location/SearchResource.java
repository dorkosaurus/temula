package com.temula.location;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import com.temula.StringTemplateProcessor;
import com.temula.dataprovider.JDBCDataProvider;

@Path("/search/")
public class SearchResource {
	private static final char TEMPLATE_START_CHAR='^';
	private static final char TEMPLATE_END_CHAR='$';

	private JDBCDataProvider dataProvider = new JDBCDataProvider();
	
	static final Logger logger = Logger.getLogger(SearchResource.class.getName());
	
	@GET 
	@Path("availability")
	@Produces("text/html")
	public String searchSpaceAvailability(
			@QueryParam("destination") String destination,
			@QueryParam("checkin") String checkin, 
			@QueryParam("checkout") String checkout,
			@QueryParam("numrooms") String numrooms,
			@QueryParam("numadults") String numadults,
			@QueryParam("numminors") String numminors
			){
		String path2File = this.getClass().getResource("/templates/location/availability.stg").getPath();
		STGroup g = new STGroupFile(path2File,TEMPLATE_START_CHAR,TEMPLATE_END_CHAR);
		ST st = g.getInstanceOf("list");
		SpaceAvailabiltyQuery sa = new SpaceAvailabiltyQuery();
		sa.setDestination(destination);
		this.setInt(sa,numrooms,"numrooms");
		this.setInt(sa,numadults,"numadults");
		this.setInt(sa,numminors,"numminors");
		this.setDate(sa, checkin, "checkin");
		this.setDate(sa, checkout, "checkout");
		List<SpaceAvailability> list = this.dataProvider.get(sa);
		StringTemplateProcessor stp = new StringTemplateProcessor();
		String ret = stp.bind(list, st, "list");
		return ret;
	}
	
	private void setInt(SpaceAvailabiltyQuery sa ,String value,String paramName){
		if(sa==null||value==null||value.trim().length()==0)return;
		try{
			int valueInt = Integer.parseInt(value);
			if(paramName.equals("numadults"))sa.setNumadults(valueInt);
			else if(paramName.equals("numrooms"))sa.setNumrooms(valueInt);
			else if(paramName.equals("numminors"))sa.setNumminors(valueInt);
		}
		catch(NumberFormatException e){
			logger.warning("integer parameter "+paramName+" could not be parsed("+value+"):"+e.getMessage());
		}
		
	}
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	private void setDate(SpaceAvailabiltyQuery sa ,String value,String paramName){
		if(sa==null||value==null||value.trim().length()==0||paramName==null||paramName.trim().length()==0)return;
		try{
			
			Date valueDate = sdf.parse(value);
			if(paramName.equals("checkin"))sa.setCheckin(valueDate);
			else if(paramName.equals("checkout"))sa.setCheckout(valueDate);
		}
		catch(ParseException e){
			logger.warning("Date parameter "+paramName+" could not be parsed("+value+"):"+e.getMessage());
		}
	}

}