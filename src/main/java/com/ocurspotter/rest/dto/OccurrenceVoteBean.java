package com.ocurspotter.rest.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * Created by marioferreira on 16/02/2018.
 */
@XmlRootElement
public class OccurrenceVoteBean {

        @XmlElement
        private Long userId;

        @XmlElement
        private Long occurrenceId;

        @XmlElement
        private Boolean vote;

        public OccurrenceVoteBean() {
        }

        public OccurrenceVoteBean(Long userId, Long occurrenceId, Boolean vote) {
                this.userId = userId;
                this.occurrenceId = occurrenceId;
                this.vote = vote;
        }

        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long userId) {
                this.userId = userId;
        }

        public Long getOccurrenceId() {
                return occurrenceId;
        }

        public void setOccurrenceId(Long occurrenceId) {
                this.occurrenceId = occurrenceId;
        }

        public Boolean getVote() {
                return vote;
        }

        public void setVote(Boolean vote) {
                this.vote = vote;
        }
}
