package com.aman.majorProject.hasJob.controller;

import com.aman.majorProject.hasJob.entity.Users;
import com.aman.majorProject.hasJob.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/loginForm")
    public String createLoginForm() {
        return "login";
    }

    @GetMapping("/registerForm")
    public String createRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Users user,
                               @RequestParam("confirmPassword") String confirmPassword,
                               Model model) {

        if (confirmPassword.equals(user.getPassword()) == false) {
            model.addAttribute("error", "Password Mismatch");
            return "register";
        } else {
            String password = user.getPassword();
            user.setPassword("{noop}" + password);
            user.setAuthority("ROLE_USER");
            user.setEnabled(true);
            usersRepository.save(user);
            return "redirect:/user/loginForm";
        }
    }
}
