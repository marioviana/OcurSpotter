package com.ocurspotter.rest.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by marioferreira on 16/02/2018.
 */
@XmlRootElement
public class UserBean {

        @XmlElement
        private Long id;

        @XmlElement
        private String username;

        @XmlElement
        private String firstName;

        @XmlElement
        private String lastName;

        @XmlElement
        private String email;

        @XmlElement
        private String avatar;

        public UserBean(Long id, String username, String firstName, String lastName, String avatar) {
                this.id = id;
                this.username = username;
                this.firstName = firstName;
                this.lastName = lastName;
                this.avatar = avatar;
        }

        public UserBean(Long id, String username, String firstName, String lastName, String email, String avatar) {
                this.id = id;
                this.username = username;
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.avatar = avatar;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getAvatar() {
                return avatar;
        }

        public void setAvatar(String avatar) {
                this.avatar = avatar;
        }

        public String getEmail() { return email; }

        public void setEmail(String email) { this.email = email; }
}
