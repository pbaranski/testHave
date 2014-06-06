package com.vhealth.api.entity;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Audit implements Auditable{

    private static final long serialVersionUID = -2862671438138322400L;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "created_on", nullable = false, updatable = false)
    private DateTime createdOn;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "modified_on", nullable = false)
    private DateTime modifiedOn;

    @Override
    public DateTime getCreatedOn() {
        return createdOn;
    }

    @Override
    public void setCreatedOn(DateTime date) {
        createdOn = date;
    }

    @Override
    public DateTime getModifiedOn() {
        return modifiedOn;
    }

    @Override
    public void setModifiedOn(DateTime date) {
        modifiedOn = date;
    }
}
