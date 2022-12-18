package br.dorotech.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebCustomExeption extends RuntimeException {

    private int status;
    private String message;
    private String operation;

    public WebCustomExeption(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public WebCustomExeption(int status, String message, String operation) {
        this.status = status;
        this.message = message;
        this.operation = operation;
    }

    public WebCustomExeption(String msg, Throwable e) {
        super(msg, e);
    }

}
