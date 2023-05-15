package com.portiz.nttdata.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

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

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
