package com.example.webfluxdemo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
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


    suspend fun recommendRecipe(request: ServerRequest): ServerResponse {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(recipes0)
    }

    suspend fun searchRecipe(request: ServerRequest): ServerResponse {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyAndAwait(flowOf(recipes1))
    }

    suspend fun notifyRecipe(request: ServerRequest): ServerResponse {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(Recipe("김치찌개", "맛있게 끓인다"))
    }

}