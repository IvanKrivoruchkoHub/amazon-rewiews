package com.example.amazonreviews.utils;

import com.example.amazonreviews.entity.Review;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class CustomCSVParser {
    public List<Review> parseCSVFile(String fileName) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/".concat(fileName));
        InputStreamReader input = new InputStreamReader(inputStream);
        CSVParser csvParser = null;
        csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(input);
        List<Review> reviews = new ArrayList<>();
        for (CSVRecord record : csvParser) {
            Review review = new Review();
            review.setProductId(record.get("ProductId"));
            review.setUserId(record.get("UserId"));
            review.setProfileName(record.get("ProfileName"));
            review.setText(record.get("Text"));
            reviews.add(review);
        }
        return reviews;
    }
}
