package com.vhealth.api.dao;

import java.io.Serializable;
import java.util.List;

public interface AbstractDao<E, I extends Serializable> {

    E findById(I id);

    void saveOrUpdate(E e);

    void delete(E e);

    List<E> findByCriteria();

    List<E> findByCriteria(String column, String string);

    E saveAndReturnObject(E e);

}
