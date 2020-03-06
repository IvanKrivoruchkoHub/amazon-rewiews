package com.example.amazonreviews.utils;

import java.util.List;
import java.util.Map;

public class WordCountThread extends Thread {
    private Map<String, Integer> wordCount;
    private List<String> comments;
    private int startIndex;
    private int endIndex;

    public WordCountThread(Map<String, Integer> wordCount, List<String> comments, int startIndex, int endIndex) {
        this.wordCount = wordCount;
        this.comments = comments;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            String[] commentWords = comments.get(i)
                    .toLowerCase()
                    .replaceAll("[^a-z]+", " ")
                    .split(" ");
            for (String word : commentWords) {
                Integer count = wordCount.get(word);
                if (count == null) {
                    count = 0;
                }
                wordCount.put(word, ++count);
            }
        }
    }
}