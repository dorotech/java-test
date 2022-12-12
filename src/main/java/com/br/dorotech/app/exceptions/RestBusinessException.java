package com.br.dorotech.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RestBusinessException extends ResponseStatusException {

    public RestBusinessException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
