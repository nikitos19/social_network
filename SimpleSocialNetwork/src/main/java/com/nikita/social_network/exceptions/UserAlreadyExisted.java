package com.nikita.social_network.exceptions;

public class UserAlreadyExisted extends RuntimeException {
    public UserAlreadyExisted(){
        super("User already existed");
    }
}
