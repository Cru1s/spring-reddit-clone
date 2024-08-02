/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cyberbugs.controller;

import org.springframework.web.bind.annotation.*;



@RestController
public class homeController {
    
    @GetMapping("/")
    public String homeControllerHandler() {
        return "this is number 1";
    }
    
    @GetMapping("/home")
    public String homeControllerHandler2(){
        return "testing 1 2 3";
    }
    
    
//    @PutMapping
//    @PostMapping
//    @DeleteMapping
}
