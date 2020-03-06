package com.example.amazonreviews.controller;

import com.example.amazonreviews.entity.Review;
import com.example.amazonreviews.service.ReviewService;
import com.example.amazonreviews.utils.CustomCSVParser;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InjectController {
    private static final Logger LOGGER = LogManager.getLogger(InjectController.class);
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CustomCSVParser customCSVParser;

    @PostConstruct
    public void init() {
        try {
            long startReading = System.currentTimeMillis();
            List<Review> reviews = customCSVParser.parseCSVFile("Reviews.csv");
            LOGGER.info(("Parsing - " + (System.currentTimeMillis() - startReading) * 0.001));
            long startSaving = System.currentTimeMillis();
            reviewService.saveAll(reviews);
            LOGGER.info(("Save to db - " + (System.currentTimeMillis() - startSaving) * 0.001));
        } catch (IOException e) {
            LOGGER.error("Can't parse file", e);
        }

    }
}
