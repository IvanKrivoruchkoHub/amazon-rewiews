package com.example.amazonreviews.service.impl;

import com.example.amazonreviews.entity.Review;
import com.example.amazonreviews.repository.ReviewRepository;
import com.example.amazonreviews.service.ReviewService;
import com.example.amazonreviews.utils.WordsUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private WordsUtil wordsUtil;

    @Override
    public List<Review> saveAll(List<Review> reviews) {
        return reviewRepository.saveAll(reviews);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<String> findActiveUsers(Integer count, Pageable pageable) {
        return reviewRepository.findActiveUsers(count, pageable);
    }

    @Override
    public List<String> findMostCommentedGoods(Integer count, Pageable pageable) {
        return reviewRepository.findMostCommentedGoods(1000, pageable);
    }

    @Override
    public List<String> findAllComments() {
        return reviewRepository.findAllComments();
    }

    @Override
    public List<String> getMostPopularWords(int page, int count) {
        List<String> popularWords = wordsUtil.findMostPopularWords(findAllComments());
        int beginIndex = page * count;
        if (beginIndex >= popularWords.size()) {
            throw new IndexOutOfBoundsException("begin index are bigger than count of words");
        }
        int endIndex = Math.min(beginIndex + count, popularWords.size());
        return popularWords.subList(beginIndex, endIndex);
    }


}
