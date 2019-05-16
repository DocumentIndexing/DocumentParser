package com.g4pas.index.file;

import org.aopalliance.aop.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.file.dsl.Files;
import org.springframework.messaging.MessageChannel;


@Configuration
@Profile("file")
public class FileFlow {

    @Autowired
    FileConfiguration config;
//
//    @Bean
//    public IntegrationFlow fromHttp(MessageChannel processChannel,
//                                    Advice retryAdvice,
//                                    MessageChannel errorChannel) {
//        Files.inboundAdapter(con)
//    }
}
