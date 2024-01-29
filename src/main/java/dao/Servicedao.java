package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Service;



public class Servicedao {

	EntityManagerFactory emf=Persistence.createEntityManagerFactory("amit");
    EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	public Service SaveService(Service s)
	{
		et.begin();
		em.persist(s);
		et.commit();
		return s;
		
	}
	
	public Service findService(int id)
	{
		Service service=em.find(Service.class,id);
		if(service!=null)
		{
			return service;
		}
		return null;
	}
	
	public Service updateService(Service s,int ServiceId)
	{
		Service s1=em.find(Service.class, ServiceId);
		if(s1!=null)
		{
			s.setServiceId(ServiceId);
			et.begin();
			em.merge(s);
			et.commit();
		}
		return null;
	}

	public Service deleteService(int id)
	{
		Service s=em.find(Service.class, id);
		if(s!=null)
		{
			et.begin();
			em.remove(s);
			et.commit();
			return s;
		}
		return null;
	}


}
