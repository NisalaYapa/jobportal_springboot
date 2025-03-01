package com.nisala.jobportal.controller;

import com.nisala.jobportal.entity.Users;
import com.nisala.jobportal.entity.UsersType;
import com.nisala.jobportal.services.UsersService;
import com.nisala.jobportal.services.UsersTypeService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    @Autowired //Not mandatory
    public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model){
        List<UsersType> usersTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user", new Users());
        return "register";


    }

    @PostMapping("/register/new")
    public String userRegistration (@Valid Users users, Model model){
        Optional<Users> optionalUsers = usersService.getUsersByEmail(users.getEmail());

        if (optionalUsers.isPresent()){
            model.addAttribute("error", "Email already registered. Try another email or login");
            List<UsersType> usersTypes = usersTypeService.getAll();
            model.addAttribute("getAllTypes", usersTypes);
            model.addAttribute("user", new Users());
            return "register";
        }

        usersService.addNew(users);

        return "dashboard";
    }


}
