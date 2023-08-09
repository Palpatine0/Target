package com.target.target_manager_api.security;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;



@RestControllerAdvice
public class AccessDeniedHandler {
    //to handel access exception, will rethrow when exc captured, then will send it to MyAccess...
    @ExceptionHandler(AccessDeniedException.class)
    public void defaultExceptionHandler(AccessDeniedException e) throws AccessDeniedException {
        throw e;
    }
}

