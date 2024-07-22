package com.portiz.nttdata.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Builder;
import lombok.Data;

@Data
@Entity(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

	@Id
	@Column(name = "identificacion", length = 13)
	private String identification;

	@Column(name = "nombre", length = 200)
	private String name;

	@Column(name = "genero", length = 1)
	private String gender;

	@Column(name = "edad")
	private int age;

	@Column(name = "direccion", length = 200)
	private String address;

	@Column(name = "telefono")
	private String phone;

}
