package com.temula.location;





import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import junit.framework.TestCase;

import org.glassfish.grizzly.http.server.HttpServer;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.temula.StringTemplateProcessor;

public class TestLocation extends TestCase {
	HttpServer httpServer = null;
	Client c=null;
	WebResource r=null;
	static final Logger logger = Logger.getLogger(TestLocation.class.getName());
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/").port(9998).build();
	}
	
	public static final URI BASE_URI = getBaseURI();
		  
	protected static HttpServer startServer() throws IOException {
		logger.info("Starting grizzly...!!!");
		ResourceConfig rc = new PackagesResourceConfig("com.temula.location");
		return GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
	}
		      
	protected void setUp() throws Exception {
		httpServer = startServer();
		c  = Client.create();
		r=c.resource(BASE_URI);
	}    

	protected void tearDown() throws Exception {
	        super.tearDown();
	        httpServer.stop();
	 }
	public void testLocationGet()throws Exception{
		 String response = r.path("location").get(String.class);
		 assertTrue(response.equals("testme"));
	}
	public void testTempleGet()throws Exception{
		 logger.info("entering testTempleGet");
		 String response = r.path("location/space/").get(String.class);
		 SpaceParser parser = new SpaceParserVTDXML();
		 List<Space>spaces = parser.parseXHTML(response);
		 assertNotNull(spaces);
		 assertTrue(spaces.size()>0);
	 }

	 
	 public void testPost()throws Exception		{
		 List<Space>list = new ArrayList<Space>();
		 for(int i=0;i<10;i++){
			 Space space = new Space();
			 space.setName("Space"+i);
			 space.setProximityToTempleKM(i);
			 space.setNumDoubleACRooms(i*2);
			 list.add(space);
		 }	
		 char TEMPLATE_START_CHAR='^';
		 char TEMPLATE_END_CHAR='$';

		 String path2File = this.getClass().getResource("/templates/location/space.stg").getPath();
		 STGroup g = new STGroupFile(path2File,TEMPLATE_START_CHAR,TEMPLATE_END_CHAR);
		 ST st = g.getInstanceOf("longlist");
		 StringTemplateProcessor stp = new StringTemplateProcessor();
		 String ret = stp.bind(list, st, "list");
		 
		 ClientResponse response = r.path("location/space/").type(MediaType.TEXT_HTML).post(ClientResponse.class,ret );
		 ClientResponse.Status status = response.getClientResponseStatus();
		 assertTrue(status==ClientResponse.Status.OK);
	 }
	 
}
