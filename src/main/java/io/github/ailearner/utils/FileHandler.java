package io.github.ailearner.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {
    public List<String> readWordsFromFile(String file_path) {
        List<String> allWordsInFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allWordsInFile.addAll(
                        Arrays.asList(line.split("\\s+"))
                );
            }
        } catch (Exception e) {
            throw new Error(e);
        }
        return allWordsInFile;
    }
}