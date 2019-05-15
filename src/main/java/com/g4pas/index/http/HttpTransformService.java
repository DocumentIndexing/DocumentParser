package com.g4pas.index.http;

import com.g4pas.index.exception.TransformServiceException;
import com.g4pas.index.model.payload.Request;
import org.springframework.stereotype.Service;

/**
 * Add and specific conversion required when processing ah HTTP request
 */
@Service
public class HttpTransformService {

    public Request transform(Request payload) throws TransformServiceException {
        //TODO
        return payload;// Nothing to add at the moment
    }
}
