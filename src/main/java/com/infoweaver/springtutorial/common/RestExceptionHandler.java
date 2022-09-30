package com.infoweaver.springtutorial.common;

import com.infoweaver.springtutorial.constant.Status;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Ruobing Shang 2022-09-01 17:22
 */

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * Use @Slf4j annotation to replace this line code.
     * private final static Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);
     */

    /**
     * Not Found exception handler.
     */
    @ExceptionHandler({NoHandlerFoundException.class, NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleControllerException(Throwable ex) {
        log.debug("Error Message:" + ex.getMessage(), ex);
        return Map.of("message", ex.getMessage());
    }

    /**
     * Handler HttpMessageNotReadableException
     * such as, request body is empty.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleRequestException(HttpMessageNotReadableException ex) {
        log.debug("Error Message:" + ex.getMessage(), ex);
        return Map.of("message", Optional.ofNullable(ex.getMessage())
                .orElse(Status.HTTP_400_BAD_REQUEST.getMessage()));
    }

    /**
     * Handler MethodArgumentNotValidException
     * such as, request parameters are invalid.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        log.debug("Error Message:" + ex.getMessage(), ex);
        return Map.of("message", Optional.ofNullable(message)
                .orElse(Status.HTTP_400_BAD_REQUEST.getMessage()));
    }


    /**
     * Handler NullPointerException
     * such as, Response handler is a void method.
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> handleNullPointerException(Exception ex) {
        log.debug("Error Message:" + ex.getMessage(), ex);
        return Map.of("message", Optional.ofNullable(ex.getMessage())
                .orElse(Status.HTTP_200_OK.getMessage()));
    }

    /**
     * Handler MyBatisSystemException
     * Please close spring-boot-devtools hot-reload when using redis,
     * because the devtools will not update the context of redisTemplate,
     * this will cause redisTemplate to use the closed ApplicationContext,
     * then will eventually result in a null pointer exception.
     */
    @ExceptionHandler(MyBatisSystemException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleMyBatisSystemException(Exception ex) {
        String message = "Please close spring-boot-devtools hot-reload when using redis, " +
                "because devtools will not update the context of redisTemplate, " +
                "this will cause redisTemplate to use the closed ApplicationContext, " +
                "then will eventually result in a null pointer exception.";
        log.debug("Error Message:" + ex.getMessage(), ex);
        return Map.of("message", message);
    }


//    /**
//     * seems not valid
//     * Handler AuthenticationException
//     */
//    @ExceptionHandler(AuthenticationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map<String, String> handleAuthenticationException(Exception ex) {
//        log.debug("Error Message:" + ex.getMessage(), ex);
//        return Map.of("message", Optional.ofNullable(ex.getMessage())
//                .orElse(Status.HTTP_400_BAD_REQUEST.getMessage()));
//    }

    /**
     * Global default exception handler.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleException(Exception ex) {
        log.debug("Error Message:" + ex.getMessage(), ex);
        return Map.of("message", Status.HTTP_400_BAD_REQUEST.getMessage());
    }
}
