package com.temula.person;


import javax.ws.rs.core.Response.Status;

import junit.framework.TestCase;

import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.temula.RepresentationalState;
import com.temula.Resource;

public class TestPerson extends TestCase{
	 Resource<Person> resource =null;
	
	@Before
	public void setUp() throws Exception {
	    /*
	     * Guice.createInjector() takes your Modules, and returns a new Injector
	     * instance. Most applications will call this method exactly once, in their
	     * main() method.
	     */
	    Injector injector = Guice.createInjector(new PersonModule());
	    /*
	     * Now that we've got the injector, we can build objects.
	     */
	    this.resource = injector.getInstance(Key.get(new TypeLiteral<Resource<Person>>(){}));
	}
	
	public void testPutGetDelete(){
		Person person = new Person();
		person.setFirstName("Dorkus");
		person.setLastName("Maximus");
		Status status = resource.put(person);
		assertEquals(Status.CREATED, status);
		
		
		RepresentationalState state = resource.get(person);
		assertNotNull(state);
		assertNotNull(state.toString());
		assertTrue(state.toString().length()>0);
		
		status = resource.delete(person);
		assertEquals(Status.OK,status);
		
		
	}
	
	
	
}
