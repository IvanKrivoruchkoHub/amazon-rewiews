package com.example.amazonreviews.controller;

import com.example.amazonreviews.entity.Review;
import com.example.amazonreviews.entity.Role;
import com.example.amazonreviews.entity.User;
import com.example.amazonreviews.service.ReviewService;
import com.example.amazonreviews.service.RoleService;
import com.example.amazonreviews.service.UserService;
import com.example.amazonreviews.utils.CustomCSVParser;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class InjectController {
    private static final Logger LOGGER = LogManager.getLogger(InjectController.class);

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CustomCSVParser customCSVParser;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/inject")
    @ResponseBody
    public void init() {
        Role userRole = new Role("USER");
        userRole = roleService.save(userRole);
        Role adminRole = new Role("ADMIN");
        adminRole = roleService.save(adminRole);

        User user = new User();
        user.setLogin("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.addRole(userRole);
        userService.save(user);

        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.addRole(adminRole);
        userService.save(admin);
        try {
            long startReading = System.currentTimeMillis();
            List<Review> reviews = customCSVParser.parseCSVFile("Reviews.csv");
            LOGGER.info("Parsing - " + (System.currentTimeMillis() - startReading) * 0.001);
            long startSaving = System.currentTimeMillis();
            reviewService.saveAll(reviews);
            LOGGER.info("Saving - " + (System.currentTimeMillis() - startSaving) * 0.001);
        } catch (IOException e) {
            LOGGER.error("Can't parse file", e);
        }
    }
}
