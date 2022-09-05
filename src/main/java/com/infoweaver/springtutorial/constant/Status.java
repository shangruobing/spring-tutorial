package com.infoweaver.springtutorial.constant;


/**
 * @author Ruobing Shang 2022-09-01 17:23
 */

public enum Status {
    /**
     * HTTP Status Code
     */
    HTTP_100_CONTINUE(100, "CONTINUE"),
    HTTP_200_OK(200, "OK"),
    HTTP_201_CREATED(201, "CREATED"),
    HTTP_400_BAD_REQUEST(400, "BAD_REQUEST"),
    HTTP_401_UNAUTHORIZED(401, "UNAUTHORIZED"),
    HTTP_403_FORBIDDEN(403, "FORBIDDEN"),
    HTTP_404_NOT_FOUND(404, "NOT_FOUND"),
    HTTP_405_METHOD_NOT_ALLOWED(405, "METHOD_NOT_ALLOWED"),
    HTTP_500_INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR");

    private final int code;
    private final String message;

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
