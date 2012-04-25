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

public class SpaceParserVTDXML implements SpaceParser {
	static final Logger logger = Logger.getLogger(SpaceParserVTDXML.class.getName());
	static final Field fields[] = Space.class.getDeclaredFields();
	static final Method methods[] = Space.class.getMethods();
	static Map<String,Field>nameField=new HashMap<String,Field>();
	static Map<String,Method>nameMethod=new HashMap<String,Method>();
	static Map<String,Boolean>acceptableBooleans = new HashMap<String,Boolean>();
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
	 
	 
	Pattern pattern =  Pattern.compile("\\<span itemprop=\"(\\w+)\">(.+)</span>");	 
	Pattern pattern2 =  Pattern.compile("\\<span itemprop=\"spaceId\"><a href='(.+)'>.+</a></span>");	 
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
				Matcher m = pattern.matcher(fragment);
				if(m.matches()){
					if(m.group(1).toLowerCase().equals("spaceid"))continue;
					this.setValue(m.group(1), m.group(2), space);
				}
				Matcher m2 = pattern2.matcher(fragment);
				if(m2.matches()){
					//System.out.println(m2.group(1));
				}
			}
			spaces.add(space);
		}
		return spaces;
	}

	private void setValue(String fieldName,String value,Space space)throws Exception{
		String fieldNameLC = fieldName.toLowerCase();
		Method method = nameMethod.get(fieldNameLC);
		Field field = nameField.get(fieldNameLC);
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
			 else{

				 method.invoke(space, value);
			 }
		}
		 catch(Exception e){
			 throw e;
		 }

	}
}
