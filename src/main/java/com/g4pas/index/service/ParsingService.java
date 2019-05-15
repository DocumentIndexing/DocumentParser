package com.g4pas.index.service;

import com.g4pas.index.exception.ExtractContentException;
import com.g4pas.index.exception.ParserServiceException;
import com.g4pas.index.model.payload.IndexDocumentRequest;
import com.g4pas.index.model.payload.ParsedIndexDocumentRequest;
import com.g4pas.index.model.payload.Request;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaTypeRegistry;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.util.Base64.getDecoder;

/**
 * Transforms the Json String into the inbound message request instance.<br/>
 * This was originally in the DSL <code>.transform()</code> but the exception was not being handled correctly, so,
 * I have extract in to a Service component
 */
@Service
public class ParsingService {


    public static final int FALSE_IMAGE_PROCESSING = 0;

    public Request parse(IndexDocumentRequest request) throws ParserServiceException {
        final byte[] decode = getDecoder().decode(request.getContent());

        final int REMOVE_CHARACTER_LIMIT = -1;

        BodyContentHandler handler = new BodyContentHandler(REMOVE_CHARACTER_LIMIT);
        final MediaTypeRegistry defaultRegistry = MediaTypeRegistry.getDefaultRegistry();

        AutoDetectParser parser = new AutoDetectParser();
        TesseractOCRConfig x = new TesseractOCRConfig();
        x.setEnableImageProcessing(FALSE_IMAGE_PROCESSING);

        Metadata metadata = new Metadata();

        try (InputStream stream = new ByteArrayInputStream(decode)) {
            parser.parse(stream,
                         handler,
                         metadata);
            return new ParsedIndexDocumentRequest(request).setRawContent(handler.toString());

        } catch (IOException | SAXException | TikaException e) {
            throw new ExtractContentException("Failed to convert the binary array to plain text",
                                              e);
        }

    }
}
