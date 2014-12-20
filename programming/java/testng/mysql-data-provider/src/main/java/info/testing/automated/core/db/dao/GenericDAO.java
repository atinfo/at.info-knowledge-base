package info.testing.automated.core.db.dao;

import info.testing.automated.core.db.entities.BaseEntity;
import info.testing.automated.core.db.utils.HibernateUtils;
import info.testing.automated.core.enums.Schema;
import org.hibernate.*;

import java.io.Serializable;
import java.util.List;

/**
 * Author: Sergey Kuts
 */
public class GenericDAO<T extends BaseEntity, ID extends Serializable> implements DAO<T, ID> {

    private Class<T> entity;
    private Session session;
    private Schema schema;

    public GenericDAO(final Class<T> entity, final Schema schema) {
        this.entity = entity;
        this.schema = schema;
    }

    private Session createNewSession() {
        return HibernateUtils.getSessionFactory(schema).getCurrentSession();
    }

    private void startNewTransaction(final Session session) {
        session.beginTransaction();
    }

    private void createNewSessionAndTransaction() {
        session = createNewSession();

        if (session != null) {
            startNewTransaction(session);
        }
    }

    private void commitTransaction() {
        if (session != null) {
            session.getTransaction().commit();
        }
    }

    private void rollbackTransaction() {
        if (session != null && session.getTransaction().isActive()) {
            session.getTransaction().rollback();
        }
    }

    @SuppressWarnings("unchecked")
    public T findById(final ID id) {
        T object = null;

        try {
            createNewSessionAndTransaction();
            if (session != null) {
                object = (T) session.get(entity, id);
            }
            commitTransaction();
        } catch (HibernateException e) {
            rollbackTransaction();
            object = null;
        }

        return object;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        List<T> objects = null;

        try {
            createNewSessionAndTransaction();
            if (session != null) {
                objects = (List<T>) session.createQuery(String.format("from %s", entity.getSimpleName())).list();
            }
            commitTransaction();
        } catch (HibernateException e) {
            rollbackTransaction();
            objects = null;
        }

        return objects;
    }

    public void save(final T entity) {
        try {
            createNewSessionAndTransaction();
            if (session != null && entity != null) {
                session.merge(entity);
            }
            commitTransaction();
        } catch (HibernateException e) {
            rollbackTransaction();
        }
    }
}
