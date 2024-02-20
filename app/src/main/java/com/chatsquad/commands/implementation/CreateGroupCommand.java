package com.chatsquad.commands.implementation;

import java.util.List;

import com.chatsquad.commands.ICommand;
import com.chatsquad.services.IGroupService;

public class CreateGroupCommand implements ICommand {

    private final IGroupService groupService;

    public CreateGroupCommand(IGroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public void execute(List<String> tokens) {
        String groupName = tokens.get(1);
        String adminId = tokens.get(2);
        List<String> memberIds = tokens.subList(3, tokens.size());
        try {
            String output = groupService.createGroup(groupName, adminId, memberIds);
            System.out.println(output);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
