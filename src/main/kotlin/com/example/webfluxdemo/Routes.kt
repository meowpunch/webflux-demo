package com.example.webfluxdemo

import kotlinx.coroutines.FlowPreview
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


@Configuration
class Routes {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Bean
    @FlowPreview
    @RouterOperations(
            RouterOperation(path = "/recommend-recipes", method = [RequestMethod.GET], beanClass = Service::class, beanMethod = "recommendRecipes"),
            RouterOperation(path = "/search-recipes", method = [RequestMethod.GET], beanClass = Service::class, beanMethod = "searchRecipes"),
            RouterOperation(path = "/notify-recipe", method = [RequestMethod.GET], beanClass = Handler::class, beanMethod = "notifyRecipe")
    )
    fun route(recipeHandler: Handler): RouterFunction<ServerResponse> = coRouter {
        GET("/recommend-recipes", recipeHandler::recommendRecipes)
        GET("/search-recipes", recipeHandler::searchRecipes)
        GET("/notify-recipe", recipeHandler::notifyRecipe)
    }
}