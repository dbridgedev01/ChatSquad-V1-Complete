
package com.chatsquad;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AppTest")
public class AppTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Integration Test #1")
    void runTest1() {

        // Arrange
        List<String> arguments = new ArrayList<>(Arrays.asList("INPUT_FILE=input.txt"));

        String expectedOutput = "Users loaded successfully\n" +
                "Group 1 created successfully\n" +
                "Group 2 created successfully\n" +
                "User(s) 7 8 added successfully\n" +
                "User 2 removed successfully\n" +
                "HELLO_WORLD Members: rocky - Admin, georgex, milan01, shay11, gini99, bryan\n" +
                "Cool_Squad Members: serge77 - Admin, milan01, shay11, romero5\n" +
                "Forbidden\n" +
                "Group not found\n" +
                "User not found";
        // Act
        App.run(arguments);

        // Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
