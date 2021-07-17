package com.curiosity.config;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {
    @Bean
    RestHighLevelClient restHighLevelClient() {
      return    new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.173.10", 9200, "http")
                       ));
    }
}
