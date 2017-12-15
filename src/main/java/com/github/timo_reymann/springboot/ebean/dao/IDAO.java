package com.github.timo_reymann.springboot.ebean.dao;

import io.ebean.Query;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Timo Reymann
 * @since 14.12.17
 */
public interface IDAO<T, ID extends Serializable> {
    /**
     * Save an entity
     *
     * @param entity Entity zum Speichern
     */
    void save(T entity);

    /**
     * Save collection of entities
     *
     * @param entities Entities to save
     */
    void save(Collection<T> entities);

    /**
     * Delete entity
     *
     * @param entity Entity to delete
     */
    int delete(T entity);

    /**
     * Delete collection of entities
     *
     * @param entities Entities to delete
     */
    int delete(Collection<T> entities);

    /**
     * Find entity by id
     *
     * @param id Id of entity
     * @return Entity or null, if not found
     */
    T findById(ID id);

    /**
     * Return all entities in database
     *
     * @return List of all entities in database
     */
    List<T> findAll();

    /**
     * Delete entity by id
     *
     * @param id Id of entity
     */
    int deleteById(ID id);

    /**
     * Delete by ids
     *
     * @param ids Ids of entity to delete
     */
    int deleteById(Collection<ID> ids);

    /**
     * Query object for entity
     *
     * @return Query object for entity
     */
    Query<T> query();

    /**
     * Delete entity physical
     *
     * @param entity Entity to delete
     */
    int hardDelete(T entity);

    /**
     * Delete entities
     *
     * @param entities Entities to delete
     */
    int hardDelete(List<T> entities);

    /**
     * Delete all entities from database
     */
    int deleteAll();

    /**
     * Physically delete all entities
     */
    int hardDeleteAll();
}
