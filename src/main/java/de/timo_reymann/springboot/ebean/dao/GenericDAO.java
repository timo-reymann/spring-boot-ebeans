package de.timo_reymann.springboot.ebean.dao;

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

    @Override
    public void save(T entity) {
        ebeanServer.save(entity);
    }

    @Override
    public void save(Collection<T> entities) {
        ebeanServer.save(entities);
    }

    @Override
    public int delete(T entity) {
        return toInt(ebeanServer.delete(entity));
    }

    @Override
    public int delete(Collection<T> entities) {
        return toInt(ebeanServer.delete(entities));
    }

    @Override
    public T findById(ID id) {
        return query().where()
                .idEq(id)
                .findOne();
    }

    @Override
    public int deleteById(ID id) {
        return query().where()
                .idEq(id)
                .delete();
    }

    @Override
    public int deleteById(Collection<ID> ids) {
        return query().where()
                .idIn(ids)
                .delete();
    }

    @Override
    public Query<T> query() {
        return ebeanServer.find(clazz);
    }

    @Override
    public int hardDelete(T entity) {
        return toInt(ebeanServer.deletePermanent(entity));
    }

    @Override
    public int hardDelete(List<T> entities) {
        return toInt(ebeanServer.deletePermanent(entities));
    }

    @Override
    public int deleteAll() {
        return query().delete();
    }

    @Override
    public int hardDeleteAll() {
        return query().setIncludeSoftDeletes()
                .delete();
    }

    @Override
    public List<T> findAll() {
        return query().findList();
    }

    private int toInt(Boolean bool) {
        return bool ? 1 : 0;
    }
}
