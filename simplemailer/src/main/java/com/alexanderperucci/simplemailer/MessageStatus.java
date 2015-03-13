/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexanderperucci.simplemailer;

/**
 *
 * @author alexander
 */
public enum MessageStatus {
    MESSAGE_DELIVERED(1), MESSAGE_NOT_DELIVERED(2), MESSAGE_PARTIALLY_DELIVERED(3);
    
    private final int statusCode;
      
    MessageStatus(int statusCode){
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
    
}