//package com.demo.spring.data.config;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.http.HttpHeaders;
//
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
////@Configuration
//public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {
//
//    @Bean
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("some-header", "on every request");
//
//        return RestClients.create(
//                ClientConfiguration
//                        .builder()
//                        .connectedTo("localhost:9200")
////                        .usingSsl()
////                        .withProxy("localhost:8888")
////                        .withPathPrefix("ela")
////                        .withConnectTimeout(Duration.ofSeconds(5))
////                        .withSocketTimeout(Duration.ofSeconds(3))
////                        .withDefaultHeaders(httpHeaders)
//                        //.withBasicAuth(username, password)
////                        .withHeaders(() -> {
////                            HttpHeaders headers = new HttpHeaders();
////                            headers.add("currentTime", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
////                            return headers;
////                        })
//                        .build()
//        ).rest();
//    }
//
//    @Bean(name = { "elasticsearchOperations", "elasticsearchTemplate" })
//    public ElasticsearchOperations elasticsearchTemplate() {
//        return new ElasticsearchRestTemplate(elasticsearchClient());
//    }
//}
