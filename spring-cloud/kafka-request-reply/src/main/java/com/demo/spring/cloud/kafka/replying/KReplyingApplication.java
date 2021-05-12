package com.demo.spring.cloud.kafka.replying;

import lombok.SneakyThrows;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.SimpleKafkaHeaderMapper;
import org.springframework.kafka.support.converter.MessagingMessageConverter;
import org.springframework.messaging.handler.annotation.SendTo;

import java.time.Duration;

@SpringBootApplication
public class KReplyingApplication {
    public static void main(String[] args) {
        SpringApplication.run(KReplyingApplication.class, args);
    }

    @SneakyThrows
    @KafkaListener(id = "server", topics = "kRequests")
    @SendTo // use default replyTo expression
    public String listen(String in) {
        System.out.append("kafka.").println("Server received: " + in);
        Thread.sleep(Duration.ofSeconds(4).toMillis());
        System.out.append("kafka.").println("Server Resent: " + in);
        return in.toUpperCase();
    }

    @Bean
    public NewTopic kRequests() {
        return TopicBuilder.name("kRequests")
                .partitions(10)
                .replicas(2)
                .build();
    }

    @Bean // not required if Jackson is on the classpath
    public MessagingMessageConverter simpleMapperConverter() {
        MessagingMessageConverter messagingMessageConverter = new MessagingMessageConverter();
        messagingMessageConverter.setHeaderMapper(new SimpleKafkaHeaderMapper());
        return messagingMessageConverter;
    }
}
