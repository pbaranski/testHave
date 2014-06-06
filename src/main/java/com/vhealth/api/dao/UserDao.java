package com.vhealth.api.dao;

import com.vhealth.api.entity.User;

import java.util.List;

public interface UserDao extends AbstractDao<User, String> {
    void updateUser(User user);

    void deleteUserByName(String userName);

    User saveUser(User user);

    List<User> findUsers();

    User findUser(String userName);

    void setUserIsAdmin(String userName, boolean status);

    boolean userAdminRights(String userName);

    User getLoggedUser();
}
