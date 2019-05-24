package com.aca.chatbot;

import java.util.Scanner;

public class CLReader {

    private static final CLReader clReader = new CLReader();

    private CLReader() {
    }

    public static CLReader getCLReader() {
        return clReader;
    }

    public String getInput(String requestText){
        Scanner scanner = new Scanner(System.in);
        System.out.print(requestText);
        return scanner.nextLine();
    }

}
