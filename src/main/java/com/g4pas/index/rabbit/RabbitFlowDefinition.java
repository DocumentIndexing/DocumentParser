package com.g4pas.index.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.MessageHandlerSpec;

//import org.springframework.integration.dsl.channel.MessageChannels;

@Configuration
@Profile("rabbitmq")
public class RabbitFlowDefinition {

    @Autowired
    private RabbitConfig rabbitConfig;

    @Bean
    public MessageHandlerSpec publishToIndexHandler( AmqpTemplate amqpTemplate ){

        return  Amqp.outboundAdapter(amqpTemplate).routingKey(rabbitConfig.getIndexQueueName());
    }

}
