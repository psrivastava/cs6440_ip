package com.gatech.cs6440.drugabuse.controller;

import com.gatech.cs6440.drugabuse.model.*;
import com.gatech.cs6440.drugabuse.service.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class APIController {

    // Logging
    static Logger logger = LoggerFactory.getLogger(APIController.class);

    @Autowired
    UserService userDetailService;
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ChatService chatService;

    // testing the API only
    @GetMapping("/date")
    public String hello() {
        Date date = new Date();
        return String.format("We're Live. It is %s", date.toString());
    }

    @GetMapping("/users")
    public List<UserDetails> getUserDetails() {
        logger.info("Got request to users endpoint");
        // Fetch it directly from the database
        List<UserDetails> userList = new ArrayList<>();

        try {
            userList = userDetailService.findAll();
            logger.info("Fetched Users");
            logger.info(userList.toString());
        } catch (Exception e) {
            logger.error("Something went terribly wrong while fetching USER details from the database.");
            e.printStackTrace();
        }

        return userList;
    }

    @GetMapping("/users/{id}")
    public UserDetails getUserById(@PathVariable Integer id) {

        UserDetails user = new UserDetails();

        try {
            user = userDetailService.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("Something went terribly wrong while fetching that USER from the database.");
            e.printStackTrace();
        }
        return user;
    }

    @GetMapping("/users/count")
    public HashMap<String, Integer> getCount() {
        logger.info("Got request to count endpoint");

        long userCount = 0;

        try {
            userCount = userDetailService.count();
            logger.info("Fetched Count");
        } catch (Exception e) {
            logger.error("Something went terribly wrong while fetching count from the database.");
            e.printStackTrace();
        }

        HashMap<String, Integer> count = new HashMap<>();
        count.put("count", (int) userCount);
        return count;
    }

    @GetMapping("/user-profile")
    public List<User> getUserProfiles() {
        logger.info("Got request to userprofile endpoint");
        // Fetch it directly from the database
        List<User> users = new ArrayList<>();

        try {
            users = userProfileService.findAll();
            logger.info("Fetched User profiles");
        } catch (Exception e) {
            logger.error("Something went terribly wrong while fetching USER details from the database.");
            e.printStackTrace();
        }

        return users;
    }

    @GetMapping("/user-profile/{id}")
    public User getUserProfileDetails(@PathVariable Integer id) {
        logger.info("Got request to userprofile endpoint");
        logger.info("Id is:" + id);
        // Fetch it directly from the database
        User user = new User();

        try {
            user = userProfileService.findById(id).orElse(null);
            logger.info("Fetched User profile for id:" + id);
        } catch (Exception e) {
            logger.error("Something went terribly wrong while fetching USER details from the database.");
            e.printStackTrace();
        }

        return user;
    }

    @GetMapping("/category")
    public List<Category> getCategories() {
        logger.info("Hitting Cat endpoint");

        List<Category> categories = new ArrayList<>();
        categories = categoryService.findAll();
        return categories;
    }

    @GetMapping("/category/{id}")
    public Category getCategories(@PathVariable int id) {
        logger.info("Hitting Cat endpoint for id");

        Category category = new Category();
        category = categoryService.findById(id).orElse(null);
        return category;
    }

    @PostMapping("/category")
    public Category getCategories(@RequestBody Category category) {
        logger.info("Hitting Cat to create a new category");
        categoryService.save(category);
        logger.info("Success: SAVED");
        return category;
    }

    @GetMapping("/chat/{id}")
    public List<Chat> getChats(@PathVariable int id) {
        logger.info("Hitting Chat endpoint");

        List<Chat> chats = new ArrayList<>();
        chats = chatService.findAll();

        return chats.stream().filter(c -> c.getCategory() == id).collect(Collectors.toList());
    }

    @PostMapping("/chat")
    public Chat getChats(@RequestBody Chat newChat) {
        logger.info("Hitting Cat to create a new category");
        chatService.save(newChat);
        logger.info("Success: SAVED");
        return newChat;
    }

}
