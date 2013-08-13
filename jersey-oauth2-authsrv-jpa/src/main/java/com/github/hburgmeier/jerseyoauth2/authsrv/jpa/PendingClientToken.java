package com.github.hburgmeier.jerseyoauth2.authsrv.jpa;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

import com.github.hburgmeier.jerseyoauth2.authsrv.api.client.IAuthorizedClientApp;
import com.github.hburgmeier.jerseyoauth2.authsrv.api.client.IPendingClientToken;

@Entity
@NamedQueries({
	@NamedQuery(name="findPendingByCode", query="select p from PendingClientToken p where p.code = :code and " +
			"p.clientApp.clientApp.clientId = :clientId and " +
			"p.clientApp.clientApp.clientSecret = :clientSecret")
})
@XmlRootElement
class PendingClientToken implements IPendingClientToken {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int id;
	
	private String code;
	
	@ManyToOne
	private AuthorizedClientApplication clientApp;
	
	public PendingClientToken()
	{
		
	}

	public PendingClientToken(AuthorizedClientApplication clientApp) {
		this.clientApp = clientApp;
		code = UUID.randomUUID().toString(); //TODO use code generator
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public AuthorizedClientApplication getClientApp() {
		return clientApp;
	}

	public void setClientApp(AuthorizedClientApplication clientApp) {
		this.clientApp = clientApp;
	}

	@Override
	public IAuthorizedClientApp getAuthorizedClient() {
		return clientApp;
	}
	
}