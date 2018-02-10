package com.ocurspotter.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UserRole")
public class UserRole {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	@Column(name = "role")
	private String role;

	public UserRole() {}

	public UserRole(User user, String role) {
		this.user = user;
		this.role = role;
	}

	public Integer getId() { return this.id; }

	public void setId(Integer id) { this.id = id; }

	public User getUser() { return this.user; }

	public void setUser(User user) { this.user = user; }

	public String getRole() { return this.role; }

	public void setRole(String role) { this.role = role; }

}