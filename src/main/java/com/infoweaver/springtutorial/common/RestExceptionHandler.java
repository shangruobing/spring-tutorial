package com.infoweaver.springtutorial.common;

import com.infoweaver.springtutorial.constant.Status;
import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;
import java.util.Optional;

/**
 * @author Ruobing Shang 2022-09-01 17:22
 */


@RestControllerAdvice
public class RestExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * Not Found exception handler.
     */
    @ExceptionHandler({NoHandlerFoundException.class, NotFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleControllerException(Throwable ex) {
        logger.debug("Error Message:" + ex.getMessage());
        return Map.of("message", ex.getMessage());
    }

    /**
     * Handler HttpMessageNotReadableException
     * such as, request body is empty.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleRequestException(HttpMessageNotReadableException ex) {
        logger.debug("Error Message:" + ex.getMessage());
        return Map.of("message", Optional.ofNullable(ex.getMessage())
                .orElse(Status.HTTP_400_BAD_REQUEST.getMessage()));

    }

    /**
     * Global default exception handler.
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleException(HttpMessageNotReadableException ex) {
        logger.debug("Error Message:" + ex.getMessage());
        return Map.of("message", Optional.ofNullable(ex.getMessage())
                .orElse(Status.HTTP_400_BAD_REQUEST.getMessage()));
    }
}
