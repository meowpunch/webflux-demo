package com.example.webfluxdemo

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter
import org.springframework.web.reactive.function.server.router


@Configuration
class Routes(private val recipeHandler: Handler) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Bean
    @RouterOperations(
            RouterOperation(path = "/recommend-recipes", method = [RequestMethod.GET], beanClass = Handler::class, beanMethod = "recommendRecipe"),
            RouterOperation(path = "/search-recipes", method = [RequestMethod.GET], beanClass = Handler::class, beanMethod = "searchRecipe"),
            RouterOperation(path = "/notify-recipe", method = [RequestMethod.GET], beanClass = Handler::class, beanMethod = "notifyRecipe")
    )
    fun route(): RouterFunction<ServerResponse> = coRouter {
        GET("/recommend-recipes", recipeHandler::recommendRecipe)
        GET("/search-recipes", recipeHandler::searchRecipe)
        GET("/notify-recipe", recipeHandler::notifyRecipe)
    }
}