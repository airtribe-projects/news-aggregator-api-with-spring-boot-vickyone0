package com.example.NewsAggregatorAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.NewsAggregatorAPI.entity.User;
import com.example.NewsAggregatorAPI.entity.UserDTO;
import com.example.NewsAggregatorAPI.service.AuthenticationService;

@RestController
public class UserController {

    @Autowired
    private AuthenticationService _authenticationservice;

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDTO userDTO){

        User registerUser =_authenticationservice.registerUser(userDTO);

        return registerUser;


    }

    @PostMapping("/signin")
    public String signin(@RequestParam("username") String username, @RequestParam("password") String password){
        return  _authenticationservice.signinUser(username,password);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/signin")
    public String signin(){
        return "hellos";
    }

   
    
}
