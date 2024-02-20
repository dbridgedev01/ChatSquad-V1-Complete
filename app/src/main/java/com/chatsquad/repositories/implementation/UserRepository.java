package com.chatsquad.repositories.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.chatsquad.entities.User;
import com.chatsquad.repositories.IUserRepository;

public class UserRepository implements IUserRepository {

    private final Map<String, User> userMap;

    public UserRepository(){
        userMap = new HashMap<String, User>();
    }

    public UserRepository(Map<String,User> userMap){
        this.userMap = userMap;
    }

    @Override
    public User save(User user) {
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

}
