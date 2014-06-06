package com.vhealth.api.dao.impl;

import com.vhealth.api.dao.AbstractDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDaoImpl<E, I extends Serializable> implements AbstractDao<E, I> {

    @PersistenceContext
    EntityManager entityManager;
    private Class<E> entityClass;

    protected AbstractDaoImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public E findById(I id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public void saveOrUpdate(E e) {
        entityManager.merge(e);
    }

    @Override
    public E saveAndReturnObject(E e) {
        entityManager.persist(e);
        return e;
    }

    @Override
    public void delete(E e) {
        entityManager.remove(e);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findByCriteria() {
        return entityManager.createQuery("from " + entityClass.getName()).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findByCriteria(String column, String string) {
        return entityManager.createQuery("from " + entityClass.getName() + " where " + column + " like '" + string + "'").getResultList();


        //TODO such construction doesn't work  - it's a pity because looks nice - I need to find some time for investigating it
//        return entityManager
//                .createQuery("from " + entityClass.getName() + " where ?2 like ?3")
//                .setParameter(2, column)
//                .setParameter(3, string)
//                .getResultList();
    }

}