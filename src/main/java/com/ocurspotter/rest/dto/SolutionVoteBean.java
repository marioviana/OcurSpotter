package com.ocurspotter.rest.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by marioferreira on 16/02/2018.
 */
@XmlRootElement
public class SolutionVoteBean {

        @XmlElement
        private Long userId;

        @XmlElement
        private Long solutionId;

        @XmlElement
        private Boolean vote;

        public SolutionVoteBean() {
        }

        public SolutionVoteBean(Long userId, Long solutionId, Boolean vote) {
                this.userId = userId;
                this.solutionId = solutionId;
                this.vote = vote;
        }

        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long userId) {
                this.userId = userId;
        }

        public Long getSolutionId() { return this.solutionId; }

        public void setSolutionId(Long occurrenceId) {
                this.solutionId = occurrenceId;
        }

        public Boolean getVote() {
                return vote;
        }

        public void setVote(Boolean vote) {
                this.vote = vote;
        }
}
