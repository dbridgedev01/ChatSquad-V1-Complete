package com.chatsquad.services.implementation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.chatsquad.entities.User;
import com.chatsquad.repositories.IUserRepository;
import com.chatsquad.services.IUserService;

public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String loadUserData(String inputFile) throws IOException {
        String line = "";
        String id, name, userName;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
        while ((line = bufferedReader.readLine()) != null)
        {
            String[] fileValues = line.split(",");
            id = fileValues[0];
            name = fileValues[1];
            userName = fileValues[2];
            userRepository.save(new User(id, name, userName));
        }
        bufferedReader.close();
        return "Users loaded successfully";
    }
}
