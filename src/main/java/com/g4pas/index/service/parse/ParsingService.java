package com.g4pas.index.service.parse;

import com.g4pas.index.exception.ParserServiceException;
import com.g4pas.index.model.payload.IndexDocumentBinaryRequest;
import com.g4pas.index.model.payload.ParsedIndexDocumentRequest;
import com.g4pas.index.model.payload.Request;
import com.g4pas.index.service.util.TikaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Transforms the Json String into the inbound message request instance.<br/>
 * This was originally in the DSL <code>.transform()</code> but the exception was not being handled correctly, so,
 * I have extract in to a Service component
 */
@Service
public class ParsingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParsingService.class);
    //Disable the limit of number of characters that can be procesed


    /**
     * Parse the binary document into clear text
     *
     * @param request
     * @return
     * @throws ParserServiceException
     */
    public Request parse(IndexDocumentBinaryRequest request)
            throws ParserServiceException {

        TikaUtil.ParsedData data = TikaUtil.parse(request.getContent());

        return new ParsedIndexDocumentRequest(request).setContent(data.getText())
                                                      .setMetadata(data.getMetadata())
                                                      .setId(request.getUrl());
    }
}
