package com.vhealth.api.entity;

import org.joda.time.DateTime;

public interface Auditable<T> {

    DateTime getCreatedOn();

    void setCreatedOn(DateTime date);

    DateTime getModifiedOn();

    void setModifiedOn(DateTime date);
 
/*    T getCreationUser();
 
    void setCreationUser(T user);
 
    T getModificationUser();
 
    void setModificationUser(T user);
 
    T getCurrentUser();*/
}
