package com.ocurspotter.rest.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by marioferreira on 16/02/2018.
 */
@XmlRootElement
public class TypeBean {

        @XmlElement
        private Long id;

        @XmlElement
        private String name;

        @XmlElement
        private String description;

        @XmlElement
        private String avatar;

        public TypeBean(Long id, String name, String description, String avatar) {
                this.id = id;
                this.name = name;
                this.description = description;
                this.avatar = avatar;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getAvatar() { return avatar; }

        public void setAvatar(String avatar) { this.avatar = avatar; }
}
