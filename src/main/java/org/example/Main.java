package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String filename = "src/main/resources/quest2-input.txt";
        try {
            countWords(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void countWords(String filename) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line).append(" ");
            }
        }

        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(text.toString().toLowerCase());
        Map<String, Integer> wordCounts = new HashMap<>();
        while (matcher.find()) {
            String word = matcher.group();
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> sortedWordCounts = new ArrayList<>(wordCounts.entrySet());
        sortedWordCounts.sort((e1, e2) -> {
            int countCompare = e2.getValue().compareTo(e1.getValue());
            if (countCompare == 0) {
                return e1.getKey().compareTo(e2.getKey());
            } else {
                return countCompare;
            }
        });

        for (Map.Entry<String, Integer> entry : sortedWordCounts) {
            System.out.println(entry.getValue() + " " + entry.getKey());
        }
    }
}
