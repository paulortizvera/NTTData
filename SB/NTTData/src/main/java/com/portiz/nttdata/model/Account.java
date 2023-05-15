package com.portiz.nttdata.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "cuenta")
public class Account {

	@Id
	@Column(name = "numero_cuenta")
	private String number;

	@Column(name = "tipo_cuenta", length = 200)
	private String type;

	@Column(name = "saldo_inicial")
	private BigDecimal initialBalance;

	@Column(name = "estado", length = 1)
	private String status;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
