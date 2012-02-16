package com.temula.person;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.temula.DataProvider;
import com.temula.InputValidator;
import com.temula.RepresentationalStateBinder;
import com.temula.Resource;
import com.temula.ResourceURI;
import com.temula.binder.PersonBinder;
import com.temula.dataprovider.HibernateDataProvider;

public class PersonModule extends AbstractModule {

	@Override
	protected void configure() {

		bind(  
				new TypeLiteral<DataProvider<Person>> (){
			
				}   
		).to(
				new TypeLiteral<HibernateDataProvider<Person>> (){
					
				} 		
		);

		
		bind(  
				new TypeLiteral<Resource<Person>> (){
			
				}   
		).to(PersonResource.class);

		
		bind(  
				new TypeLiteral<RepresentationalStateBinder<Person>> (){
					
				}   
		).to(PersonBinder.class);

		bind(  
				new TypeLiteral< InputValidator<Person>> (){
					
				}   
		).to(PersonInputValidator.class);

		
		bind(ResourceURI.class).to(PersonURI.class);

		
		
		
	}

}
