package org.koreait.springexam.errors;


public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }

}
