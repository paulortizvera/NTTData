package com.portiz.nttdata.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Builder;
import lombok.Data;

@Data
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
}
