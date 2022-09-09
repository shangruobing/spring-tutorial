package com.infoweaver.springtutorial.common;

import com.infoweaver.springtutorial.constant.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Ruobing Shang 2022-09-10 0:26
 */

@RestControllerAdvice

public class RequestExceptionHandle {
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
