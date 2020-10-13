# webflux-demo
### Keywords
- webflux, kotlin, coroutine, functional, swagger, elasticsearch

### Environments
- mac
- intelliJ
- docker
- jq

### Get Started
```shell script
# exec elasticsearch, kibana
$ es/docker-compose up

# insert init data
$ sh es/migrate.sh
```

### Features
- Without Spring Data Elasticserach or ELastic Rest High Level Client
  - Using WebClient and raw Query

```kotlin
fun searchRecipes(keywords: String, size: Int): Flow<Any> = webClient
      .post()
      .uri("/omtm/recipe/_search")
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue("""
          {
            "query": {
              "multi_match": {
                "query": "$keywords",
                "fields": ["description","title^0.5"]
              }
            },
            "size": $size
          }
      """.trimIndent())
      .retrieve()
      .bodyToFlux(Any::class.java)
      .asFlow()
```
