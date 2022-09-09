package com.infoweaver.springtutorial.common;

import com.infoweaver.springtutorial.constant.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Ruobing Shang 2022-09-01 17:22
 */

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Global default exception handler.
     * TODO:Refactor with ResponseEntity.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<String> handleControllerException(Throwable ex) {
        System.out.println(ex.getMessage());
        Status status = (ex.getMessage() == null) ? Status.HTTP_404_NOT_FOUND : Status.HTTP_400_BAD_REQUEST;
        return Response.fail(status.getCode(), status.getMessage(), ex.getMessage());
    }

}