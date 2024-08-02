/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cyberbugs.service;

import com.cyberbugs.model.User;
import java.util.List;

public interface UserService {
    
    public User registUser(User user);
    
    public User findUserById(Integer userId) throws Exception;
    
    public User findUserByEmail(String email);
    
    public User followUser(Integer userId_1, Integer userId_2) throws Exception;
    
    public User updateUser(User user, Integer userId) throws Exception;
    
    public List<User> searchUser(String query);
    
    public User findUserByJwt(String jwt);
    
}
