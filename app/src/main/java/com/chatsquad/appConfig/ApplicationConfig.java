package com.chatsquad.appConfig;

import com.chatsquad.commands.CommandInvoker;
import com.chatsquad.commands.ICommand;
import com.chatsquad.commands.implementation.LoadDataCommand;
import com.chatsquad.commands.implementation.ModifyGroupCommand;
import com.chatsquad.commands.implementation.ViewGroupCommand;
import com.chatsquad.commands.implementation.CreateGroupCommand;
import com.chatsquad.repositories.IGroupRepository;
import com.chatsquad.repositories.IUserRepository;
import com.chatsquad.repositories.implementation.GroupRepository;
import com.chatsquad.repositories.implementation.UserRepository;
import com.chatsquad.services.IGroupService;
import com.chatsquad.services.IUserService;
import com.chatsquad.services.implementation.GroupService;
import com.chatsquad.services.implementation.UserService;

public class ApplicationConfig {

    // Initialize Repositories
    private final IUserRepository userRepository = new UserRepository();
    private final IGroupRepository groupRepository = new GroupRepository();

    // Initialize Services
    private final IUserService userService = new UserService(userRepository);
    private final IGroupService groupService = new GroupService(userRepository, groupRepository);

    // Initialize Commands
    private final ICommand loadDataCommand = new LoadDataCommand(userService);
    private final ICommand createGroupCommand = new CreateGroupCommand(groupService);
    private final ICommand modifyGroupCommand = new ModifyGroupCommand(groupService);
    private final ICommand viewGroupCommand = new ViewGroupCommand(groupService);

    // Configure Command Invoker
    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("LOAD-DATA", loadDataCommand);
        commandInvoker.register("CREATE-GROUP", createGroupCommand);
        commandInvoker.register("MODIFY-GROUP", modifyGroupCommand);
        commandInvoker.register("VIEW-GROUP", viewGroupCommand);

        return commandInvoker;
    }
}
