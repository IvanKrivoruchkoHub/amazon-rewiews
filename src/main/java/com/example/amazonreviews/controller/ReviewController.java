package com.example.amazonreviews.controller;

import com.example.amazonreviews.service.ReviewService;
import com.example.amazonreviews.utils.WordsUtil;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {
    private static final Logger LOGGER = LogManager.getLogger(ReviewController.class);

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private WordsUtil wordsUtil;

    @GetMapping("/users")
    public List<String> getMostActiveUsers() {
        return reviewService.findActiveUsers(1000);
    }

    @GetMapping("/goods")
    public List<String> getMostCommentedGoods() {
        return reviewService.findMostCommentedGoods(1000);
    }

    @GetMapping("/words")
    public List<String> getMostPopularWords() {
        long startGettingFromDb = System.currentTimeMillis();
        List<String> allComments = reviewService.findAllComments();
        LOGGER.info("Get comments from bd - " + (System.currentTimeMillis() - startGettingFromDb) * 0.001);

        long startFindPopularWords = System.currentTimeMillis();
        List<String> popularWords = wordsUtil.findMostPopularWords(allComments);
        LOGGER.info("Finding popular words - "+ (System.currentTimeMillis() - startFindPopularWords) * 0.001);

        return popularWords;
    }
}
