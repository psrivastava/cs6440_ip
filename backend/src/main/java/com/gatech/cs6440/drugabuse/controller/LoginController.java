package com.gatech.cs6440.drugabuse.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@CrossOrigin
public class LoginController {

    @GetMapping("/login")
    public HashMap<String, String> checkLoginEndpoint() {

        HashMap<String,String> loginResponse = new HashMap<>();
        loginResponse.put("result", "success");

        return loginResponse;
    }

    @PostMapping("/login")
    public HashMap<String, String> attemptLogin() {

        HashMap<String,String> loginResponse = new HashMap<>();
        loginResponse.put("result", "success");

        return loginResponse;
    }

}
