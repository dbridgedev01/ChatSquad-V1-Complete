package com.chatsquad.repositories.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.chatsquad.entities.Group;
import com.chatsquad.repositories.IGroupRepository;

public class GroupRepository implements IGroupRepository {

     private final Map<String, Group> groupMap;
     private int groupId = 0;

    public GroupRepository(){
        groupMap = new HashMap<String, Group>();
    }

    public GroupRepository(Map<String,Group> groupMap){
        this.groupMap = groupMap;
    }

    @Override
    public Group save(Group group) {
        if(group.getId() == null){
            groupId++;
            Group grp = new Group(String.valueOf(groupId), group.getGroupName(), group.getAdmin(), group.getMembers());
            groupMap.put(grp.getId(), grp);
            return grp;
        }
        groupMap.put(group.getId(), group);
        return group;
    }

    @Override
    public Optional<Group> findById(String id) {
        return Optional.ofNullable(groupMap.get(id));
    }
    
}
