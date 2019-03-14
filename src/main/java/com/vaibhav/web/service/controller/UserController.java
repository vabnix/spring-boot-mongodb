package com.vaibhav.web.service.controller;

import com.vaibhav.web.service.Domain.UserDo;
import com.vaibhav.web.service.Domain.Users;
import com.vaibhav.web.service.configuration.Decryption;
import com.vaibhav.web.service.data.User;
import com.vaibhav.web.service.data.UserTransformer;
import com.vaibhav.web.service.repositories.UserRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Api(description = "Operations related to Partners")
public class UserController {

    private final static String USERS = "/users";
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ApiOperation(value = "Retrieve all user")
    @RequestMapping(path = USERS, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 401, message = "You are not authenticated"),
            @ApiResponse(code = 403, message = "You are not authorized to see these Credential"),
            @ApiResponse(code = 404, message = "Credential not found")})
    public Users findAllUsers() {
        Users users = new Users();
        List<User> user = userRepository.findAll();
        for(User user1: user){
            UserDo userDo = new UserDo();
            userDo.setId(user1.getId());
            userDo.setUsername(user1.getUsername());
            userDo.setPassword(Decryption.decrypt(user1.getPassword()));
            users.add(userDo);
        }
        return users;
    }

    @ApiOperation(value = "Add user")
    @RequestMapping(path = USERS, method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User Created"),
            @ApiResponse(code = 401, message = "You are not authenticated"),
            @ApiResponse(code = 403, message = "You are not authorized to see these Credential"),
            @ApiResponse(code = 404, message = "Credential not found")})
    public User createUsers(@RequestBody UserDo userDo) {
        userDo.setId(UUID.randomUUID());
        return userRepository.save(UserTransformer.transform(userDo));
    }
}
