package com.project.Ecommerce.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class APIException extends RuntimeException{


    public APIException(String Message) {
        super(Message);

    }
}
