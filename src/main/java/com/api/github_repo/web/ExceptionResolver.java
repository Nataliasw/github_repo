package com.api.github_repo.web;

import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(Exception.class)
    public HashMap<String, String> handleException(HttpServletRequest request, Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("status", e.getMessage().substring(0,3));
        response.put("message", e.getMessage().substring(14,e.getMessage().length()-1));
        return response;
    }
    @ExceptionHandler(MissingPathVariableException.class)
    public HashMap<String, String> handleMissingPathVariableException(HttpServletRequest request, MissingPathVariableException e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Required path variable is missing in this request. Please add it to your request.");
        return response;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public HashMap<String, String> handleNotFoundResourceException(HttpServletRequest request, NoHandlerFoundException e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Requested resource wasn't found on the server");
        return response;
    }
}