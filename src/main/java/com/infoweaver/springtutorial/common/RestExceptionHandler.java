package com.infoweaver.springtutorial.common;

import com.infoweaver.springtutorial.constant.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Ruobing Shang 2022-09-01 17:22
 */

@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * Global default exception handler.
     * TODO:Refactor with ResponseEntity.
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<String> handleControllerException(Throwable ex) {
        System.out.println("Error Message:" + ex.getMessage());
        Status status = (ex.getMessage() == null) ? Status.HTTP_404_NOT_FOUND : Status.HTTP_400_BAD_REQUEST;
        return Response.fail(status.getCode(), status.getMessage(), ex.getMessage());
    }

    /**
     * Handler HttpMessageNotReadableException
     * such as, request body is empty.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<String> handleRequestException(HttpMessageNotReadableException ex) {
        System.out.println("Error Message:" + ex.getMessage());
        Status status = Status.HTTP_400_BAD_REQUEST;
        return Response.fail(status.getCode(), status.getMessage(), ex.getMessage());
    }

}
