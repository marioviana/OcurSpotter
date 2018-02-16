package com.ocurspotter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Solution")
public class Solution {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "description")
	private String description;

	@Column(name = "openDate")
	private Date openDate;

	@Column(name = "deadline")
	private Date deadline;

	@Column(name = "value")
	private Double value;

	@Column(name = "choosed")
	private boolean choosed;

	@Column(name = "status")
	private boolean status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "occurrenceId")
	private Occurrence occurrence;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "solution")
	private Set<SolutionVote> votes = new HashSet<SolutionVote>(0);


	public Solution() { }

	public Solution(String description, Date openDate, Date deadline, Double value, boolean choosed, boolean status,
		Occurrence occurrence, User user, Set<SolutionVote> votes) {
		this.description = description;
		this.openDate = openDate;
		this.deadline = deadline;
		this.value = value;
		this.choosed = choosed;
		this.status = status;
		this.occurrence = occurrence;
		this.user = user;
		this.votes = votes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) { this.deadline = deadline; }

	public Double getValue() { return value; }

	public void setValue(Double value) { this.value = value; }

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public boolean getChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Occurrence getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(Occurrence occurrence) {
		this.occurrence = occurrence;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<SolutionVote> getVotes() {
		return votes;
	}

	public void setVotes(Set<SolutionVote> votes) {
		this.votes = votes;
	}
}

