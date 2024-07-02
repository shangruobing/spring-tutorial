package com.infoweaver.springtutorial.common;

import com.infoweaver.springtutorial.constant.Status;
import com.infoweaver.springtutorial.exception.CustomNoRollbackException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.sasl.AuthenticationException;
import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Ruobing Shang 2023-09-24 20:34
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * Custom exception handler.
     */
    @ExceptionHandler({CustomNoRollbackException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleCustomNoRollbackException(Throwable ex) {
        log.error(ex.getMessage());
        return Map.of("message", ex.getMessage());
    }

    /**
     * Not Found exception handler.
     */
    @ExceptionHandler({NoHandlerFoundException.class, NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleControllerException(Throwable ex) {
        log.error(ex.getMessage());
        return Map.of("message", ex.getMessage());
    }

    /**
     * SQL Syntax exception handler.
     */
    @ExceptionHandler({
            SQLSyntaxErrorException.class,
            BadSqlGrammarException.class,
            SQLException.class,
            BatchUpdateException.class,
            DuplicateKeyException.class,
            DataIntegrityViolationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleSqlSyntaxErrorException(Throwable ex) {
        log.error(ex.getMessage());
        return Map.of("message", "查询条件不正确");
    }

    /**
     * SQLIntegrityConstraintViolationException
     */
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleSqlIntegrityConstraintViolationException(Throwable ex) {
        log.error(ex.getMessage());
        return Map.of("message", "传入数据不正确");
    }

    /**
     * Authentication exception handler.
     */
    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, String> handleAuthenticationException(Throwable ex) {
        log.error(ex.getMessage());
        return Map.of("message", ex.getMessage());
    }

    /**
     * 使用Spring Security AccessDeniedHandlerImpl来处理403，这里直接抛出即可
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void accessDeniedException(AccessDeniedException ex) throws AccessDeniedException {
        log.error(ex.getMessage());
        throw ex;
    }

    /**
     * Handler HttpMessageNotReadableException
     * such as, request body is empty or JSON parse error
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleRequestException(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage());
        return Map.of("message", Optional.ofNullable(ex.getMessage()).orElse("Required request body is missing"));
    }

    /**
     * Handler MethodArgumentNotValidException
     * such as, request parameters are invalid.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        String message = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return Map.of("message", Optional.ofNullable(message).orElse(Status.HTTP_400_BAD_REQUEST.getMessage()));
    }

    /**
     * Handler NullPointerException
     * such as, Response handler is a void method.
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleNullPointerException(Exception ex) {
        log.error(ex.getMessage());
        return Map.of("message", Optional.ofNullable(ex.getMessage()).orElse(Status.HTTP_400_BAD_REQUEST.getMessage()));
    }

    /**
     * Global default exception handler.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleException(Exception ex) {
        log.error(ex.getMessage());
        return Map.of("message", Optional.ofNullable(ex.getMessage()).orElse(Status.HTTP_400_BAD_REQUEST.getMessage()));
    }
}
