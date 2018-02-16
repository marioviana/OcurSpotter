package com.ocurspotter.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="SolutionVote")
public class SolutionVote implements Serializable {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "solutionId")
	private Solution solution;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	@Column(name = "vote")
	private Boolean vote;


	public SolutionVote() {}

	public SolutionVote(Solution solution, User user, boolean vote) {
		this.solution = solution;
		this.user = user;
		this.vote = vote;
	}

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getVote() {
		return vote;
	}

	public void setVote(Boolean vote) {
		this.vote = vote;
	}
}
