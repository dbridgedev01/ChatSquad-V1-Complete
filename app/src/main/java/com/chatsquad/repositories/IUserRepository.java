package com.chatsquad.repositories;

import java.util.Optional;

import com.chatsquad.entities.User;

public interface IUserRepository {
    public User save(User user);

    public Optional<User> findById(String id);
}
