package com.chatsquad.repositories;

import java.util.Optional;

import com.chatsquad.entities.Group;

public interface IGroupRepository {
    public Group save(Group group);

    public Optional<Group> findById(String id);
}
