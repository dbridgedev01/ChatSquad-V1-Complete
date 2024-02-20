package com.chatsquad.entities;

import java.util.List;

public class Group extends BaseEntity {

    private final String groupName;
    private final User admin;
    private List<User> members;

    public Group(String id, String groupName, User admin, List<User> members) {
        this.id = id;
        this.groupName = groupName;
        this.admin = admin;
        this.members = members;

    }

    public String getGroupName() {
        return groupName;
    }

    public User getAdmin() {
        return admin;
    }

    public List<User> getMembers() {
        return members;
    }

    public void addMembers(List<User> members) {
        this.members.addAll(members);
    }

    public void removeMember(User member) {
        this.members.remove(member);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
