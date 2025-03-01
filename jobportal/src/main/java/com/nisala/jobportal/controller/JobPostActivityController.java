package com.nisala.jobportal.controller;

import com.nisala.jobportal.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobPostActivityController {

    private final UsersService usersService;

    @Autowired
    public JobPostActivityController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/dashboard/")
    public String searchJobs(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String currentUserName = authentication.getName();
            model.addAttribute("username", currentUserName);
        }

        model.addAttribute("user", usersService.getCurrentUserProfile());
        System.out.println("Dashboard");
        return "dashboard";
    }
}
