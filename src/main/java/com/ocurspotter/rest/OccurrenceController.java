package com.ocurspotter.rest;

import com.ocurspotter.model.Occurrence;
import com.ocurspotter.service.OccurrenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by marioferreira on 13/02/2018.
 */
@RestController
public class OccurrenceController {

    /** The user service. */
    @Autowired
    private OccurrenceService occurrenceService;

    @RequestMapping(path="/occurrence", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Occurrence> getAll() {
        return this.occurrenceService.getAll();
    }
}
