package dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.List;


import javax.persistence.OneToMany;
@Entity
public class ClientEvent {
    @Id
	private int clientEventId;
	private int clientEventNoOfPeople;
	private LocalDate startDate ;
	private double clientEventCost;
	private int clientEventNoOfDays;
	private String clientEventLocation;
	@ManyToOne(cascade=CascadeType.ALL)
	private Client client;
	@OneToMany(cascade=CascadeType.ALL)
	private List<ClientService>clientservices;
	private EventType eventype;
	public int getClientEventId() {
		return clientEventId;
	}
	public void setClientEventId(int clientEventId) {
		this.clientEventId = clientEventId;
	}
	public int getClientEventNoOfPeople() {
		return clientEventNoOfPeople;
	}
	public void setClientEventNoOfPeople(int clientEventNoOfPeople) {
		this.clientEventNoOfPeople = clientEventNoOfPeople;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public double getClientEventCost() {
		return clientEventCost;
	}
	public void setClientEventCost(double clientEventCost) {
		this.clientEventCost = clientEventCost;
	}
	public int getClientEventNoOfDays() {
		return clientEventNoOfDays;
	}
	public void setClientEventNoOfDays(int clientEventNoOfDays) {
		this.clientEventNoOfDays = clientEventNoOfDays;
	}
	public String getClientEventLocation() {
		return clientEventLocation;
	}
	public void setClientEventLocation(String clientEventLocation) {
		this.clientEventLocation = clientEventLocation;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<ClientService> getClientservices() {
		return clientservices;
	}
	public void setClientservices(List<ClientService> clientservices) {
		this.clientservices = clientservices;
	}
	public EventType getEventype() {
		return eventype;
	}
	public void setEventype(EventType eventype) {
		this.eventype = eventype;
	}
	@Override
	public String toString() {
		return "ClientEvent [clientEventId=" + clientEventId + ", clientEventNoOfPeople=" + clientEventNoOfPeople
				+ ", startDate=" + startDate + ", clientEventCost=" + clientEventCost + ", clientEventNoOfDays="
				+ clientEventNoOfDays + ", clientEventLocation=" + clientEventLocation + ", client=" + client
				+ ", clientservices=" + clientservices + ", eventype=" + eventype + "]";
	}
	
	
	
}
