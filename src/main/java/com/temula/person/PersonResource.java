package com.temula.person;

import java.util.List;

import javax.ws.rs.core.Response.Status;

import com.google.inject.Inject;
import com.temula.DataProvider;
import com.temula.InputValidator;
import com.temula.RepresentationalState;
import com.temula.RepresentationalStateBinder;
import com.temula.Resource;

public class PersonResource implements Resource<Person> {

	private DataProvider<Person> dataProvider=null;
	private RepresentationalStateBinder<Person> binder=null;
	private InputValidator<Person> validator=null;
	private PersonURI resourceURI=null;

	@Inject
	public PersonResource(DataProvider<Person> dataProvider,RepresentationalStateBinder<Person> binder,InputValidator<Person> validator,PersonURI resourceURI){
		this.dataProvider=dataProvider;
		this.binder=binder;
		this.validator=validator;
		this.resourceURI=resourceURI;
	}
	
	
	@Override
	public RepresentationalState get(Person attributes) {
		List<Person>people = this.dataProvider.get(attributes);
		return this.binder.marshall(people, this.resourceURI.getURI());
	}
	

	@Override
	public Status put(Person t) {

		boolean isValid = this.validator.isValid(t);
		if(isValid==false){
			return Status.BAD_REQUEST;
		}
		
		return this.dataProvider.put(t);
	}

	@Override
	public Status post(List<Person> list) {

		boolean isValid = this.validator.isValid(list);
		if(isValid==false){
			return Status.BAD_REQUEST;
		}

		return this.dataProvider.post(list);
	}

	@Override
	public Status delete(Person t) {

		boolean isValid = this.validator.isValid(t);
		if(isValid==false){
			return Status.BAD_REQUEST;
		}

		return this.dataProvider.delete(t);
	}
}