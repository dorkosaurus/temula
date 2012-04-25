package com.temula.location;


import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class SpaceParserSaxon implements SpaceParser{
	Field fields[] = Space.class.getDeclaredFields();
	 Method methods[] = Space.class.getMethods();
	 static final Logger logger = Logger.getLogger(LocationResource.class.getName());

	
	public List<Space> parseXHTML(String xhtml)throws Exception{
		if(xhtml==null||xhtml.trim().length()==0)return null;
		List<Space> spaces = new ArrayList<Space>();

		ByteArrayInputStream bis = new ByteArrayInputStream(xhtml.getBytes());
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(bis);
		XPathFactory xpf = XPathFactory.newInstance();
		XPath xpath = xpf.newXPath();

		NodeList nodes = (NodeList) xpath.evaluate(EXPRESSION,doc, XPathConstants.NODESET);
		
		for(int idx=0;idx<nodes.getLength();idx++){

			 
			 Node node = nodes.item(idx);
			 NodeList subnodes = (NodeList)xpath.evaluate(SUBEXPRESSION,node, XPathConstants.NODESET);
			 Space space = new Space();
			 // ITERATE THROUGH THE LIST OF ATTRIBUTES 
			 for(int fieldIdx=0;fieldIdx<fields.length;fieldIdx++){
				 String fname = fields[fieldIdx].getName();
				 //FIND THE PUBLIC SETTER FOR THE ATTRIBUTE OR MOVE ON
				 String expectedMethodName = "set"+fname.toLowerCase();
				 Method method=null;
				 for(int methodIdx=0;methodIdx<methods.length;methodIdx++){
					 Method method_ = methods[methodIdx];
					 String actualMethodName = method_.getName().toLowerCase();
					 if(actualMethodName.equals(expectedMethodName)){
						 method=method_;
						 break;
					 }
				 }
				 if(method==null)continue;

				 
				 String subexpression = SUBEXPRESSION2.replaceAll("ELEMNAMEGOESHERE", fname);
				 String value = (String) xpath.evaluate(subexpression,node, XPathConstants.STRING);
				 //I'm asserting that a null or empty string should be ignored
				 if(value==null||value.trim().length()==0){
					 continue;
				 }
				 String className = fields[fieldIdx].getType().getName().toLowerCase();
				 try{
					 if(className.equals("boolean")){
						 Boolean b = new Boolean(value);
						 method.invoke(space, b);
					 }
					 else if(className.equals("int")){
						 Integer itg = new Integer(value);
						 method.invoke(space, itg);
					 }
					 else{
						 method.invoke(space, value);
					 }
				 }
				 catch(Exception e){
					 e.printStackTrace();
				 }
			 }
			 spaces.add(space);
		 }
		return spaces;
		
	}
}