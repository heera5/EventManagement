package controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.Admindao;
import dao.ClientEventdao;
import dao.ClientServicedao;
import dao.Clientdao;
import dao.Servicedao;
import dto.Admin;
import dto.Client;
import dto.ClientEvent;
import dto.ClientService;
import dto.EventType;
import dto.Service;

public class EventManagement {

	EntityManagerFactory emf=Persistence.createEntityManagerFactory("amit");
    EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	 Admindao adao=new Admindao();	
	 Servicedao sdao=new Servicedao();
	 Clientdao cdao=new Clientdao();
	 ClientServicedao csdao=new ClientServicedao();
	 ClientEventdao cedao=new ClientEventdao();
	 ClientEventdao d1=new ClientEventdao();
	 ClientServicedao d2=new ClientServicedao ();
	public static void main(String[] args) {
		
		EventManagement em= new EventManagement();
		System.out.println(em.UpdateService());
	}
	
	public Admin SaveAdmin()
	{
		
		Admin admin =new Admin();
		Scanner sc= new Scanner(System.in);
		System.out.println("Admin name");
		admin.setName(sc.next());
		System.out.println("Admin email");
		admin.setEmail(sc.next());
		System.out.println("admin contact");
		admin.setContact(sc.nextLong());
		System.out.println("admin password");
		admin.setPassword(sc.next());
		
		return adao.SaveAdmin(admin);
	}
	
