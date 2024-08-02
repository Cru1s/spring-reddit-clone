/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cyberbugs.response;

/**
 *
 * @author Cruis
 */
public class ApiResponse {
    
    private String message;
    
    private boolean status;

    public ApiResponse() {
    }

    public ApiResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }
    
    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}
