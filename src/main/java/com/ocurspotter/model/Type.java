package com.ocurspotter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Type")
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "avatar")
	private String avatar;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
	private Set<Occurrence> occurrences = new HashSet<Occurrence>(0);

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "types")
	private Set<User> userTypes = new HashSet<User>(0);

	public Type() {}

	public Type(String name, String description, Set<Occurrence> occurrences, Set<User> userTypes) {
		this.name = name;
		this.description = description;
		this.occurrences = occurrences;
		this.userTypes = userTypes;
	}

	public Type(String name, String description, String avatar, Set<Occurrence> occurrences, Set<User> userTypes) {
		this.name = name;
		this.description = description;
		this.avatar = avatar;
		this.occurrences = occurrences;
		this.userTypes = userTypes;
	}

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getDescription() { return description; }

	public void setDescription(String description) { this.description = description; }

	public Set<Occurrence> getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(Set<Occurrence> occurrences) {
		this.occurrences = occurrences;
	}

	public Set<User> getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(Set<User> userTypes) {
		this.userTypes = userTypes;
	}

	public String getAvatar() { return avatar; }

	public void setAvatar(String avatar) { this.avatar = avatar; }

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 83 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Type other = (Type) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

}
