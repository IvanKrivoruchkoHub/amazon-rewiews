package com.example.amazonreviews.service;

import com.example.amazonreviews.entity.Review;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface ReviewService {
    List<Review> saveAll(List<Review> reviews);

    Review save (Review review);

    public List<String> findActiveUsers(Integer count, Pageable pageable);

    public List<String> findMostCommentedGoods(Integer count, Pageable pageable);

    public List<String> findAllComments();

    public List<String> getMostPopularWords(int page, int count);
}
