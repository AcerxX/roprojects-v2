package com.roprojects.fullv2.controller;

import com.roprojects.fullv2.entity.User;
import com.roprojects.fullv2.service.user.UserService;
import com.roprojects.fullv2.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthenticationController {
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public AuthenticationController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/register")
    public String registration(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registration(Model model, @Valid User user, Errors errors) {
        userValidator.validate(user, errors);

        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);

            return "register";
        }
        userService.save(user);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
}
