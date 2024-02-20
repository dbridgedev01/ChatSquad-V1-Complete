package com.chatsquad.commands.implementation;

import java.util.List;

import com.chatsquad.commands.ICommand;
import com.chatsquad.services.IUserService;

public class LoadDataCommand implements ICommand {

    private final IUserService userService;

    public LoadDataCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String inputFileName = tokens.get(1);
        try {
            String output = userService.loadUserData(inputFileName);
            System.out.println(output);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
