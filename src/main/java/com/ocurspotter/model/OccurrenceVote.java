package com.ocurspotter.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="OccurrenceVote")
public class OccurrenceVote implements Serializable {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "occurrenceId")
	private Occurrence occurrence;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	@Column(name = "vote")
	private Integer vote;

	public OccurrenceVote() {}

	public OccurrenceVote(Occurrence occurrence, User user, int vote) {
		this.occurrence = occurrence;
		this.user = user;
		this.vote = vote;
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

	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}
}
