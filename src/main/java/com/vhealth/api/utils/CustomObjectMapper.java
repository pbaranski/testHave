package com.vhealth.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;


public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        super();
        registerModule(new JodaModule());
    }
}
