package com.portiz.nttdata.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "cuenta")
public class Account {

	@Id
	@Column(name = "numero_cuenta")
	private String number;

	@Column(name = "tipo_cuenta", length = 200)
	private String type;

	@Column(name = "saldo_inicial")
	private double initialBalance;

	@Column(name = "estado", length = 1)
	private String status;

	@Column(name = "clienteid")
	private int clientId;
}
