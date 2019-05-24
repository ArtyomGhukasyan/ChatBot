package com.aca.chatbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Repository {

    private static final Repository repository = new Repository();
    private final String SEPARATOR = "/";
    private String dictionaryPath = "dictionary.txt";

    public void setDictionaryPath(String dictionaryPath) {
        this.dictionaryPath = dictionaryPath;
    }

    private Repository() {
    }

    public static Repository getRepository() {
        return repository;
    }

    public Map<String, String> getFromDB() {

        Map<String, String> myDictionary = new HashMap<>();
        File database = new File(dictionaryPath);
        Scanner scanner = null;
        String[] questionAnswer;

        try {
            scanner = new Scanner(database);

            while (scanner.hasNext()) {
                questionAnswer = scanner.nextLine().split("/");
                myDictionary.put(questionAnswer[0], questionAnswer[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return myDictionary;
    }

    public void writeToDB(String question, String answer) throws IOException {

        String myLine = question + SEPARATOR + answer;
        File database = new File(dictionaryPath);
        FileWriter writer = null;

        try {
            writer = new FileWriter(database, true);
            writer.write(System.lineSeparator());
            writer.write(myLine);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}