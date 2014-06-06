package com.vhealth.api.service;

import com.vhealth.api.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto findByUserName(String userName);

    void saveUser(UserDto userDto);

    void updateUser(UserDto userDto);

    void deleteUserByName(String userName);

    void setAdminRights(String userName, boolean status);

    List<UserDto> findUsers();

    boolean checkAdminRights(String userName);

    void updateUserWithCheck(UserDto userDto, String userName);
}
