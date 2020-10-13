package com.example.webfluxdemo

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.reactive.asFlow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Component
class Handler(@Autowired private val service: Service) {

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

    @FlowPreview
    suspend fun recommendRecipes(request: ServerRequest): ServerResponse =
            ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .json()
                    .bodyAndAwait(
                            service.recommendRecipes(
                                    // TODO: validator
                                    request.queryParam("ingredients").orElseThrow(),
                                    request.queryParam("size").orElseThrow().toInt()
                            )
                    )


    suspend fun searchRecipes(request: ServerRequest): ServerResponse {
        val a: Flow<List<Recipe>> = flowOf(recipes1)
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