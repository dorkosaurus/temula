package com.temula.location;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import junit.framework.TestCase;

import org.glassfish.grizzly.http.server.HttpServer;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

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
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/").port(9998).build();
	}
	
	public static final URI BASE_URI = getBaseURI();
		  
	protected static HttpServer startServer() throws IOException {
		System.out.println("Starting grizzly...");
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
		 String response = r.path("location/space/").get(String.class);
		 ByteArrayInputStream bis= new ByteArrayInputStream(response.getBytes());

		 /** this is crazy...there's a bug in jtidy that's forcing me to read the document 2x 
		  * http://stackoverflow.com/questions/1530154/jtidy-upgrade-broke-document-xpaths
		  * */
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		 DocumentBuilder db = dbf.newDocumentBuilder();
		 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		 Tidy tidy = new Tidy();
		 tidy.setQuiet(true);
		 tidy.setShowWarnings(false);
		 Document doc = tidy.parseDOM(bis, System.out);

		 //first read
		 Source xmlSource = new DOMSource(doc);
		 Result outputTarget = new StreamResult(outputStream);
		 TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
		 InputStream is = new ByteArrayInputStream(outputStream.toByteArray());

		 //second read...without this second read I get a ArrayOutOfBoundsException
		 Document doc2 = db.parse(is);
		 
		 XPathFactory xpf = XPathFactory.newInstance();
		 XPath xpath = xpf.newXPath();
		 
		 //get name
		 String expression = "//dl[@itemtype='http://schema.org/Place/Space']/dd[@itemprop='name']";
		 NodeList nodes = (NodeList) xpath.evaluate(expression,doc2, XPathConstants.NODESET);
		 assertNotNull(nodes);
		 assertTrue(nodes.getLength()>0);
	 }
	 
	 public void testPost()throws Exception		{
		 List<Space>list = new ArrayList<Space>();
		 for(int i=0;i<10;i++){
			 Space space = new Space();
			 space.setName("Space"+i);
			 space.setProximityToTemple(""+i+" km");
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


		 ClientResponse response = r.path("location/space/").type(MediaType.TEXT_HTML).post(ClientResponse.class,ret );
		 ClientResponse.Status status = response.getClientResponseStatus();
		 assertTrue(status==ClientResponse.Status.OK);
	 }
	 
}