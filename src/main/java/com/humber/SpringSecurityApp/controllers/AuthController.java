package com.humber.SpringSecurityApp.controllers;

import com.humber.SpringSecurityApp.models.MyUser;
import com.humber.SpringSecurityApp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController implements ErrorController {

    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/error")
    public String error403(){
        return "auth/error403";
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) String msg){
        model.addAttribute("msg", msg);
        return "auth/login";
    }

    @GetMapping("/logout")
    public String customLogout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // Perform logout logic if needed
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return "redirect:/restaurant/menu";
    }

    //registration get mapping
    @GetMapping("/register")
    public String register(Model model, @RequestParam
                           (required = false) String error){
        model.addAttribute("user", new MyUser());
        model.addAttribute("error", error);
        return "auth/register";
    }

    //registration post mapping
    @PostMapping("/register")
    public String registerPost(Model model, @ModelAttribute MyUser user){
        //check if user exists
        if(userService.userExists(user.getUsername())){
            return "redirect:/register?error=User already exists";
        }
        //save user to database
        userService.saveUser(user);
        //redirect to login page
        return "redirect:/login?msg=User created successfully";
    }
}
