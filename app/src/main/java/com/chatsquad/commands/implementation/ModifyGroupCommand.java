package com.chatsquad.commands.implementation;

import java.util.List;

import com.chatsquad.commands.ICommand;
import com.chatsquad.exceptions.NoSuchCommandException;
import com.chatsquad.services.IGroupService;

public class ModifyGroupCommand implements ICommand {

    private final IGroupService groupService;

    public ModifyGroupCommand(IGroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public void execute(List<String> tokens) {
        String subCommand = tokens.get(1);
        String groupId = tokens.get(2);
        String adminId = tokens.get(3);
        // ADD-USERS COMMAND
        if (subCommand.equals("ADD-USERS")) {
            List<String> memberIds = tokens.subList(4, tokens.size());
            try {
                String output = groupService.addMembersToGroup(groupId, adminId, memberIds);
                System.out.println(output);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        // REMOVE-USER COMMAND
        else if (subCommand.equals("REMOVE-USER")) {
            String memberId = tokens.get(4);
            try {
                String output = groupService.removeMemberFromGroup(groupId, adminId, memberId);
                System.out.println(output);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else
            throw new NoSuchCommandException();

    }

}
