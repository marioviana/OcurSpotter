package com.ocurspotter.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Type")
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "typeId")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "types")
	private Set<User> userTypes = new HashSet<User>(0);

	public Type() {}

	public Type(String name, String description, Set<User> userTypes) {
		this.name = name;
		this.description = description;
		this.userTypes = userTypes;
	}

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getDescription() { return description; }

	public void setDescription(String description) { this.description = description; }

	public Set<User> getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(Set<User> userTypes) {
		this.userTypes = userTypes;
	}

}
