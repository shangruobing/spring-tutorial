package com.infoweaver.springtutorial.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Ruobing Shang 2022-09-01 17:02
 */
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -1220656299702215742L;
    private int code;
    private String message;
    private T data;
    private static final String MYBATIS_PLUS_SUCCESS_STATUS = "1";

    public static <T> Response<T> success(T data) {
        /**
         * Avoid return Mybatis Plus success status code 1.
         */
        if (MYBATIS_PLUS_SUCCESS_STATUS.equals(data.toString())) {
            return new Response<>(200, "OK");
        }

        return new Response<>(200, "OK", data);
    }

    public static <T> Response<T> fail(int code, String message, T data) {
        return new Response<>(code, message, data);
    }

    private Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}