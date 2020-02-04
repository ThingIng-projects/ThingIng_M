package com.thinging.project.controller;

import com.thinging.project.errors.*;
import com.thinging.project.errors.utils.ErrorCode;
import com.thinging.project.errors.utils.ErrorResponse;
import com.thinging.project.request.AbstractThingIngRequest;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

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

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(COAPServerStartedException.class)
    @ResponseBody
    protected final ErrorResponse returnCoapServerConflict(final COAPServerStartedException e) {
        return new ErrorResponse(e.getErrorCode(), e.getMessage());
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(COAPServerNotStartedException.class)
    @ResponseBody
    protected final ErrorResponse returnCoapServerConflict(final COAPServerNotStartedException e) {
        return new ErrorResponse(e.getErrorCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(COAPResourceNotExistsException.class)
    @ResponseBody
    protected final ErrorResponse returnCoapServerConflict(final COAPResourceNotExistsException e) {
        return new ErrorResponse(e.getErrorCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotExistsException.class)
    @ResponseBody
    protected final ErrorResponse returnUserNotExistsException(final UserNotExistsException e) {
        return new ErrorResponse(e.getErrorCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserExistsException.class)
    @ResponseBody
    protected final ErrorResponse returnUserExistsException(final UserExistsException e) {
        return new ErrorResponse(e.getErrorCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ServiceUnavailableException.class)
    @ResponseBody
    protected final ErrorResponse serviceUnAvalible(final ServiceUnavailableException e) {
        return new ErrorResponse(e.getErrorCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(PSQLException.class)
    @ResponseBody
    protected final ErrorResponse serviceUnAvalible(final PSQLException e) {
        return new ErrorResponse(ErrorCode.DB_ERROR, e.getMessage());
    }

    protected <T> ResponseEntity<T> respondCreated(final T object){ return respond(object, HttpStatus.CREATED); }
    protected <T> ResponseEntity<T> respondOK(final T object){ return respond(object, HttpStatus.OK); }
    protected  <T> ResponseEntity<T> respondEmpty(){ return new ResponseEntity<>(HttpStatus.NO_CONTENT); }


    private <T> ResponseEntity<T> respond(final T object, final HttpStatus httpStatus){
        return object == null ? respondEmpty() : new ResponseEntity<>(object, httpStatus);
    }

    protected final void validateRequest(final AbstractThingIngRequest request) {
        Assert.notNull(request, "request.object.is.required");

        final Set<ConstraintViolation<Object>> violations = this.validator.validate(request);
        if (!violations.isEmpty()) {
            final StringBuilder sb = new StringBuilder();
            for (final ConstraintViolation<Object> violation : violations) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(violation.getPropertyPath()).append(' ').append(violation.getMessage());
            }
            throw new IllegalArgumentException(sb.toString());
        }
    }

}
