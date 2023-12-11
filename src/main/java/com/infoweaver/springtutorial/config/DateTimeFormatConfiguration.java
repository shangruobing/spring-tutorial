package com.infoweaver.springtutorial.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Ruobing Shang 2023-10-12 18:18
 */
@Configuration
public class DateTimeFormatConfiguration {
    private static final String PATTERN_DATE = "yyyy-MM-dd";
    private static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * Globally format LocalDate and LocalDateTime types.
     * Convert 2023-10-07T22:40:03 to 2023-10-07 22:40:03
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return objectMapperBuilder -> {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN_DATE_TIME);
            objectMapperBuilder.serializerByType(LocalDate.class, new LocalDateSerializer(dateFormatter));
            objectMapperBuilder.deserializerByType(LocalDate.class, new LocalDateDeserializer(dateFormatter));
            objectMapperBuilder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
            objectMapperBuilder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        };
    }
}
