package com.vhealth.api.dao.impl;


import com.vhealth.api.dao.UserDao;
import com.vhealth.api.entity.User;
import com.vhealth.api.utils.UserDetailsExtractor;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, String> implements UserDao {

    protected UserDaoImpl() {
        super(User.class);
    }

    @Override
    public void updateUser(User user) {
        saveOrUpdate(user);
    }

    @Override
    public void deleteUserByName(String userName) {
        User user = findById(userName);
        delete(user);
    }

    @Override
    public User saveUser(User user) {
        return saveAndReturnObject(user);
    }

    @Override
    public List<User> findUsers() {
        return findByCriteria();
    }

    @Override
    @Nullable
    public User findById(String userName) {
        return entityManager.find(User.class, userName);
    }

    @Override
    public User findUser(String userName) {
        return findById(userName);
    }

    @Override
    public void setUserIsAdmin(String userName, boolean status) {
        User user = findById(userName);
        user.setAdmin(status);
        saveOrUpdate(user);
    }

    @Override
    public boolean userAdminRights(String userName) {
        User user = findById(userName);
        return user.isAdmin();
    }

    @Override
    public User getLoggedUser() {
        return (findById(UserDetailsExtractor.geUserNameOfLoggedUser()));
    }


}