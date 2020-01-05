package com.thinging.project.controller;

import com.thinging.project.exceptions.RecordConflictException;
import com.thinging.project.exceptions.dto.ErrorResponse;
import com.thinging.project.exceptions.utils.ErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Validator;
import java.util.LinkedList;
import java.util.Set;

/**
 * Base class for all controllers, which is actually useful to define error handling inside it and have common functionality here, like validation behaviour
 */
public abstract class AbstractController {

    private Validator validator;

    protected AbstractController(Validator validator) {
        this.validator = validator;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    protected final ErrorResponse returnBadRequestHttpStatus(final IllegalArgumentException e) {
        return new ErrorResponse(ErrorCode.ILLEGAL_ARGUMENT, e.getMessage());
    }

    protected <T> ResponseEntity<T> respondCreated(final T object){ return respond(object, HttpStatus.CREATED); }
    protected <T> ResponseEntity<T> respondOK(final T object){ return respond(object, HttpStatus.OK); }
    protected  <T> ResponseEntity<T> respondEmpty(){ return new ResponseEntity<>(HttpStatus.NO_CONTENT); }


    private <T> ResponseEntity<T> respond(final T object, final HttpStatus httpStatus){
        return object == null ? respondEmpty() : new ResponseEntity<>(object, httpStatus);
    }
}
