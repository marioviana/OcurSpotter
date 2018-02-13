package com.ocurspotter.model;

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

	@Column(name = "name")
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

	public Solution(Long id, String description, Date deadline, Double value) {
		this.id = id;
		this.description = description;
		this.deadline = deadline;
		this.value = value;
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
}

