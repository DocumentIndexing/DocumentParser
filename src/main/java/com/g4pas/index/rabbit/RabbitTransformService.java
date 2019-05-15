package com.g4pas.index.rabbit;

import com.g4pas.index.exception.TransformServiceException;
import com.g4pas.index.model.payload.IndexDocumentRequest;
import com.g4pas.index.model.payload.Request;
import com.g4pas.index.utils.JsonUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Transforms the Json String into the inbound message request instance.<br/>
 * This was originally in the DSL <code>.transform()</code> but the exception was not being handled correctly, so,
 * I have extract in to a Service component
 */
@Service
public class RabbitTransformService {

    public Request transform(String json) throws TransformServiceException {
        try {
            return JsonUtils.asObject(json,
                                      IndexDocumentRequest.class);
        } catch (IOException e) {
            throw new TransformServiceException("Failed to transform the payload into IndexDocumentRequest.class",
                                                e);
        }
    }
}
