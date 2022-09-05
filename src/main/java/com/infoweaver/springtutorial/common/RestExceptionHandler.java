package com.infoweaver.springtutorial.common;

import com.infoweaver.springtutorial.constant.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Ruobing Shang 2022-09-01 17:22
 */

@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * 默认全局异常处理。
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<String> exception(Exception e) {
        System.out.println(e.getMessage());
        Status status = Status.HTTP_400_BAD_REQUEST;
        return Response.fail(status.getCode(), status.getMessage(), e.getMessage());
    }

}