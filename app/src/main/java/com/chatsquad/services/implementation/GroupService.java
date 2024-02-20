package com.chatsquad.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import com.chatsquad.entities.Group;
import com.chatsquad.entities.User;
import com.chatsquad.exceptions.UserNotFoundException;
import com.chatsquad.exceptions.ForbiddenException;
import com.chatsquad.repositories.IGroupRepository;
import com.chatsquad.repositories.IUserRepository;
import com.chatsquad.services.IGroupService;

public class GroupService implements IGroupService {

    private final IUserRepository userRepository;
    private final IGroupRepository groupRepository;

    public GroupService(IUserRepository userRepository, IGroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public String createGroup(String groupName, String adminId, List<String> memberIds) {
        User admin = userRepository.findById(adminId).orElseThrow(() -> new UserNotFoundException("User not found"));
        List<User> members = memberIds.stream().map(memberId -> userRepository.findById(memberId)
                .orElseThrow(() -> new UserNotFoundException("User not found"))).collect(Collectors.toList());
        Group group = groupRepository.save(new Group(null, groupName, admin, members));

        return "Group " + group.getId() + " created successfully";

    }

    @Override
    public String addMembersToGroup(String groupId, String adminId, List<String> memberIds) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new UserNotFoundException("Group not found"));
        User admin = userRepository.findById(adminId).orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!group.getAdmin().equals(admin))
            throw new ForbiddenException("Forbidden");
        List<User> members = memberIds.stream().map(memberId -> userRepository.findById(memberId)
                .orElseThrow(() -> new UserNotFoundException("User not found"))).collect(Collectors.toList());
        group.addMembers(members);

        return "User(s) " + String.join(" ", memberIds) + " added successfully";

    }

    @Override
    public String removeMemberFromGroup(String groupId, String adminId, String memberId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new UserNotFoundException("Group not found"));
        User admin = userRepository.findById(adminId).orElseThrow(() -> new UserNotFoundException("User not found"));
        User memberToRemove = userRepository.findById(memberId).orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!group.getAdmin().equals(admin))
            throw new ForbiddenException("Forbidden");
        group.removeMember(memberToRemove);
    
        return "User " + memberId + " removed successfully";
    }

    @Override
    public String viewGroupMembers(String groupId, String memberId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new UserNotFoundException("Group not found"));
        User user = userRepository.findById(memberId).orElseThrow(() -> new UserNotFoundException("User not found"));
        String output = "";

        if(group.getAdmin().equals(user) || group.getMembers().contains(user)){
            String groupName = group.getGroupName();
            String adminUsername = group.getAdmin().getUserName();
            List <String> usernames = group.getMembers().stream().map(u -> u.getUserName()).collect(Collectors.toList());
            output = String.format("%s Members: %s - Admin, %s", groupName, adminUsername, String.join(", ", usernames));
        }

        else {
            throw new ForbiddenException("Forbidden");
        }
        return output;
    }

}
