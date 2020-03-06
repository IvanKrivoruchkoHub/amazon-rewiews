package com.example.amazonreviews.service;

import com.example.amazonreviews.entity.Review;
import com.example.amazonreviews.repository.ReviewRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> saveAll(List<Review> reviews) {
        return reviewRepository.saveAll(reviews);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<String> findActiveUsers(Integer count) {
        return reviewRepository.findActiveUsers(count);
    }

    @Override
    public List<String> findMostCommentedGoods(Integer count) {
        return reviewRepository.findMostCommentedGoods(1000);
    }

    @Override
    public List<String> findAllComments() {
        return reviewRepository.findAllComments();
    }
}
