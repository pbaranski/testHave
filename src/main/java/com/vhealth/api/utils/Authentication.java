package com.vhealth.api.utils;

import com.vhealth.api.dao.UserDao;
import com.vhealth.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Authentication implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        User userPass = userDao.findById(username);
        if (userPass.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        UserDetails user = new org.springframework.security.core.userdetails.User(username, userPass.getPassword(), true, true, true, true, authorities);
        return user;
    }
}
