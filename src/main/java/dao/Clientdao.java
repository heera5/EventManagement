package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Client;



public class Clientdao {

	EntityManagerFactory emf=Persistence.createEntityManagerFactory("amit");
    EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	public Client SaveClient(Client c)
	{
		et.begin();
		em.persist(c);
		et.commit();
		return c;
		
	}
	
	public Client findClient(int id)
	{
		Client client=em.find(Client.class,id);
		if(client!=null)
		{
			return client;
		}
		return null;
	}
	
	public Client updateClient(Client c,int ClientId)
	{
		Client c1=em.find(Client.class, ClientId);
		if(c1!=null)
		{
			c.setClientId(ClientId);
			et.begin();
			em.merge(c);
			et.commit();
		}
		return null;
	}

	public Client deleteClient(int id)
	{
		Client c=em.find(Client.class, id);
		if(c!=null)
		{
			et.begin();
			em.remove(c);
			et.commit();
			return c;
		}
		return null;
	}

}
