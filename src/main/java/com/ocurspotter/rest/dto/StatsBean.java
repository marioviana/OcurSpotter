package com.ocurspotter.rest.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by marioferreira on 16/02/2018.
 */
@XmlRootElement
public class StatsBean {

        @XmlElement
        private Long occurrences;

        @XmlElement
        private Long solutions;

        @XmlElement
        private Long users;

        @XmlElement
        private Long types;

        @XmlElement
        private Long occurrenceVotes;

        public StatsBean(Long occurrences, Long solutions, Long users, Long types, Long occurrenceVotes, Long solutionVotes) {
                this.occurrences = occurrences;
                this.solutions = solutions;
                this.users = users;
                this.types = types;
                this.occurrenceVotes = occurrenceVotes;
                this.solutionVotes = solutionVotes;
        }

        public Long getOccurrences() {
                return occurrences;
        }

        public void setOccurrences(Long occurrences) {
                this.occurrences = occurrences;
        }

        public Long getSolutions() {
                return solutions;
        }

        public void setSolutions(Long solutions) {
                this.solutions = solutions;
        }

        public Long getUsers() {
                return users;
        }

        public void setUsers(Long users) {
                this.users = users;
        }

        public Long getTypes() {
                return types;
        }

        public void setTypes(Long types) {
                this.types = types;
        }

        public Long getOccurrenceVotes() {
                return occurrenceVotes;
        }

        public void setOccurrenceVotes(Long occurrenceVotes) {
                this.occurrenceVotes = occurrenceVotes;
        }

        public Long getSolutionVotes() {
                return solutionVotes;
        }

        public void setSolutionVotes(Long solutionVotes) {
                this.solutionVotes = solutionVotes;
        }

        @XmlElement
        private Long solutionVotes;
}
