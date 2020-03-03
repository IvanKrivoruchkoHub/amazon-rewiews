package com.example.amazonreviews.service;

import com.example.amazonreviews.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> saveAll(List<Review> reviews);

    Review save (Review review);

    public List<String> findActiveUsers(Integer count);

    public List<String> findMostCommentedGoods(Integer count);

}
