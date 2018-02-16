package com.ocurspotter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Occurrence")
public class Occurrence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private Boolean status;

	@Column(name = "openDate")
	private Date openDate;

	@Column(name = "closeDate")
	private Date closeDate;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@Column(name = "suggestion")
	private Boolean suggestion;

	@Column(name = "image")
	private String image;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typeId")
	private Type type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "occurrence")
	private Set<Solution> solutions = new HashSet<Solution>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "occurrence")
	private Set<OccurrenceVote> votes = new HashSet<OccurrenceVote>(0);

	public Occurrence() {}

	public Occurrence(String title, String description, Boolean status, Date openDate, Date closeDate, Double latitude,
		Double longitude, Boolean suggestion, String image, Type type, User user, Set<Solution> solutions,
		Set<OccurrenceVote> votes) {
		this.title = title;
		this.description = description;
		this.status = status;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.suggestion = suggestion;
		this.image = image;
		this.type = type;
		this.user = user;
		this.solutions = solutions;
		this.votes = votes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Boolean getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(Boolean suggestion) {
		this.suggestion = suggestion;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(Set<Solution> solutions) {
		this.solutions = solutions;
	}

	public Set<OccurrenceVote> getVotes() {
		return votes;
	}

	public void setVotes(Set<OccurrenceVote> votes) {
		this.votes = votes;
	}
}
