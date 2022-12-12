package com.ferreirabrunomarcelo.productapi.exceptions;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Component
public class CustomAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
      Map<String, Object> errorAttributes = super.getErrorAttributes(request, options);
      Throwable throwable =  getError(request);
      if(throwable instanceof ResponseStatusException exception) {
          if(exception.getStatusCode().value() == 404) {
              errorAttributes.put("message", "resource not found.");
          }
      }
      return errorAttributes;
    }


}
