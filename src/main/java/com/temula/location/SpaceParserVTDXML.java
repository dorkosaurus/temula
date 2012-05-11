package com.temula.location;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
/**
 * This classes uses VTDXML as the xpath engine to parse xhtml Space representations.  In time trials I ran, this performed 20x faster than Tidy or SAXON.  Supposed to be much faster than Xerxes as well but I haven't timed this.
 * @author vivekr
 */
public class SpaceParserVTDXML implements SpaceParser {

	/** Local logger **/
	static final Logger logger = Logger.getLogger(SpaceParserVTDXML.class.getName());

	/** Fields will be a static variable shared by all classes.  This might be a bottlneck but I'm not sure yet */
	static final Field fields[] = Space.class.getDeclaredFields();

	/** Methods loaded for fast, shared lookup.  Again, this might be a performance bottleneck as load increases */
	static final Method methods[] = Space.class.getMethods();

	/** Maps the itemprop name in the xhtml document to the field associated with it*/
	static Map<String,Field>nameField=new HashMap<String,Field>();

	/** Maps the itemprop name in the xhtml document to the method associated with it*/
	static Map<String,Method>nameMethod=new HashMap<String,Method>();

	/** I need a dictionary of what accounts for acceptable boolean term */
	static Map<String,Boolean>acceptableBooleans = new HashMap<String,Boolean>();

	static final String[] ignoreFields = new String[]{"totalrooms"};
	
	
	
	/** Mapping names to Fields and Methods here.  Note that lowercase itemprop names exactly match to the field names in the Space class */
	static{
		for(Field field:fields){
			String fNameLC = field.getName().toLowerCase();
			nameField.put(fNameLC, field);
			String getmethodLC = "set"+fNameLC;
			for(int i=0;i<methods.length;i++){
				String methodNameLC = methods[i].getName().toLowerCase();
				if(methodNameLC.equals(getmethodLC)){
					nameMethod.put(fNameLC, methods[i]);
				}
			}
		}
	}

	/** Setting acceptable boolean values here **/
	static{
		acceptableBooleans.put("t",true);
		acceptableBooleans.put("f",false);
		acceptableBooleans.put("true",true);
		acceptableBooleans.put("false",false);
		acceptableBooleans.put("y",true);
		acceptableBooleans.put("n",false);
		acceptableBooleans.put("yes",true);
		acceptableBooleans.put("no",false);
	}
	 
	/** This is the pattern used to parse fragments.  Essentially looking for the item prop and its value **/
	Pattern itemPropPattern =  Pattern.compile("\\<span itemprop=\"(\\w+)\">(.+)</span>");	 

	
	/**
	 * This method performs the following functionality
	 * 1) Breaks apart the xhtml into elements by EXPRESSION. In XPATH notation, this would be a node-set
	 * 2) For each element that results
	 * 2a) Create a space object
	 * 2b) Split the element into fragments using SUBEXPRESSION (another nodeset)
	 * 2c) For each fragment
	 * 2ci) Test if matches the itemPropPattern.  If so, take the name and value and bind it to the instantiated Space object.
	 * 2d) Add the space object to the list
	 * 3) return the list.
	 */
	@Override
	public List<Space> parseXHTML(String xhtml) throws Exception {
		List<Space> spaces = new ArrayList<Space>();
		byte[]b = xhtml.getBytes();
		VTDGen vg = new VTDGen();
		vg.setDoc(b);
		vg.parse(true);
		VTDNav nav = vg.getNav();
		AutoPilot ap = new AutoPilot();
		ap.bind(nav);
		ap.selectXPath(EXPRESSION);

		while(ap.evalXPath()!= -1){
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			long l = nav.getElementFragment();
			int len = (int) (l>>32);
			int offset = (int) l;
			baos.write(b, offset, len); //write the fragment out into out.txt
			baos.flush();
			byte[]bytes = baos.toByteArray();
			VTDGen vg1 = new VTDGen();
			vg1.setDoc(bytes);
			vg1.parse(true);
			VTDNav nav1 = vg1.getNav();
			AutoPilot ap1 = new AutoPilot();
			ap1.bind(nav1);
			ap1.selectXPath(SUBEXPRESSION);
			Space space = new Space();
			while(ap1.evalXPath()!=-1){
				ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
				long l1 = nav1.getElementFragment();
				int len1 = (int) (l1>>32);
				int offset1 = (int) l1;
				baos1.write(bytes, offset1, len1); //write the fragment out into out.txt
				byte[]bytes1 = baos1.toByteArray();
				String fragment = new String(bytes1);
				Matcher m = itemPropPattern.matcher(fragment);
				if(m.matches()){
					this.setValue(m.group(1), m.group(2), space);
				}
			}
			spaces.add(space);
		}
		return spaces;
	}
	
	/**
	 * This method sets the value on the space object.  uses introspection so I can change space attributes at will and it will automagically bind.
	 * @param fieldName
	 * @param value
	 * @param space
	 * @throws Exception
	 */
	private void setValue(String fieldName,String value,Space space)throws Exception{
		
		
		
		String fieldNameLC = fieldName.toLowerCase();
		for(String ignoreName:ignoreFields){
			if(ignoreName.equals(fieldNameLC))return;
		}
		
		
		Method method = nameMethod.get(fieldNameLC);
		Field field = nameField.get(fieldNameLC);

		
		
		if(method==null)throw new Exception("field name "+fieldName+" had a null method");
		if(field==null)throw new Exception("field name "+fieldName+" had a null Field");
		
		
		String className = field.getType().getName().toLowerCase();
		try{
			if(className.equals("boolean")){
				String valueLC = value.toLowerCase();
				Boolean bool = acceptableBooleans.get(valueLC);
				if(bool!=null){
					method.invoke(space, bool);
				}
			 }
			 else if(className.equals("int")){
				 Integer itg = new Integer(value);
				 method.invoke(space, itg);
			 }
			 else if(className.equals("double")){
				 Double doub = new Double(value);
				 method.invoke(space, doub);
			 }
			 else{

				 method.invoke(space, value);
			 }
		}
		 catch(Exception e){
			 throw e;
		 }
}
}
