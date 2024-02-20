package com.chatsquad.services;

import java.util.List;

public interface IGroupService {
    public String createGroup(String groupName, String adminId, List<String> memberIds);
    public String addMembersToGroup(String groupId, String adminId, List<String> memberIds);
    public String removeMemberFromGroup(String groupId, String adminId, String memberId);
    public String viewGroupMembers(String groupId, String memberId);
}
