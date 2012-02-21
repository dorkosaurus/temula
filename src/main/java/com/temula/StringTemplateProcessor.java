package com.temula;

import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.StringRenderer;

import com.temula.person.Person;


public class StringTemplateProcessor {
	/** these characters denote the start end end of a variable interpolation.  
	 * to make them maximally intuitive, i'm using common regular expression 
	 * syntax to denote start and end*/
	private static final char TEMPLATE_START_CHAR='^';
	private static final char TEMPLATE_END_CHAR='$';
	
	StringRenderer renderer = new StringRenderer();
	public StringTemplateProcessor(){
		
	}

	/**
	 * General binding code.  used to generalize this documented StringTemplate code
	 * <pre>
	    ST st = new ST("<b>$u.id$</b>: $u.name$", '$', '$');
	    st.add("u", new User(999, "parrt"));
	    String result = st.render(); // "<b>999</b>: parrt"
	   </pre>
	   We pass in Object obj instead of User, the ST class to run the comparison with and the object identifer (the 'u' in '$u.id$' and '$u.name$').
	 * 
	 * @param obj
	 * @param template
	 * @param objectIdentifier
	 * @return
	 */
	public String bind(Object obj,ST template,String objectIdentifier){
		template.add(objectIdentifier,obj);
		return template.render();
	}
	
	public static void main(String args[])throws Exception{
		StringTemplateProcessor stp = new StringTemplateProcessor();
		stp.list();
//		stp.instance();
	}
	
	protected void list(){
		STGroup g = new STGroupFile("templates/person/person.stg",TEMPLATE_START_CHAR,TEMPLATE_END_CHAR);
		ST st = g.getInstanceOf("list");
		List<Person> list = new ArrayList<Person>();
		for(int i=0;i<3;i++){
			Person person = new Person();
			person.setFirstName("Vivek"+i);
			person.setLastName("Ramaswamy"+i);
			list.add(person);
		}
		
		StringTemplateProcessor stp = new StringTemplateProcessor();
		String ret = stp.bind(list, st, "list");
		System.out.println(ret);
	}
	protected void instance(){
		STGroup g = new STGroupFile("templates/person/person.stg",TEMPLATE_START_CHAR,TEMPLATE_END_CHAR);
		ST st = g.getInstanceOf("instance");
		Person person = new Person();
		person.setFirstName("Vivek");
		person.setLastName("Ramaswamy");
		StringTemplateProcessor stp = new StringTemplateProcessor();
		String ret = stp.bind(person, st, "p");
		System.out.println(ret);
	}
}
