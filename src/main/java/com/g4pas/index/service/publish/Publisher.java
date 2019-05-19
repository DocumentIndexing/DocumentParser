package com.g4pas.index.service.publish;

import com.g4pas.index.model.payload.Request;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface Publisher {
    @Gateway(requestChannel = "publishChannel")
    void publish(Request request);
}
