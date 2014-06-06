package com.vhealth.api.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vhealth.api.utils.validators.NewUserName;
import org.joda.time.DateTime;

@JsonSerialize
public class UserDto extends UserDtoCore {
    @NewUserName
    private String userName;
    private DateTime createdOn;

    public DateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(DateTime createdOn) {
        this.createdOn = createdOn;
    }

}
