package com.vhealth.api.controller;

import com.vhealth.api.dto.UserDto;
import com.vhealth.api.entity.User;
import com.vhealth.api.service.UserService;
import com.vhealth.api.utils.ExceptionsResolver;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UiUserController extends ExceptionsResolver {

    private static final Logger logger = Logger.getLogger(UiUserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/ui/users", method = RequestMethod.GET)
    public String showUsersForm(Model model) {
        model.addAttribute("user", new User());
        List<UserDto> usersDtoList = userService.findUsers();
        model.addAttribute("users", usersDtoList);
        return "/ui/users/index";
    }

    @RequestMapping(value = "/ui/users", method = RequestMethod.POST)
    public String searchUser(Model model, String userName) {
        model.addAttribute("user", userService.findByUserName(userName));
        return "/ui/users/user";
    }

    @RequestMapping(value = "/ui/users/{userName}", method = RequestMethod.GET)
    public String showUser(Model model, @PathVariable String userName) {
        UserDto userDto = userService.findByUserName(userName);
        model.addAttribute("user", userDto);
        return "/ui/users/user";
    }

    @RequestMapping(value = "/ui/users/create", method = RequestMethod.GET)
    public String saveUser(Model model) {
        model.addAttribute("user", new User());
        return "ui/users/create";

    }

    @RequestMapping(value = "/ui/users/create", method = RequestMethod.POST)
    public String saveUser(Model model, UserDto userDto, RedirectAttributes redirectAttributes) {
        userService.saveUser(userDto);
        if (userService.findByUserName(userDto.getUserName()).equals(userDto)) {
            redirectAttributes.addFlashAttribute("saved", "success");
        } else {
            redirectAttributes.addFlashAttribute("saved", "error");
        }
        return "redirect:/ui/users";

    }

    @RequestMapping(value = "/ui/users/{userName}/edit", method = RequestMethod.GET)
    public String updateUser(Model model, @PathVariable String userName) {
        UserDto userDto = userService.findByUserName(userName);
        model.addAttribute("user", userDto);
        return "/ui/users/update";
    }

    @RequestMapping(value = "/ui/users/{userName}/update", method = RequestMethod.POST)
    public String updateUser(Model model, UserDto userDto, RedirectAttributes redirectAttributes) {
        userService.updateUser(userDto);
        redirectAttributes.addFlashAttribute("saved", "success");
        return "redirect:/ui/users/" + userDto.getUserName();
    }

    @RequestMapping(value = "/ui/users/{userName}/delete", method = RequestMethod.GET)
    public String deleteUser(Model model, @PathVariable String userName, RedirectAttributes redirectAttributes) {
        userService.deleteUserByName(userName);
        redirectAttributes.addFlashAttribute("deleted", "success");
        return "redirect:/ui/users";


    }


}