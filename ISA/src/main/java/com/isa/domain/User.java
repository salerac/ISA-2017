package com.isa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity(name="Korisnik")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	@Column @NotEmpty(message="Polje ime nije uneto.") @Pattern(regexp="[a-zA-Z]+", message="U Imenu su dozvoljena samo slova.")
	private String name;
	@Column @NotEmpty(message="Polje prezime nije uneto.") @Pattern(regexp="[a-zA-Z]+", message="U Prezimenu su dozvoljena samo slova.")
	private String surname;
	@Column @Email(message="Email nije dobrog formata.") @NotEmpty(message="Polje email nije uneto.")
	private String email;
	@Column @NotEmpty(message="Lozinka nije uneta.")
	private String password;
	@Column @NotEmpty(message="Grad nije unet.")
	private String city;
	@Column @NotEmpty(message="Broj nije unet") @Pattern(regexp="[0-9]+", message="Unos za broj nije validan.")
	private String phoneNumber;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Privilege privilege;
	@Column
	private boolean enabled;
	@Column
	private String confirmationToken;
	
	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	
	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public User(){}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
}
