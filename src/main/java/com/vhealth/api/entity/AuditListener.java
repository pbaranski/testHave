package com.vhealth.api.entity;

import org.joda.time.DateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditListener<T> {


    @PrePersist
    void onPrePersist(Object o) {
        if (o instanceof Auditable) {
            ((Auditable<T>) o).setCreatedOn(new DateTime());
            onPreUpdate(o);
        }
    }

    @PreUpdate
    void onPreUpdate(Object o) {
        if (o instanceof Auditable) {
            ((Auditable<T>) o).setModifiedOn(new DateTime());
        }
    }


}
