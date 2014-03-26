package com.umarashfaq.tyrion.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="persons")
public class Person extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@Column(name="email", length=100, unique=true, nullable=false)
	String email;
	
	@Column(name="password")
	String password;
	
	@Column(name="username")
	String username;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
		
}
