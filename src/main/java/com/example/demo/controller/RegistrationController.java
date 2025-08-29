package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.validation.Errors;

@Controller
public class RegistrationController {
    @GetMapping("/register")
    public String showRegistrationForm(WebRequest webRequest, Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    public ModelAndView registerUserAccount( 
            @ModelAttribute("user")
            @Valid
            UserDto userDto,
            HttpServletRequest request,
            Errors errors) {
        
        // Check validation
        if (errors.hasErrors()) {
            return new ModelAndView("registration", "user", userDto);
        }

        // Handling registration
        //

        // Return view if success
        return new ModelAndView("", "user", userDto);
    }
}
