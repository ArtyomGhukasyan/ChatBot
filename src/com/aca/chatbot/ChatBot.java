package com.aca.chatbot;

import java.io.IOException;
import java.util.Map;

public class ChatBot {

    Map<String, String> dictionary;
    CLReader clReader = CLReader.getCLReader();
    Repository repository = Repository.getRepository();

    public ChatBot() {
        initializeDictionary();
    }

    public void initializeDictionary() {
        dictionary = repository.getFromDB();
    }

    public void start() {

        System.out.println("Hi, I'm chatbot. You can chat or play with me.");

        String response = "";

        while (!response.equalsIgnoreCase("q")) {
            response = clReader.getInput("Press 'a',if you want to ask a question" + "\n" +
                    "Press 't', if you want to play TicTacToe" + "\n" +
                    "Press 'c', if you want to calculate some math expression" + "\n" +
                    "Press 'q' to quit" + "\n" +
                    "Enter your response here: ");
            switch (response) {
                case "t": // call TicTacToe.start()
                    break;
                case "c": // call calculator.start()
                    break;
                case "a":
                    startChat();
                    break;
                default:
                    break;
            }
        }

        System.out.println("Bye!");
    }

    public void startChat() {
        System.out.println("press q anytime you want to quit chat!");
        String question = "";

        while (!question.equalsIgnoreCase("q")) {
            question = clReader.getInput("Question: ");

            if (dictionary.containsKey(question)) {
                System.out.println(dictionary.get(question));
                question = clReader.getInput("Question: ");
            } else {
                addNewQuestion(question);
                question = clReader.getInput("Question: ");
            }
        }
    }

    public void addNewQuestion(String question) {

        String answer = clReader.getInput("I have no answer for " +
                "this question, please teach me ho to answer to this: ");

        while (answer.equals("")) {
            answer = clReader.getInput("I cant have blank as an answer, please" +
                    "type something: ");
        }

        dictionary.put(question, answer);

        try {
            repository.writeToDB(question, answer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Thanks for teaching me something new!");
    }
}
