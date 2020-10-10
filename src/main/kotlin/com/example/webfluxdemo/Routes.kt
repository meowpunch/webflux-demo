package com.example.webfluxdemo

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router


@Configuration
class Routes(private val recipeHandler: Handler) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Bean
    fun route(): RouterFunction<ServerResponse> = router {
        listOf(

                GET("/recommend-recipes", recipeHandler::recommendRecipe),
                GET("/search-recipes", recipeHandler::searchRecipe),
                GET("/notify-recipe", recipeHandler::notifyRecipe)
        )
    }
}