	public Admin AdminLogin()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter admin email");
		String adminmail=sc.next();
		System.out.println("enter admin password");
		String adminpassword=sc.next();
		Query query=Persistence.createEntityManagerFactory("amit").createEntityManager().createQuery("select a from Admin a where a.email=?1");		
		query.setParameter(1,adminmail)	;
		Admin exAdmin=(Admin)query.getSingleResult();
		if(exAdmin!=null)
		{
			if(exAdmin.getPassword().equals(adminpassword))
			{
				return exAdmin;
			}
			return null;
			
		}
		return null;
		}
	

    public Service saveService()
    {
    Admin exadmin=AdminLogin();
    if(exadmin!=null)
    {
    	Service service =new Service();
    	Scanner sc=new Scanner(System.in);
    	System.out.println("enter service name");
    	service.setServiceName(sc.next());
    	System.out.println("enter service cost per person");
    	service.setServiceCostPerPerson(sc.nextDouble());
    	System.out.println("enter service cost per day");
    	service.setServiceCostPerDay(sc.nextDouble());
    	System.out.println("enter no of days");
    	service.setNoOfDays(sc.nextInt());
    	Service savedService=sdao.SaveService(service);
    	exadmin.getServices().add(savedService);
    	adao.updateAdmin(exadmin, exadmin.getAdminId());
    	return service;
    	
    }
    	return null;
    }
    

    
    public List<Service> getAllService()
    {
    	System.out.println("enter the admin ceredentials");
    	Admin exadmin =AdminLogin();
    	if(exadmin!=null)
    	{
    		Query query= Persistence.createEntityManagerFactory("amit").createEntityManager().createQuery("select a from Service a" );
    		List<Service> service= query.getResultList();
    		return service;
    	}
		return null;
    	
     }
    
     public String UpdateService()
     {
    	  Scanner sc= new Scanner("System.in");
    	  List<Service>service=getAllService();
    	  for(Service s:service)
    	  {
    		  System.out.println("serviceid" + "servicename"+ "cost_per_person"+ "cost_per_day");
    		  System.out.println("   "+s.getServiceId()+"     "+s.getServiceName()+"    "+s.getServiceCostPerPerson()+"    "+s.getServiceCostPerDay());
    	  }
    	  
    	  System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% choose service idfrom above to update %%%%%%%%%%%%%%%%%%%%%%%%%%");
    	  int id=sc.nextInt();
    	  Service tobeUpdated =sdao.findService(id);
    	  System.out.println("enter updated cost per person");
    	  double costperperson=sc.nextDouble();
    	  System.out.println("enterupdates cost per day");
    	  double costperday=sc.nextDouble();
    	  tobeUpdated .setServiceCostPerDay(costperday);
    	  tobeUpdated.setServiceCostPerPerson(costperperson);
    	  
    	  Service updated=sdao.updateService(tobeUpdated, id);
    	  if(updated!=null)
    	  {
    		return "service update success" ;
    		
    	  }
    	  return "invalid service id";
    	  
     }
    	
    	
     public Service deleteService()
     {
    	 Scanner sc= new Scanner(System.in);
    	 Admin exadmin=AdminLogin();
    	 
    	 if(exadmin!=null) {
    	  List<Service>services=exadmin.getServices();
       	  for(Service s:services)
       	  {
       		  System.out.println("serviceid" + "servicename"+ "cost_per_person"+ "cost_per_day");
       		  System.out.println("   "+s.getServiceId()+"     "+s.getServiceName()+"    "+s.getServiceCostPerPerson()+"    "+s.getServiceCostPerDay());
       	  }
       	  
       	  System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% choose service id from above to delete %%%%%%%%%%%%%%%%%%%%%%%%%%");
       	  int id=sc.nextInt();
       	  Service tobedeleted =sdao.findService(id);
       	  services.remove(tobedeleted) ;
       	  exadmin.setServices(services);
       	  adao.updateAdmin(exadmin, exadmin.getAdminId());
       	  return tobedeleted;
    	 }
    	 
    	 return null;
     }
    
   public Client clientSignup()
   {
	   Client client=new Client();
	   Scanner sc=new Scanner(System.in);
	   System.out.println("enter your name");
	   client.setName(sc.next());
	   System.out.println("enter your contact");
	   client.setContact(sc.nextLong());
	   System.out.println("enter your email");
	   client.setEmail(sc.next());
	   System.out.println( "enter your password");
	   client.setPassword(sc.next());
	   
	   return  cdao.SaveClient(client);
   }
    
   
   public Client clientLogin()
   {
	   Scanner sc =new Scanner(System.in);
	   System.out.println("enter client email");
	   String clientmail=sc.next();
	   System.out.println("enter client password");
	   String clientpassword=sc.next();
	   Query query= Persistence.createEntityManagerFactory("amit").createEntityManager().createQuery("select c from Client c where c.email=?1");
	   query.setParameter(1, clientmail);
	   Client exclient=(Client)query.getSingleResult();
	   if(exclient!=null)
	   {
		   if(exclient.getPassword().equals(clientpassword))
		   {
			   System.out.println("success");
			   return exclient;
			   
		   }
		   else 
		   {
			   System.out.println("not success");
		   }
		   return null;
	   }
	   return null;
   }
   
   public String addClientEventOnly() {
		
		System.out.println("------Add Client Event Only (Without Services)--------");
		Client exClient = clientLogin();
		List<ClientEvent> clientEventList = exClient.getClientevent();
		if(exClient != null)
		{
			ClientEvent ce = new ClientEvent();
			
			System.out.println("\tEnter Event Type  ");
			System.out.println("Press -1- for Marriage");
			System.out.println("Press -2- for Engagement");
			System.out.println("Press -3- for Birthday");
			System.out.println("Press -4- for Babyshower");
			System.out.println("Press -5- for Anniversary");
			System.out.println("Press -6- for BachelorParty");
			System.out.println("Press -7- for NamingCeremony");
			System.out.println("Press -8- for Reunion");
			Scanner sc=new Scanner(System.in);
			
			int value = sc.nextInt();
			if(value==1) {
				ce.setEventype(EventType.Marriage);
			}
			else if(value == 2) {
				ce.setEventype(EventType.Engagment);
			}
				
			else if(value == 3) {
				ce.setEventype(EventType.Birthday);
			}
			else if(value == 4) {
				ce.setEventype(EventType.Anniversary);
			}
			else if(value == 5) {
				ce.setEventype(EventType.BadyShower);
			}
			else if(value==6) {
				ce.setEventype(EventType.Reunion);
				
			}
			else if(value==7) {
				ce.setEventype(EventType.NamingCeremony);
			}
			ce.setStartDate(LocalDate.now());
			Scanner sc1=new Scanner(System.in);
			System.out.print("No Of Days : "); ce.setClientEventNoOfDays(sc1.nextInt());
			System.out.print("No Of People : "); ce.setClientEventNoOfPeople(sc1.nextInt());
			System.out.print("Event Location : "); ce.setClientEventLocation(sc1.next());
			ce.setClient(exClient);
			clientEventList.add(ce);
			exClient.setClientevent(clientEventList);
			Client updatedClient = cdao.updateClient(exClient, exClient.getClientId());

			if(updatedClient != null)
				return "Client Event Added Successfully";
		}
		return "Client User Not Found";
	}
   public String ClientService(){
   	Client exclient=clientLogin();
   	if(exclient!=null) {
   		List<ClientEvent>clv=exclient.getClientevent();
   		System.out.println(" Client Event Id : "); 
   		Scanner sc1=new Scanner(System.in);
   		int exClientEventId = sc1.nextInt();
			int countvalue = 0;
			for(ClientEvent events : clv)
			{
				if(events.getClientEventId() == exClientEventId)
				{
					countvalue ++;
					double eventCost = events.getClientEventCost();
					List<ClientService> exClientServices = events.getClientservices();
					Scanner sc=new Scanner(System.in);
					System.out.println("Enter Service Adding Count : "); 
					int serviceCount = sc.nextInt();
					for(int i=1;i<=serviceCount;i++)
					{
						ClientService cs = new ClientService();
						List<Service> listOfServices = getAllListServices();
						for (Service service : listOfServices) 
						{
							System.out.println(service);
						}
						System.out.print(" Service Id :");
						int svalue = sc.nextInt();
						Service s1 = sdao.findService(svalue);
						cs.setClientServiceName(s1.getServiceName());
						cs.setClientServiceNoOfDays(events.getClientEventNoOfDays());
						cs.setClientServiceCostPerPerson(s1.getServiceCostPerPerson());
						cs.setClientServiceCost(events.getClientEventNoOfDays() * cs.getClientServiceCostPerPerson() * cs.getClientServiceNoOfDays());
						eventCost = eventCost + cs.getClientServiceCost();
						exClientServices.add(cs);
						ClientService cs1= d2.SaveService(cs);
					}
					
					events.setClientEventCost(eventCost);
					events.setClientservices(exClientServices);
					ClientEvent ce1 = d1.updateClientEvent(events, events.getClientEventId());
					if(ce1 != null)
					{
						return " Service Added";
					}
				}
			}
		}
		return " Service Not Added";
	}
   	
		
		
   public List<Service> getAllListServices()
	{
		Query query = em.createQuery("select s from Service s");
		List<Service> listOfServices= query.getResultList();
		return listOfServices;
		
		
	}
   
}


