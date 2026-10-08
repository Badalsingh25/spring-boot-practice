package com.example.chatbot.service;

import com.example.chatbot.dto.GeminiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    
    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    
    @Value("${gemini.api.key}")
    private String apiKey;
    
    public Flux<String> streamResponse(String message) {
        
        // Create Gemini request
        GeminiRequest.Part part =
                new GeminiRequest.Part(message);
        
        GeminiRequest.Content content =
                new GeminiRequest.Content(List.of(part));
        
        GeminiRequest request =
                new GeminiRequest(List.of(content));
        
        // Call Gemini streaming API
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1beta/models/gemini-3.8-flash:streamGenerateContent")
                        .queryParam("alt", "sse")
                        .build())
                .header("x-goog-api-key", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .bodyValue(request)
                .retrieve()
                
                // Gemini sends Server-Sent Events
                .bodyToFlux(
                        new ParameterizedTypeReference<
                                ServerSentEvent<String>>() {
                        }
                )
                
                // Extract text from every SSE event
                .map(event -> {
                    
                    try {
                        
                        // Ignore empty events
                        if (event.data() == null ||
                                event.data().isBlank()) {
                            
                            return "";
                        }
                        
                        // Convert JSON string to JsonNode
                        JsonNode json =
                                objectMapper.readTree(event.data());
                        
                        /*
                         * Gemini response structure:
                         *
                         * {
                         *   "candidates": [
                         *     {
                         *       "content": {
                         *         "parts": [
                         *           {
                         *             "text": "Hello"
                         *           }
                         *         ]
                         *       }
                         *     }
                         *   ]
                         * }
                         */
                        
                        JsonNode candidates =
                                json.get("candidates");
                        
                        if (candidates == null ||
                                candidates.isEmpty()) {
                            
                            return "";
                        }
                        
                        JsonNode contentNode =
                                candidates
                                        .get(0)
                                        .get("content");
                        
                        if (contentNode == null) {
                            return "";
                        }
                        
                        JsonNode parts =
                                contentNode.get("parts");
                        
                        if (parts == null ||
                                parts.isEmpty()) {
                            
                            return "";
                        }
                        
                        JsonNode text =
                                parts.get(0).get("text");
                        
                        if (text == null) {
                            return "";
                        }
                        
                        return text.asText();
                        
                    } catch (Exception e) {
                        
                        throw new RuntimeException(
                                "Failed to parse Gemini SSE event",
                                e
                        );
                    }
                })
                
                // Don't send empty strings to the client
                .filter(text -> !text.isEmpty());
    }
}