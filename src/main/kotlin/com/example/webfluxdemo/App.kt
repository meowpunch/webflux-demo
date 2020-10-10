package com.example.webfluxdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RecipeRecommenderApplication

fun main(args: Array<String>) {
    runApplication<RecipeRecommenderApplication>(*args)
}
