package com.jwtproject.exception;

import org.springframework.http.HttpStatus;

public class Unauthorized401Exception extends Abstract4xxException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;

    public Unauthorized401Exception (String message) { super(HTTP_STATUS, message); }

}
