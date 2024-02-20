package com.chatsquad.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.chatsquad.entities.Group;
import com.chatsquad.entities.User;
import com.chatsquad.repositories.IGroupRepository;
import com.chatsquad.repositories.IUserRepository;
import com.chatsquad.services.implementation.GroupService;


@DisplayName("GroupServiceTest")
@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

    @Mock
    private IGroupRepository groupRepositoryMock;

    @Mock
    private IUserRepository userRepositoryMock;

    @InjectMocks
    private GroupService groupService;

    @Test
    @DisplayName("createGroup method should create a Group")
    public void create_ShouldReturnSuccessString(){

        List<User> members = new ArrayList<User>(){
            {
            add(new User("2", "Porro", "porro2"));
            add(new User("3", "Dejan", "dejank"));
            add(new User("4", "Vicario", "venom"));
            }
        };

        List <String> memberIds = new ArrayList<String>(){
            {
            add("2");
            add("3");
            add("4");
            }
        };
        
        User admin = new User("1", "Abdul", "abdx");
        
        //Arrange
        Group expectedGroup = new Group("1", "TEST_GROUP", admin, members);
        String expectedOutput = "Group " + expectedGroup.getId() + " created successfully";
        
        when(userRepositoryMock.findById(any(String.class))).thenReturn(Optional.of(admin));
        when(groupRepositoryMock.save(any(Group.class))).thenReturn(expectedGroup);

        //Act
        String actualOutput = groupService.createGroup("TEST_GROUP","1", memberIds);

        //Assert
        Assertions.assertEquals(expectedOutput,actualOutput);
        verify(groupRepositoryMock,times(1)).save(any(Group.class));
    }
    
}
