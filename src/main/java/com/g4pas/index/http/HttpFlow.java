package com.g4pas.index.http;

import com.g4pas.index.model.payload.IndexDocumentJsonRequest;
import org.aopalliance.aop.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.dsl.Http;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.multipart.MultipartResolver;

@Configuration
@Profile("rest")


public class HttpFlow {

    @Autowired
    private HttpTransformService httpTransformationService;
//
//    @Autowired
//    private NullReturningTerminatingService terminatingService;

    @Bean
    public IntegrationFlow jsonOverHttp(MessageChannel processChannel,
                                        Advice retryAdvice,
                                        MessageChannel errorChannel,
                                        MultipartResolver multipartResolver) {


        final HttpRequestHandlingMessagingGateway inboundHttpGateway = Http.inboundGateway("/index/document")
                                                                           .requestPayloadType(IndexDocumentJsonRequest.class)
                                                                           .requestMapping(m -> m.methods(HttpMethod.POST,
                                                                                                          HttpMethod.PUT))
                                                                           .get();
        return IntegrationFlows.from(inboundHttpGateway)
                               .handle(httpTransformationService)
                               .gateway(processChannel,
                                        e -> e.advice(retryAdvice)
                                              .errorChannel(errorChannel)
                                              .requiresReply(false))//Call Gateway flow and then retry if any exception
                               .get();
    }

    @Bean
    public IntegrationFlow multipartOverHttp(MessageChannel processChannel,
                                             Advice retryAdvice,
                                             MessageChannel errorChannel,
                                             MultipartResolver multipartResolver) {


        final HttpRequestHandlingMessagingGateway inboundHttpGateway = Http.inboundGateway("/index/binary")
                                                                           .multipartResolver(multipartResolver)
                                                                           .requestMapping(m -> m.methods(HttpMethod.POST,
                                                                                                          HttpMethod.PUT))
                                                                           .get();
        return IntegrationFlows.from(inboundHttpGateway)
                               .handle(httpTransformationService)
                               .gateway(processChannel,
                                        e -> e.advice(retryAdvice)
                                              .errorChannel(errorChannel)
                                              .requiresReply(false))//Call Gateway flow and then retry if any exception
                               .get();
    }

}
