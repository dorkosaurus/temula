 package com.temula.binder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.temula.RepresentationalState;
import com.temula.RepresentationalStateBinder;
import com.temula.person.Person;
import com.temula.representationalstate.PersonState;

public class PersonBinder implements RepresentationalStateBinder<Person> {

	@Override
	public RepresentationalState marshall(List<Person> data, URI resource) {
		PersonState state = new PersonState("<html><body>Hello world!</body></html>");
		return state;
	}

	@Override
	public List<Person> unmarshall(RepresentationalState state, URI resource) {
		Person person = new Person();
		person.setFirstName("Vivek");
		person.setLastName("Ramaswamy");
		List<Person> list = new ArrayList<Person>();
		list.add(person);
		return list;
	}

}
