package br.dorotech.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.dorotech.domain.model.ResponseClientError;
import br.dorotech.domain.model.WebCustomExeption;

@Provider
public class ErrorHandler implements ExceptionMapper<WebCustomExeption> {

    @Override
    public Response toResponse(WebCustomExeption exception) {

        final String message = exception.getMessage();
        final String op = exception.getOperation();
        final int code = exception.getStatus();
        final ResponseClientError client = new ResponseClientError(message, op);

        return Response.status(code).entity(client).build();
    }
}
