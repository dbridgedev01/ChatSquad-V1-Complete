/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.chatsquad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.chatsquad.appConfig.ApplicationConfig;
import com.chatsquad.commands.CommandInvoker;
import com.chatsquad.exceptions.NoSuchCommandException;

public class App {

    // To run the application ./gradlew run --args="INPUT_FILE=input.txt"
    public static void main(String[] args) { 
        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        String expectedSequence = "INPUT_FILE";
        String actualSequence = args[0].split("=")[0];
        if (expectedSequence.equals(actualSequence)) {
            run(commandLineArgs);
        }
    }

    public static void run(List<String> commandLineArgs) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
        BufferedReader bufferedReader;
        String inputFile = commandLineArgs.get(0).split("=")[1];
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFile));
            String line = bufferedReader.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(line.split(" "));
                commandInvoker.executeCommand(tokens.get(0), tokens);
                // read next line
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException | NoSuchCommandException e) {
            e.printStackTrace();
        }

    }
}
