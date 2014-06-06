package com.vhealth.api.service.impl;

import com.vhealth.api.dao.UserDao;
import com.vhealth.api.dto.UserDto;
import com.vhealth.api.dto.UserDtoMapper;
import com.vhealth.api.service.UserService;
import com.vhealth.api.utils.exceptions.InvalidPayloadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    @Override
    public UserDto findByUserName(String userName) {
        return UserDtoMapper.generateUserDtoFromUser(userDao.findUser(userName));
    }

    @Override
    @Transactional(readOnly = false)
    public void saveUser(UserDto userDto) {
        userDao.saveUser(UserDtoMapper.generateUserFromDto(userDto));
    }

    @Override
    @Transactional(readOnly = false)
    public void updateUser(UserDto userDto) {
        userDao.updateUser(UserDtoMapper.generateUserFromDto(userDto));
    }

    @Override
    @Transactional(readOnly = false)
    public void updateUserWithCheck(UserDto userDto, String userName) {
        if (userDto.getUserName().equals(userName)) {
            updateUser(userDto);
        }
        throw new InvalidPayloadException("userName in URL and in payload didn't match");
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteUserByName(String userName) {
        if (userDao.findUser(userName) == null) {
            throw new InvalidPayloadException("Deleting nonexistent user");
        }
        userDao.deleteUserByName(userName);
    }

    @Override
    @Transactional(readOnly = false)
    public void setAdminRights(String userName, boolean status) {
        userDao.setUserIsAdmin(userName, status);
    }

    @Override
    public List<UserDto> findUsers() {
        return UserDtoMapper.convertUserListToUserDtoList(userDao.findUsers());
    }

    @Override
    public boolean checkAdminRights(String userName) {
        return userDao.userAdminRights(userName);
    }
}

