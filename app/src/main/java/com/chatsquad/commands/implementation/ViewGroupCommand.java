package com.chatsquad.commands.implementation;

import java.util.List;

import com.chatsquad.commands.ICommand;
import com.chatsquad.services.IGroupService;

public class ViewGroupCommand implements ICommand {

    private final IGroupService groupService;

    public ViewGroupCommand(IGroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public void execute(List<String> tokens) {
        String groupId = tokens.get(1);
        String memberId = tokens.get(2);
        try {
            String output = groupService.viewGroupMembers(groupId, memberId);
            System.out.println(output);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
