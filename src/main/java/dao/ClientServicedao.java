package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.ClientService;



public class ClientServicedao {

	EntityManagerFactory emf=Persistence.createEntityManagerFactory("amit");
    EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	public ClientService SaveService(ClientService cs)
	{
		et.begin();
		em.persist(cs);
		et.commit();
		return cs;
		
	}
	
	public ClientService findService(int id)
	{
		ClientService clientservice=em.find(ClientService.class,id);
		if(clientservice!=null)
		{
			return clientservice;
		}
		return null;
	}
	
	public ClientService updateClientService(ClientService cs,int ClientServiceId)
	{
		ClientService cs1=em.find(ClientService.class, ClientServiceId);
		if(cs1!=null)
		{
			cs.setClientServiceId(ClientServiceId);
			et.begin();
			em.merge(cs);
			et.commit();
		}
		return null;
	}

	public ClientService deleteService(int id)
	{
		ClientService cs=em.find(ClientService.class, id);
		if(cs!=null)
		{
			et.begin();
			em.remove(cs);
			et.commit();
			return cs;
		}
		return null;
	}

}
