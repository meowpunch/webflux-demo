package com.example.webfluxdemo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecipeRecommenderTest(
        @Autowired private val client: WebTestClient
) {
    @Test
    internal fun get() {
        print("hi")
        val response = client.get()
                .uri("/notify-recipe")
                .exchange()
                .expectStatus().isOk
                .expectBody(Recipe::class.java)
                .returnResult().responseBody
        assertThat(response).isEqualTo(Recipe("김치찌개", "맛있게 끓인다"))
    }
}