package com.github.timo_reymann.springboot.ebean.dao;

import io.ebean.EbeanServer;
import io.ebean.Query;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Timo Reymann
 * @since 14.12.17
 */
@Service
public class GenericDAO<T, ID extends Serializable> implements IDAO<T, ID> {
    private Class<T> clazz;
    private EbeanServer ebeanServer;

    public GenericDAO<T, ID> init(Class<T> clazz, EbeanServer ebeanServer) {
        this.clazz = clazz;
        this.ebeanServer = ebeanServer;
        return this;
    }

    /**
     * Save an entity
     *
     * @param entity Entity zum Speichern
     */
    @Override
    public void save(T entity) {
        ebeanServer.save(entity);
    }

    /**
     * Save collection of entities
     *
     * @param entities Entities to save
     */
    @Override
    public void save(Collection<T> entities) {
        ebeanServer.save(entities);
    }

    /**
     * Delete entity
     *
     * @param entity Entity to delete
     * @return Number of affected rows
     */
    @Override
    public int delete(T entity) {
        return toInt(ebeanServer.delete(entity));
    }

    /**
     * Delete collection of entities
     *
     * @param entities Entities to delete
     * @return Number of affected rows
     */
    @Override
    public int delete(Collection<T> entities) {
        return toInt(ebeanServer.delete(entities));
    }

    /**
     * Find entity by id
     *
     * @param id Id of entity
     * @return Entity or null, if not found
     */
    @Override
    public T findById(ID id) {
        return query().where()
                .idEq(id)
                .findOne();
    }

    /**
     * Delete entity by id
     *
     * @param id Id of entity
     * @return Number of affected rows
     */
    @Override
    public int deleteById(ID id) {
        return query().where()
                .idEq(id)
                .delete();
    }

    /**
     * Delete by ids
     *
     * @param ids Ids of entity to delete
     * @return Number of affected rows
     */
    @Override
    public int deleteById(Collection<ID> ids) {
        return query().where()
                .idIn(ids)
                .delete();
    }

    /**
     * Query object for entity
     *
     * @return Query object for entity
     */
    @Override
    public Query<T> query() {
        return ebeanServer.find(clazz);
    }

    /**
     * Delete entity physical
     *
     * @param entity Entity to delete
     * @return Number of affected rows
     */
    @Override
    public int hardDelete(T entity) {
        return toInt(ebeanServer.deletePermanent(entity));
    }

    /**
     * Delete entities
     *
     * @param entities Entities to delete
     * @return Number of affected rows
     */
    @Override
    public int hardDelete(List<T> entities) {
        return toInt(ebeanServer.deletePermanent(entities));
    }

    /**
     * Delete all entities from database
     *
     * @return Number of affected rows
     */
    @Override
    public int deleteAll() {
        return query().delete();
    }

    /**
     * Physically delete all entities
     *
     * @return Number of affected rows
     */
    @Override
    public int hardDeleteAll() {
        return query().setIncludeSoftDeletes()
                .delete();
    }

    /**
     * Return all entities in database
     *
     * @return List of all entities in database
     */
    @Override
    public List<T> findAll() {
        return query().findList();
    }

    private int toInt(Boolean bool) {
        return bool ? 1 : 0;
    }
}
