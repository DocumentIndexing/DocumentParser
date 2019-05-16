package com.g4pas.index.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.consumer")
@Profile("rabbitmq")
@Component
public class RabbitConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConfig.class);
    private String indexQueueName;
    private int concurrentConsumers = 1;

    public String getIndexQueueName() {
        return indexQueueName;
    }

    public RabbitConfig setIndexQueueName(final String indexQueueName) {
        this.indexQueueName = indexQueueName;
        LOGGER.info("setIndexQueueName([indexQueueName]) : set with {}",
                    indexQueueName);
        return this;
    }

    public int getConcurrentConsumers() {
        return concurrentConsumers;
    }

    public void setConcurrentConsumers(final int concurrentConsumers) {
        this.concurrentConsumers = concurrentConsumers;
    }

}
