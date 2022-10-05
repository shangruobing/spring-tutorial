package com.infoweaver.springtutorial.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author Ruobing Shang 2022-10-04 21:32
 */
public class JacksonUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static ObjectMapper getObjectMapper() {
        OBJECT_MAPPER.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return OBJECT_MAPPER.registerModule(addJavaTimeModule());
    }

    public static String toJsonString(Object object) throws JsonProcessingException {
        return getObjectMapper().writeValueAsString(object);
    }

    public static JavaTimeModule addJavaTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeUtils.DATE_TIME_FORMATTER))
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeUtils.DATE_TIME_FORMATTER));
        return javaTimeModule;
    }

    public static Jackson2JsonMessageConverter getMessageConverter() {
        return new Jackson2JsonMessageConverter(getObjectMapper());
    }

    public static HashMap<?, ?> jsonToHashMap(String str) throws JsonProcessingException {
        return getObjectMapper().readValue(str, HashMap.class);
    }

}
