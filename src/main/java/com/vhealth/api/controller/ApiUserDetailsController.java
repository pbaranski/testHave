package com.vhealth.api.controller;

import com.vhealth.api.dto.UserDto;
import com.vhealth.api.entity.Item;
import com.vhealth.api.service.ItemAdminService;
import com.vhealth.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/api/userDetails")
public class ApiUserDetailsController {
    @Autowired
    private UserService userService;
    @Autowired
    private ItemAdminService itemAdminService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public UserDto returnCurrentUser(Principal principal) {
        final String userName = principal.getName();
        return userService.findByUserName(userName);
    }

    @RequestMapping(value = "/items", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> returnCurrentUserItems() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return itemAdminService.findItemsByUserName(userName);
    }

}
