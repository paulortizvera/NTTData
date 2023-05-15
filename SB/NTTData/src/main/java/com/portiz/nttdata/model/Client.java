package com.portiz.nttdata.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "cliente")
@PrimaryKeyJoinColumn(referencedColumnName = "identificacion")
public class Client extends Person {

	@Column(name = "clienteid", unique = true)
	private int clientId;

	@Column(name = "contrasena", length = 200)
	private String password;

	@Column(name = "estado", length = 1)
	private String status;

	@Column(name = "identificacion", length = 13)
	private String identification;

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

}
