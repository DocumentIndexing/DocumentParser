package com.g4pas.index.service.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.*;
import org.springframework.messaging.MessageChannel;

import javax.annotation.PostConstruct;

/**
 * Builds the Integration Flow for the consumer
 **/

@Configuration
public class PublishFlowDefinition {

    public static final String PUBLISH_CHANNEL = "publishChannel";


    private static final Logger LOGGER = LoggerFactory.getLogger(PublishFlowDefinition.class);


    @Autowired
    private MessageHandlerSpec publishToIndexHandler;



    @PostConstruct
    public void init() {
        LOGGER.warn("init([]) : Initialising complete(ish)...");
    }


    @Bean
    public MessageChannel publishChannel() {
        return MessageChannels.direct(PUBLISH_CHANNEL)
                              .get();
    }



    @Bean
    public IntegrationFlow publishFlow(MessageChannel publishChannel) {

        return IntegrationFlows
                .from(publishChannel)
                .transform(Transformers.toJson())
                .handle(publishToIndexHandler)
                .transform(Transformers.toMap())
                .get();
    }


}
