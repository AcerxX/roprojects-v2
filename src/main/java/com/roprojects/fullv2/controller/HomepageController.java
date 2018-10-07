package com.roprojects.fullv2.controller;

import com.roprojects.fullv2.controller.base.UserBoundController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class HomepageController extends UserBoundController {
    @GetMapping("/dashboard")
    public String welcome(Model model) {
        return "app";
    }

    @GetMapping("/")
    public RedirectView index() {
        return new RedirectView("dashboard");
    }
}
