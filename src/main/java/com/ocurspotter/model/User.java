package com.ocurspotter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "avatar")
	private String avatar;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserRole> userRole = new HashSet<UserRole>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Solution> solutions = new HashSet<Solution>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Occurrence> occurrences = new HashSet<Occurrence>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<OccurrenceVote> occurrenceVotes = new HashSet<OccurrenceVote>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<SolutionVote> solutionVotes = new HashSet<SolutionVote>(0);

	@ManyToMany(fetch = FetchType.LAZY)@JsonIgnore
	@JoinTable(
		name = "UserType",
		joinColumns = { @JoinColumn(name = "userId") },
		inverseJoinColumns = { @JoinColumn(name = "typeId") }
	)
	private Set<Type> types = new HashSet<Type>(0);

	public User() {}

	public User(String username, String firstName, String lastName, String password, boolean enabled, String avatar,
		Set<UserRole> userRole, Set<Solution> solutions, Set<Occurrence> occurrences,
		Set<OccurrenceVote> occurrenceVotes, Set<SolutionVote> solutionVotes, Set<Type> types) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.enabled = enabled;
		this.avatar = avatar;
		this.userRole = userRole;
		this.solutions = solutions;
		this.occurrences = occurrences;
		this.occurrenceVotes = occurrenceVotes;
		this.solutionVotes = solutionVotes;
		this.types = types;
	}

	public Long getId() { return this.id; }

	public void setId(Long id) { this.id = id; }

	public String getUsername() { return this.username; }

	public void setUsername(String username) { this.username = username; }

	public String getFirstName() { return firstName; }

	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }

	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getPassword() { return this.password; }

	public void setPassword(String password) { this.password = password; }

	public boolean getEnabled() { return this.enabled; }

	public void setEnabled(boolean enabled) { this.enabled = enabled; }

	public Set<UserRole> getUserRole() { return this.userRole; }

	public void setUserRole(Set<UserRole> userRole) { this.userRole = userRole; }

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Set<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(Set<Solution> solutions) {
		this.solutions = solutions;
	}

	public Set<Occurrence> getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(Set<Occurrence> occurrences) {
		this.occurrences = occurrences;
	}

	public Set<OccurrenceVote> getOccurrenceVotes() {
		return occurrenceVotes;
	}

	public void setOccurrenceVotes(Set<OccurrenceVote> occurrenceVotes) {
		this.occurrenceVotes = occurrenceVotes;
	}

	public Set<SolutionVote> getSolutionVotes() {
		return solutionVotes;
	}

	public void setSolutionVotes(Set<SolutionVote> solutionVotes) {
		this.solutionVotes = solutionVotes;
	}

	public Set<Type> getTypes() {
		return types;
	}

	public void setTypes(Set<Type> types) {
		this.types = types;
	}

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
		final User other = (User) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}
}
