/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cyberbugs.service;

import com.cyberbugs.config.JwtProvider;
import com.cyberbugs.model.User;
import com.cyberbugs.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cruis
 */
@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository ;

    @Override
    public User registUser(User user) {

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setUid(user.getUid());
        newUser.setGender(user.getGender());

        User saveUser = userRepository.save(newUser);

        return saveUser;
    }

    @Override
    public User findUserById(Integer userId) throws Exception{
        
        
        Optional<User> user = userRepository.findById(userId);
        
        if(user.isPresent()){
            return user.get();
        }
        
        throw new Exception("user does not exist with id: " + userId);
    }

    @Override
    public User findUserByEmail(String email) {
        
        User user = userRepository.findByEmail(email);
        
        return user;

    }

    @Override
    public User followUser(Integer requestUserId, Integer followedUserId) throws Exception{
        
        User requestUser = findUserById(requestUserId);
        
        User followedUser = findUserById(followedUserId);
        
        followedUser.getFollowers().add(requestUserId);
        requestUser.getFollowings().add(followedUserId);
        
        userRepository.save(requestUser);
        userRepository.save(followedUser);
             
        return requestUser;
    }

    @Override
    public User updateUser(User user, Integer userId) throws Exception{
        
        Optional<User> dummy = userRepository.findById(userId);
        
        if(dummy.isEmpty()){
            throw new Exception("user does not exsit");
        }
        
        User oldUser = dummy.get();
        
        if (user.getFirstName() != null) {
            oldUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            oldUser.setLastName(user.getLastName());
        }
        if (user.getEmail()!= null) {
            oldUser.setEmail(user.getEmail());
        }
        if (user.getGender() != null){
            oldUser.setGender(user.getGender());
        }
        
        User updateUser = userRepository.save(oldUser);
        
        return updateUser;
    }

    @Override
    public List<User> searchUser(String query) {
        
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        
        User user = userRepository.findByEmail(email);
        
        return user;
    }

}
