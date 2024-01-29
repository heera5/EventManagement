package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.ClientEvent;

public class ClientEventdao {

	EntityManagerFactory emf=Persistence.createEntityManagerFactory("amit");
    EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	public ClientEvent SaveClientEvent(ClientEvent ce)
	{
		et.begin();
		em.persist(ce);
		et.commit();
		return ce;
		
	}
	
	public ClientEvent findClientEvent(int id)
	{
		ClientEvent clientevent=em.find(ClientEvent.class,id);
		if(clientevent!=null)
		{
			return clientevent;
		}
		return null;
	}
	
	public ClientEvent updateClientEvent(ClientEvent ce,int ClientEventId)
	{
		ClientEvent ce1=em.find(ClientEvent.class, ClientEventId);
		if(ce1!=null)
		{
			ce.setClientEventId(ClientEventId);
			et.begin();
			em.merge(ce);
			et.commit();
		}
		return null;
	}

	public ClientEvent deleteClientEvent(int id)
	{
		ClientEvent ce=em.find(ClientEvent.class, id);
		if(ce!=null)
		{
			et.begin();
			em.remove(ce);
			et.commit();
			return ce;
		}
		return null;
	}

}
