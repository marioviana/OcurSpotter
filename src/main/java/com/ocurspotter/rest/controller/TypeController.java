package com.ocurspotter.rest.controller;

import com.ocurspotter.dao.TypeDao;
import com.ocurspotter.model.Type;
import com.ocurspotter.rest.dto.TypeBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marioferreira on 17/02/2018.
 */
@RestController
public class TypeController {

    private static Logger logger = Logger.getLogger(TypeController.class);

    /** The type dao. */
    @Autowired
    private TypeDao typeDao;

    @RequestMapping(path="/types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TypeBean> getAllTypes() {
        logger.info("REST - Getting all types");
        try {
            List<Type> types = this.typeDao.getAll();
            List<TypeBean> typeBeans = new ArrayList<>();
            for (Type type : types){
                typeBeans.add(new TypeBean(type.getId(), type.getName(), type.getDescription()));
            }
            return typeBeans;
        } catch (Exception e) {
            logger.info("REST - Error getting all types", e);
        } finally {
            logger.info("REST - End of getting all types");
        }
        return null;
    }

    @RequestMapping(path="/types/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TypeBean getTypeById(@PathVariable(value = "id") Long id) {
        logger.info("REST - Getting type by id: " + id);
        try {
            Type type = this.typeDao.getById(id);
            return new TypeBean(type.getId(), type.getName(), type.getDescription());
        } catch (Exception e) {
            logger.info("REST - Error getting the type", e);
        } finally {
            logger.info("REST - End of getting the type");
        }
        return null;
    }
}
