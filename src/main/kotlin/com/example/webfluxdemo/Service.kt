package com.example.webfluxdemo

import com.example.webfluxdemo.config.es.ESExtensions.monoAsyncSearch
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.builder.SearchSourceBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class Service (@Autowired private val restHighLevelClient: RestHighLevelClient) {

    fun searchRequest(): Mono<SearchResponse> {
        val searchRequest = SearchRequest("omtm")
        val searchSourceBuilder = SearchSourceBuilder()
        searchSourceBuilder.query(QueryBuilders.matchAllQuery())
        searchRequest.source(searchSourceBuilder)
        return restHighLevelClient.monoAsyncSearch(searchRequest)
    }
}