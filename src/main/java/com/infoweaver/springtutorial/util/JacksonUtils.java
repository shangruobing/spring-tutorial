package com.infoweaver.springtutorial.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

/**
 * @author Ruobing Shang 2022-10-04 21:32
 */
public class JacksonUtils {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static String toJSONString(Object object) throws JsonProcessingException {

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeUtils.DATE_TIME_FORMATTER))
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeUtils.DATE_TIME_FORMATTER));
        objectMapper.registerModule(javaTimeModule);
        return objectMapper.writeValueAsString(object);
    }
}
