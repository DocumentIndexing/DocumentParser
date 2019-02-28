package com.g4pas.index.http;

import com.g4pas.index.exception.TransformServiceException;
import com.g4pas.index.model.request.Request;
import com.g4pas.index.model.request.RequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

/**
 * Transforms the Json String into the inbound message request instance.<br/>
 * This was originally in the DSL <code>.transform()</code> but the exception was not being handled correctly, so,
 * I have extract in to a Service component
 */
@Service
public class HttpTransformService {

    public Request transform(MultiValueMap payload) throws TransformServiceException {
        //TODO
        throw new TransformServiceException("MultiValeMap Not Supported");
//          return RequestFactory.getInstance(payload);
    }
}
