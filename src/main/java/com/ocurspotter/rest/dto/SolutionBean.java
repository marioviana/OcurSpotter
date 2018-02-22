package com.ocurspotter.rest.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by marioferreira on 16/02/2018.
 */
@XmlRootElement
public class SolutionBean {

        @XmlElement
        private Long id;

        @XmlElement
        private String description;

        @XmlElement
        private Date openDate;

        @XmlElement
        private Date deadline;

        @XmlElement
        private Double value;

        @XmlElement
        private boolean choosed;

        @XmlElement
        private boolean status;

        @XmlElement
        private UserBean user;

        @XmlElement
        private Long upvotes;

        @XmlElement
        private Long downvotes;

        public SolutionBean(Long id, String description, Date openDate, Date deadline, Double value, boolean choosed,
                boolean status, UserBean user, Long upvotes, Long downvotes) {
                this.id = id;
                this.description = description;
                this.openDate = openDate;
                this.deadline = deadline;
                this.value = value;
                this.choosed = choosed;
                this.status = status;
                this.user = user;
                this.upvotes = upvotes;
                this.downvotes = downvotes;
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

        public Date getOpenDate() {
                return openDate;
        }

        public void setOpenDate(Date openDate) {
                this.openDate = openDate;
        }

        public Date getDeadline() {
                return deadline;
        }

        public void setDeadline(Date deadline) {
                this.deadline = deadline;
        }

        public Double getValue() {
                return value;
        }

        public void setValue(Double value) {
                this.value = value;
        }

        public boolean isChoosed() {
                return choosed;
        }

        public void setChoosed(boolean choosed) {
                this.choosed = choosed;
        }

        public boolean isStatus() {
                return status;
        }

        public void setStatus(boolean status) {
                this.status = status;
        }

        public UserBean getUser() {
                return user;
        }

        public void setUser(UserBean user) {
                this.user = user;
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
}
