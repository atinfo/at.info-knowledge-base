package info.testing.automated.core.db.dao;

import info.testing.automated.core.db.entities.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Author: Sergey Kuts
 */
public interface DAO<T extends BaseEntity, ID extends Serializable> {
    T findById(ID id);

    List<T> findAll();

    void save(final T entity);
}
