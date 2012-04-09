package com.temula.location;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

public class SpaceParser {
	 Field fields[] = Space.class.getDeclaredFields();
	 Method methods[] = Space.class.getMethods();
	 
	
	public List<Space> parseXHTML(String xhtml)throws Exception{
		if(xhtml==null||xhtml.trim().length()==0)return null;
		 List<Space> spaces = new ArrayList<Space>();
	
		ByteArrayInputStream bis= new ByteArrayInputStream(xhtml.getBytes());

		
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
		 
		 /** GET THE LIST OF SPACES **/
		 String expression = "//dl[@itemtype='http://schema.org/Place/Space']";
		 NodeList nodes = (NodeList) xpath.evaluate(expression,doc2, XPathConstants.NODESET);

		 
		 /** FOR EACH SPACE **/
		 for(int idx=0;idx<nodes.getLength();idx++){
			 Node node = nodes.item(idx);
			 Space space = new Space();
			 
			 /** ITERATE THROUGH THE LIST OF ATTRIBUTES **/
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

				 
				 String fieldExpression = "//dl[@itemtype='http://schema.org/Place/Space']/dd[@itemprop='"+fname+"']";
				 NodeList nl = (NodeList)xpath.evaluate(fieldExpression,node, XPathConstants.NODESET);
				 
			 System.out.println(nl.getLength());
		 }
			 /*				 
				 System.out.println("fieldExpression="+fieldExpression+" methodName="+expectedMethodName+" value="+value+" node="+node);

				 boolean startsWithHas = (fname.toLowerCase().startsWith("has"));
				 boolean startsWithIs = (fname.toLowerCase().startsWith("is"));

				 Object obj = (startsWithHas || startsWithIs)?new Boolean(value):value;
				 
				 method.invoke(space, obj);
	*/
			 }
			 spaces.add(space);
		 }
		 

		 return spaces;
	}
}