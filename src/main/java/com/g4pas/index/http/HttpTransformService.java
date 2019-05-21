package com.g4pas.index.http;

import com.g4pas.index.exception.TransformServiceException;
import com.g4pas.index.model.payload.IndexDocumentBinaryRequest;
import com.g4pas.index.model.payload.IndexDocumentJsonRequest;
import com.g4pas.index.model.payload.Request;
import org.springframework.integration.http.multipart.UploadedMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

import java.io.IOException;
import java.util.LinkedList;

import static java.util.Base64.getDecoder;


/**
 * Add and specific conversion required when processing ah HTTP request
 */
@Service
public class HttpTransformService {
    public static final String URL = "Url";
    public static final String CONTENT = "Content";
    //    private static final Logger LOGGER = LoggerFactory.getLogger(HttpTransformService.class);

    /**
     * Transform the Json based request into the IndexDocumentBinaryRequest
     *
     * @param payload
     * @return
     */
    public Request transform(IndexDocumentJsonRequest payload) {


        return new IndexDocumentBinaryRequest().setContent(getDecoder().decode(payload.getContent()))
                                               .setFilename(payload.getFilename())
                                               .setUrl(payload.getUrl());
    }

    /**
     * Transform a Multipart Payload to an IndexDocumentBinaryRequest
     *
     * @param multipartPayload
     * @return
     * @throws TransformServiceException
     */
    public Request transform(LinkedMultiValueMap multipartPayload) throws TransformServiceException {

        try {


            return new IndexDocumentBinaryRequest().setContent(extractContent(multipartPayload))
                                                   .setFilename(extractFilename(multipartPayload))
                                                   .setUrl(extractURL(multipartPayload));
        } catch (IOException e) {
            throw new TransformServiceException("Failed to extract file contents from MultipartFile",
                                                e);
        }
    }

    private byte[] extractContent(final LinkedMultiValueMap multipartPayload) throws IOException {
        LinkedList<UploadedMultipartFile> content = (LinkedList<UploadedMultipartFile>) multipartPayload.get(CONTENT);
        UploadedMultipartFile file = content.getFirst();
        return file.getBytes();
    }

    private String extractFilename(final LinkedMultiValueMap multipartPayload) {
        LinkedList<UploadedMultipartFile> content = (LinkedList<UploadedMultipartFile>) multipartPayload.get(CONTENT);
        UploadedMultipartFile file = content.getFirst();
        String originalFilename = file.getOriginalFilename();
        return originalFilename;
    }

    private String extractURL(final LinkedMultiValueMap multipartPayload) {
        Object multipartPayloadObject = multipartPayload.get(URL)
                                                        .get(0);
        String returnURl = null;
        if (multipartPayloadObject instanceof String[]) {
            String[] urls = (String[]) multipartPayloadObject;
            returnURl = (urls.length > 0 ? urls[0] : null);
        }
        return returnURl;
    }
}
