package com.roprojects.fullv2.controller.base;

import com.roprojects.fullv2.entity.User;
import com.roprojects.fullv2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.constraints.NotNull;


@Controller
public class UserBoundController {
    @NotNull
    @Autowired // This one stays here, not above a constructor
    private UserRepository userRepository;

    @ModelAttribute("user")
    public User userInfo() {
        User user = null;

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            user = this.userRepository.findByEmail(((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername());
        }

        return user;
    }
}
