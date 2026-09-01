package com.project.Ecommerce.Exceptions;

import org.aspectj.bridge.IMessage;

public class APIException extends RuntimeException{

    public APIException() {
    }

    public APIException(String Message) {
        super(Message);

    }
}
