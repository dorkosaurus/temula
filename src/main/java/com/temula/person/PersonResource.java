package com.temula.person;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import com.temula.DataProvider;
import com.temula.InputValidator;
import com.temula.RepresentationalState;
import com.temula.RepresentationalStateBinder;
import com.temula.Resource;

public class PersonResource implements Resource<Person> {

	private DataProvider<Person> dataProvider=null;
	private RepresentationalStateBinder<Person> binder=null;
	private InputValidator<Person> validator=null;
	private URI resourceURI=null;
	public PersonResource(DataProvider<Person> dataProvider,RepresentationalStateBinder<Person> binder,InputValidator<Person> validator,URI resourceURI){
		this.setDataProvider(dataProvider);
		this.setBinder(binder);
		this.setValidator(validator);
		this.setResourceURI(resourceURI);
	}
	
	
	@Override
	public RepresentationalState get(Person attributes) {
		List<Person>people = this.dataProvider.get(attributes);
		return this.getBinder().marshall(people, this.getResourceURI());
	}
	

	@Override
	public Status put(Person t) {

		boolean isValid = this.getValidator().isValid(t);
		if(isValid==false){
			return Status.BAD_REQUEST;
		}
		
		return this.getDataProvider().put(t);
	}

	@Override
	public Status post(List<Person> list) {

		boolean isValid = this.getValidator().isValid(list);
		if(isValid==false){
			return Status.BAD_REQUEST;
		}

		return this.getDataProvider().post(list);
	}

	@Override
	public Status delete(Person t) {

		boolean isValid = this.getValidator().isValid(t);
		if(isValid==false){
			return Status.BAD_REQUEST;
		}

		return this.getDataProvider().delete(t);
	}

	private DataProvider<Person> getDataProvider() {
		return dataProvider;
	}


	private void setDataProvider(DataProvider<Person> dataProvider) {
		this.dataProvider = dataProvider;
	}


	private RepresentationalStateBinder<Person> getBinder() {
		return binder;
	}


	private void setBinder(RepresentationalStateBinder<Person> binder) {
		this.binder = binder;
	}


	private InputValidator<Person> getValidator() {
		return validator;
	}


	private void setValidator(InputValidator<Person> validator) {
		this.validator = validator;
	}


	private URI getResourceURI() {
		return resourceURI;
	}


	private void setResourceURI(URI resourceURI) {
		this.resourceURI = resourceURI;
	}
}
