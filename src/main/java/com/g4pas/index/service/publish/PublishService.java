package com.g4pas.index.service.publish;

import com.g4pas.index.model.payload.IndexDocumentJsonRequest;
import com.g4pas.index.model.payload.ParsedIndexDocumentRequest;
import com.g4pas.index.model.payload.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Publishes the request to the outbound message channel, the final destination will be dependent on the configuration
 * and profile enabled (i.e. Kafka or RabbitMQ)
 */
@Service
public class PublishService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublishService.class);
    //Disable the limit of number of characters that can be procesed

    @Autowired
    private Publisher publisher;

    public Request publish(ParsedIndexDocumentRequest request) {

        publisher.publish(request);

        return request;
    }
}
