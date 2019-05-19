package com.g4pas.index.http;

import com.g4pas.index.model.payload.IndexDocumentRequest;
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
    public IntegrationFlow fromHttp(MessageChannel processChannel,
                                    Advice retryAdvice,
                                    MessageChannel errorChannel,
                                    MultipartResolver multipartResolver) {


        final HttpRequestHandlingMessagingGateway inboundHttpGateway = Http.inboundGateway("/index/document")
                                                                           .requestPayloadType(IndexDocumentRequest.class)
                                                                           .requestMapping(m -> m.methods(HttpMethod.POST))
                                                                           .get();
        return IntegrationFlows.from(inboundHttpGateway)
                               .handle(httpTransformationService)
                               .gateway(processChannel,
                                        e -> e.advice(retryAdvice)
                                              .errorChannel(errorChannel)
                                              .requiresReply(false))//Call Gateway flow and then retry if any exception
//                               .handle(terminatingService)// Fix the integration flow issue to return to the queue and pick up another, otherwise it just waits
                               .log("Returned")
                               .get();
    }


}
