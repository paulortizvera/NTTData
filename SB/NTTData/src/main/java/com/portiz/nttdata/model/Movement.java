package com.portiz.nttdata.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "movimiento")
public class Movement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private int code;

	@Column(name = "fecha", length = 200)
	private Date date;

	@Column(name = "tipo_movimiento", length = 1)
	private String type;

	@Column(name = "valor")
	private double value;

	@Column(name = "saldo", length = 200)
	private double balance;
	
	@Column(name = "estado", length = 1)
	private String status;

	@Column(name = "numero_cuenta")
	private String account;
}
