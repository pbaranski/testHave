package com.vhealth.api.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsExtractor {

    public static String geUserNameOfLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
//TODO don't know if this else statement is needed since security check if user exist so if principal will not contain username then security should be the layer what prevent such situation
            return principal.toString();
        }
    }

}
