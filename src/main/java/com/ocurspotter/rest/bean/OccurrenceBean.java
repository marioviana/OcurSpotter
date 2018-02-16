package com.ocurspotter.rest.bean;

import com.ocurspotter.model.Type;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * Created by marioferreira on 16/02/2018.
 */
@XmlRootElement
public class OccurrenceBean {

        @XmlElement
        private Long id;

        @XmlElement
        private String title;

        @XmlElement
        private String description;

        @XmlElement
        private Boolean status;

        @XmlElement
        private Date openDate;

        @XmlElement
        private Date closeDate;

        @XmlElement
        private Double latitude;

        @XmlElement
        private Double longitude;

        @XmlElement
        private Boolean suggestion;

        @XmlElement
        private String image;

        @XmlElement
        private UserBean user;

        @XmlElement
        private TypeBean type;

        @XmlElement
        private Long upvotes;

        @XmlElement
        private Long downvotes;

        @XmlElement
        private List<SolutionBean> solution;

        public OccurrenceBean(Long id, String title, String description, Boolean status, Date openDate, Date closeDate,
                Double latitude, Double longitude, Boolean suggestion, String image, UserBean user, TypeBean type,
                Long upvotes, Long downvotes) {
                this.id = id;
                this.title = title;
                this.description = description;
                this.status = status;
                this.openDate = openDate;
                this.closeDate = closeDate;
                this.latitude = latitude;
                this.longitude = longitude;
                this.suggestion = suggestion;
                this.image = image;
                this.user = user;
                this.type = type;
                this.upvotes = upvotes;
                this.downvotes = downvotes;
        }

        public OccurrenceBean(Long id, String title, String description, Boolean status, Date openDate, Date closeDate,
                Double latitude, Double longitude, Boolean suggestion, String image, UserBean user, TypeBean type,
                Long upvotes, Long downvotes, List<SolutionBean> solution) {
                this.id = id;
                this.title = title;
                this.description = description;
                this.status = status;
                this.openDate = openDate;
                this.closeDate = closeDate;
                this.latitude = latitude;
                this.longitude = longitude;
                this.suggestion = suggestion;
                this.image = image;
                this.user = user;
                this.type = type;
                this.upvotes = upvotes;
                this.downvotes = downvotes;
                this.solution = solution;
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

        public Boolean getStatus() {
                return status;
        }

        public void setStatus(Boolean status) {
                this.status = status;
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

        public String getImage() {
                return image;
        }

        public void setImage(String image) {
                this.image = image;
        }

        public UserBean getUser() {
                return user;
        }

        public void setUser(UserBean user) {
                this.user = user;
        }

        public TypeBean getType() {
                return type;
        }

        public void setType(TypeBean type) {
                this.type = type;
        }

        public Long getUpvotes() {
                return upvotes;
        }

        public void setUpvotes(Long upvotes) {
                this.upvotes = upvotes;
        }

        public Long getDownvotes() {
                return downvotes;
        }

        public void setDownvotes(Long downvotes) {
                this.downvotes = downvotes;
        }

        public List<SolutionBean> getSolution() {
                return solution;
        }

        public void setSolution(List<SolutionBean> solution) {
                this.solution = solution;
        }
}
