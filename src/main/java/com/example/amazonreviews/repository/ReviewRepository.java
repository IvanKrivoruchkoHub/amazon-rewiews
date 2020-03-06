package com.example.amazonreviews.repository;

import com.example.amazonreviews.entity.Comment;
import com.example.amazonreviews.entity.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT profile_name FROM REVIEWS group by profile_name " +
            "order by count(profile_name) desc limit ?1", nativeQuery = true)
    public List<String> findActiveUsers(Integer count);

    @Query(value = "SELECT product_id FROM REVIEWS group by product_id " +
            "order by count(product_id) desc limit ?1", nativeQuery = true)
    public List<String> findMostCommentedGoods(Integer count);

    @Query(value = "SELECT new java.lang.String(text) FROM Review")
    public List<String> findAllComments();

}
