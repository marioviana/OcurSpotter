package com.ocurspotter.service;

import com.ocurspotter.dao.OccurrenceDao;
import com.ocurspotter.model.Occurrence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by marioferreira on 13/02/2018.
 */
@Service("occurrenceService")
public class OccurrenceServiceImpl implements OccurrenceService {

    /* The occurrence DAO */
    @Autowired
    private OccurrenceDao occurrenceDao;

    /**
     * Gets the all.
     *
     * @return the all
     */
    public List<Occurrence> getAll(){
        return this.occurrenceDao.getAll();
    }
}
