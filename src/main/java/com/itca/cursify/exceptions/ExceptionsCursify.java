package com.itca.cursify.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ExceptionsCursify extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public ExceptionsCursify(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
