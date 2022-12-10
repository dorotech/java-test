package com.br.dorotech.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {

    public ResourceNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
