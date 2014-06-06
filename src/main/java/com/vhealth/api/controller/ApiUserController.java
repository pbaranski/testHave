package com.vhealth.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vhealth.api.dto.UserDto;
import com.vhealth.api.dto.UserDtoMapper;
import com.vhealth.api.dto.UserDtoSave;
import com.vhealth.api.service.UserService;
import com.vhealth.api.utils.ExceptionsResolver;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = "/api/users")
@Validated
public class ApiUserController extends ExceptionsResolver {
    private static final Logger logger = Logger.getLogger(ApiUserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userName}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public UserDto jsonUser(@PathVariable String userName) {
        return userService.findByUserName(userName);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDto> jsonUsers() {
        return userService.findUsers();
    }

    @RequestMapping(consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity jsonCreateUser(@Valid @RequestBody UserDtoSave userDto) throws JsonProcessingException {
        userService.saveUser(UserDtoMapper.generateUserDtoFromUserDtoSave(userDto));
        return new ResponseEntity(userDto, HttpStatus.ACCEPTED);
    }

    //TODO how I can validate guy from uri? specially when validating object - how to join this together? i.e. @NewUserName String userName - this doesn't work here
    @RequestMapping(value = "/{userName}", consumes = "application/json", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<UserDto> jsonUpdateUser(@Valid @RequestBody UserDto userDto, @PathVariable String userName /*TODO userName in uri or in payload, or both :)?*/) {
        userService.updateUserWithCheck(userDto, userName);
        return new ResponseEntity("UserDto updated", HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{userName}/admin", consumes = "application/json", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> jsonChangeUserAdminRights(@RequestBody Boolean isAdmin, @PathVariable String userName) {
        userService.setAdminRights(userName, isAdmin);
        return new ResponseEntity(userName + " admin rights set to: " + isAdmin, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{userName}/admin", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> jsonCheckUserAdminRights(@PathVariable String userName) {
        boolean adminRights = userService.checkAdminRights(userName);
        return new ResponseEntity("Status of " + userName + "  admin rights = " + adminRights, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> jsonDeleteUser(@PathVariable String userName) {
        userService.deleteUserByName(userName);
        return new ResponseEntity("UserDto " + userName + " deleted", HttpStatus.OK);
    }
}

