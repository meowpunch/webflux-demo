package com.example.webfluxdemo.config.es

import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient

class ESConfig {

    fun restHighLevelClient(): RestHighLevelClient = RestHighLevelClient(
            RestClient
                    .builder(
                            HttpHost("localhost", 9100),
                            HttpHost("localhost", 9101),
                            HttpHost("localhost", 9102)
                    )
    )
}