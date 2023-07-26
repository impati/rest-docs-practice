package com.example.restdocspractice.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/docs")
public class RestDocsTestController {

    @GetMapping
    public ResponseEntity<Response> exampleGetMapping() {
        return ResponseEntity.ok(Response.of("OK"));
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Response {
        private String status;

        static Response of(String status) {
            return new Response(status);
        }
    }

}
