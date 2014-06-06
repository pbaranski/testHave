package com.vhealth.api.dto;

import com.vhealth.api.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDtoMapper {

    public static UserDto generateUserDtoFromUser(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setCreatedOn(user.getCreatedOn());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

    public static UserDto generateUserDtoFromUserDtoSave(UserDtoSave user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

    public static User generateUserFromDto(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setCreatedOn(userDto.getCreatedOn());
        user.setPassword(userDto.getPassword());

        return user;

    }

    public static List<UserDto> convertUserListToUserDtoList(List<User> users) {
        List<UserDto> usersDto = new ArrayList<UserDto>();
        for (User user : users) {
            usersDto.add(UserDtoMapper.generateUserDtoFromUser(user));
        }
        return usersDto;
    }
}