package com.temula.person;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.temula.PersistenceInterface;
import com.temula.QueryParameterInterface;
import com.temula.ValidatorInterface;
import com.temula.exception.InvalidInputException;
import com.temula.exception.NullInputException;
import com.temula.exception.NullListElementException;
import com.temula.exception.RecordExistsException;
import com.temula.utilities.HibernateSessionUtil;

public class PersonManager {
	ValidatorInterface<Person> validator=null;
	PersistenceInterface<Person> persistence=null;
	public PersonManager(ValidatorInterface<Person> personValidator,PersistenceInterface<Person> persistence){
		this.validator=personValidator;
		this.persistence=persistence;
	}
	
	public PersonManager(){
		
	}
	
	protected List<Person> get(List<QueryParameterInterface<?>> queryParams){
		return this.persistence.get(queryParams);
	}
	
    public void insert(Person person) throws NullInputException,InvalidInputException,RecordExistsException{
    	if(person==null)throw new NullInputException();
    	/*
		boolean validPerson = validator.isValid(person);
    	if(validPerson==false)throw new InvalidInputException();

    	if(person.getPersonId()>0)throw new RecordExistsException(""+person.getPersonId());
    	persistence.insert(person);
    	*/
    	Session session = HibernateSessionUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();
    }
    /**
     * Inserts a list of people
     * @param people
     * @throws NullInputException
     * @throws NullListElementException
     * @throws InvalidListElementException
     */
    protected void insert(List<Person>people)throws NullInputException,NullListElementException,InvalidInputException,RecordExistsException{
    	/** Validate input **/
    	if(people==null)throw new NullInputException();
    	for(int i=0;i<people.size();i++){
    		Person person = people.get(i);

    		if(person==null){
    			throw new NullListElementException();
    		}

    		boolean validPerson = validator.isValid(person);
    		if(validPerson==false){
    			throw new InvalidInputException(person.toString());
    		}
    	}
    	persistence.insert(people);
/* 
        Session session = HibernateSessionUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        for ( int i=0; i<people.size(); i++ ) {
            session.save(people.get(i));
            if ( i % 20 == 0 ) { //20, same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
        }
           
        tx.commit();
*/
    }
}