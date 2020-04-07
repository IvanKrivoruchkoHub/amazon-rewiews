package com.example.amazonreviews.controller;

import com.example.amazonreviews.service.ReviewService;
import com.example.amazonreviews.utils.WordsUtil;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {
    private static final Logger LOGGER = LogManager.getLogger(ReviewController.class);

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/users")
    public List<String> getMostActiveUsers(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "count", required = false, defaultValue = "100") Integer count) {
        Pageable pageable = PageRequest.of(page, count);
        return reviewService.findActiveUsers(1000, pageable);
    }

    @GetMapping("/goods")
    public List<String> getMostCommentedGoods(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "count", required = false, defaultValue = "100") Integer count) {
        Pageable pageable = PageRequest.of(page, count);
        return reviewService.findMostCommentedGoods(1000, pageable);
    }

    @GetMapping("/words")
    public List<String> getMostPopularWordsOfComments(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "count", required = false, defaultValue = "100") Integer count) {
        if (page < 0 || count <= 0) {
            throw new IllegalArgumentException("Count or page invalid values");
        }
        return reviewService.getMostPopularWords(page, count);
    }
}
