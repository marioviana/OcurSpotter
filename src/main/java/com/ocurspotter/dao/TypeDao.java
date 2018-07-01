package com.ocurspotter.dao;

import com.ocurspotter.model.Type;

import java.util.List;

public interface TypeDao {

    /**
     * Save.
     *
     * @param type the type
     */
    void save(Type type);

    /**
     * Get by id.
     *
     * @param id the type id
     */
    Type getById(Long id);

    /**
     * Gets the all.
     *
     * @return the all
     */
    List<Type> getAll();

    /**
     * Find by occurrence.
     *
     * @param id the occurrence id
     * @return the user
     */
    Type getByOccurrence(Long id);

    /** Count types
     *
     * @return the count
     */
    Long count();
}