/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cyberbugs.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer uid;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String gender;

    private List<Integer> followers = new ArrayList<>();

    private List<Integer> followings = new ArrayList<>();
    
    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();

    public User() {
    }

    public User(Integer uid, String firstName, String lastName, String email, String password, String gender, List<Integer> followings, List<Integer> followers) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.followings = followings;
        this.followers = followers;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public List<Integer> getFollowings() {
        return followings;
    }

    public void setFollowings(List<Integer> followings) {
        this.followings = followings;
    }

    public List<Post> getSavedPost() {
        return savedPost;
    }

    public void setSavedPost(List<Post> savedPost) {
        this.savedPost = savedPost;
    }
    
    

}
