package com.infoweaver.springtutorial.ai;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * @author Ruobing Shang 2025-08-05 08:18
 */
@RestController
public class AiService {
    public Flux<String> chat(@RequestBody AiDto aiDto) {
        String url = "http://127.0.0.1:11434";
        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .build();

        return webClient.post()
                .uri("/api/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(aiDto)
                .accept(MediaType.TEXT_EVENT_STREAM, MediaType.APPLICATION_NDJSON, MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(String.class);
    }
}
