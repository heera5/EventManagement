package dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientService {
    @Id
	private int clientServiceId;
	private String clientServiceName;
	private double clientServiceCost;
	private int clientServiceNoOfDays;
	private double clientServiceCostPerPerson;
	private double clientServiceDuration;
	public int getClientServiceId() {
		return clientServiceId;
	}
	public void setClientServiceId(int clientServiceId) {
		this.clientServiceId = clientServiceId;
	}
	public String getClientServiceName() {
		return clientServiceName;
	}
	public void setClientServiceName(String clientServiceName) {
		this.clientServiceName = clientServiceName;
	}
	public double getClientServiceCost() {
		return clientServiceCost;
	}
	public void setClientServiceCost(double clientServiceCost) {
		this.clientServiceCost = clientServiceCost;
	}
	public int getClientServiceNoOfDays() {
		return clientServiceNoOfDays;
	}
	public void setClientServiceNoOfDays(int clientServiceNoOfDays) {
		this.clientServiceNoOfDays = clientServiceNoOfDays;
	}
	public double getClientServiceCostPerPerson() {
		return clientServiceCostPerPerson;
	}
	public void setClientServiceCostPerPerson(double clientServiceCostPerPerson) {
		this.clientServiceCostPerPerson = clientServiceCostPerPerson;
	}
	public double getClientServiceDuration() {
		return clientServiceDuration;
	}
	public void setClientServiceDuration(double clientServiceDuration) {
		this.clientServiceDuration = clientServiceDuration;
	}
	@Override
	public String toString() {
		return "ClientService [clientServiceId=" + clientServiceId + ", clientServiceName=" + clientServiceName
				+ ", clientServiceCost=" + clientServiceCost + ", clientServiceNoOfDays=" + clientServiceNoOfDays
				+ ", clientServiceCostPerPerson=" + clientServiceCostPerPerson + ", clientServiceDuration="
				+ clientServiceDuration + "]";
	}
	
	
}
