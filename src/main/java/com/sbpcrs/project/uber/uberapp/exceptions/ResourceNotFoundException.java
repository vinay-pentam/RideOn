package com.sbpcrs.project.uber.uberapp.exceptions;


public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){

    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
