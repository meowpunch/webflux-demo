package com.example.webfluxdemo

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class Handler {

    private val recipes0 = listOf(
            Recipe("김치찌개", "잘 끓인다"),
            Recipe("된장찌개", "정성 들여 끓인다"),
            Recipe("고추장찌개", "맛있게 끓인다")
    )

    private val recipes1 = listOf(
            Recipe("고추장찌개", "잘 끓인다"),
            Recipe("된장찌개", "정성 들여 끓인다"),
            Recipe("김치찌개", "맛있게 끓인다")
    )


    fun recommendRecipe(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(recipes0)
    }

    fun searchRecipe(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(recipes1)
    }

    fun notifyRecipe(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(Recipe("김치찌개", "맛있게 끓인다")))
    }

}