package dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int clientId;
	private String name;
	private long contact;
	private String email;
	private String password;
	@OneToMany(cascade=CascadeType.ALL)
	private List<ClientEvent>clientevent;
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<ClientEvent> getClientevent() {
		return clientevent;
	}
	public void setClientevent(List<ClientEvent> clientevent) {
		this.clientevent = clientevent;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", name=" + name + ", contact=" + contact + ", email=" + email
				+ ", password=" + password + ", clientevent=" + clientevent + "]";
	}
	
	


}
