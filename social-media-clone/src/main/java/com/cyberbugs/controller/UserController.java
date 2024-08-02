/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cyberbugs.controller;

import com.cyberbugs.model.User;
import com.cyberbugs.repository.UserRepository;
import com.cyberbugs.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserService userService;
    
    @GetMapping("/users")
    public List<User> getUser() {

        List<User> users = userRepository.findAll();

        return users;
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {
        
        User user = userService.findUserById(id);
        
        return user;
    }

//    @PostMapping("/users/create")
//    public User createUser(@RequestBody User user) {
//
//        User saveUser = userService.registUser(user);
//        
//        return saveUser;
//    }

    @PutMapping("/users/{userId}")
    public User updataUser(@RequestHeader("Authorization") String jwt,@RequestBody User user) throws Exception {

        User requestUser = userService.findUserByJwt(jwt);
        
        User updateUser = userService.updateUser(user, requestUser.getUid());
        
        return updateUser;
    }
    
    @PutMapping("/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws Exception{
        
        User requestUser = userService.findUserByJwt(jwt);
        
        User user = userService.followUser(requestUser.getUid(), userId2);
        
        return user;
    }
    
    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query){
        
        List<User> users = userService.searchUser(query);
        
        return users;
    }
    
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) throws Exception{
        
        Optional<User> user = userRepository.findById(userId);
        
        if(user.isEmpty()){
            throw new Exception("user does not exist");
        }
        
        userRepository.delete(user.get());
        
        return "user delete succesfully with id " + userId ;
    }
    
    @GetMapping("/api/user/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt){
        
        User user = userService.findUserByJwt(jwt);
       
        return user;
    }
}
