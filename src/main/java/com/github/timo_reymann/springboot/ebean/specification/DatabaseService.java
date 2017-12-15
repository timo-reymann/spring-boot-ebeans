package com.github.timo_reymann.springboot.ebean.specification;

import com.github.timo_reymann.springboot.ebean.dao.GenericDAO;
import io.ebean.EbeanServer;
import lombok.AccessLevel;
import lombok.Getter;

import java.io.Serializable;

/**
 * Service for database access
 *
 * @author Timo Reymann
 * @since 14.12.17
 */
public abstract class DatabaseService<T, ID extends Serializable> {
    /**
     * Data access object
     */
    @Getter(AccessLevel.PROTECTED)
    protected GenericDAO<T, ID> dao;

    /**
     * Create database service
     *
     * @param dao         Dao for entity
     * @param ebeanServer Ebean server instance
     */
    public DatabaseService(GenericDAO<T, ID> dao, EbeanServer ebeanServer) {
        this.dao = dao;
        this.dao.init(provideClass(), ebeanServer);
    }

    /**
     * Class object of instance
     *
     * @return Class object
     */
    abstract protected Class<T> provideClass();
}
