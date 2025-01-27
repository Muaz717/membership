package com.gym.Membership.advice;

import com.gym.Membership.dto.Response;
import com.gym.Membership.util.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<Response> handleException(MyException e) {

        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
