package com.temula.dataprovider;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.temula.DataProvider;
import com.temula.location.LocationResource;
import com.temula.location.Space;

public class HibernateDataProvider<T> extends HttpServlet  implements DataProvider<T> {

	private static ServiceRegistry serviceRegistry;
	private static SessionFactory sessionFactory=null;//configureSessionFactory();
	static final Logger logger = Logger.getLogger(LocationResource.class.getName());

	static{
		configureSessionFactory();
	}

	
	public static void main(String args[]){
		System.out.println("Starting hibernate!");
	}
	
	private static SessionFactory configureSessionFactory() throws HibernateException {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}

    private static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

	

    /**
     * Introspects the attribute and looks for any getters with zero parameters (i.e. typical accessor methods) where values are not null.  Sets this in hibernate
     */
	@Override
	public List<T> get(T t) {
        Session session = getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Criteria criteria = session.createCriteria(t.getClass());
        
        if(t instanceof Space){
        	Space space = (Space)t;
        	Integer spaceId = new Integer(space.getSpaceId());
        	if(spaceId>0){
        		criteria.add(Restrictions.eq("spaceId", spaceId));
        	}
        }
        
        List<T>list= criteria.list();
        tx1.commit();
        session.close();
        return list;
	}

	@Override
	public Status put( T t) {
        Session session = getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(t);
        tx1.commit();
        session.close();
        return Status.CREATED;
	}

	@Override
	public Status post(List<T> list) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        for ( int i=0; i<list.size(); i++ ) {
            session.save(list.get(i));
            if ( i % 20 == 0 ) { //20, same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
        }
        tx.commit();
        session.close();
        return Status.CREATED;
	}

	@Override
	public Status delete(T t) {
        Session session = getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(t);
        tx1.commit();
        session.close();
        return Status.OK;
	}

}
