package com.example.amazonreviews.utils;

import com.example.amazonreviews.controller.InjectController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class WordsUtil {
    private static final Logger LOGGER = LogManager.getLogger(InjectController.class);

    public List<String> findMostPopularWords(List<String> comments) {
        Map<String, Integer> wordAndCountMap = new ConcurrentHashMap<>();
        int sizeSubList = comments.size() / 5;
        Thread thread1 = new WordCountThread(wordAndCountMap, comments, 0, sizeSubList);
        Thread thread2 = new WordCountThread(wordAndCountMap, comments, ++sizeSubList, sizeSubList * 2);
        Thread thread3 = new WordCountThread(wordAndCountMap, comments, sizeSubList * 2, sizeSubList * 3);
        Thread thread4 = new WordCountThread(wordAndCountMap, comments, sizeSubList * 3, sizeSubList * 4);
        Thread thread5 = new WordCountThread(wordAndCountMap, comments, sizeSubList * 4, comments.size());

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }

        return wordAndCountMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .limit(1000)
                .collect(Collectors.toList());
    }
}
