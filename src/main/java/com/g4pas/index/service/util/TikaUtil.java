package com.g4pas.index.service.util;

import com.g4pas.index.exception.ExtractContentException;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Base64.getDecoder;

/**
 * Tika does not handle Json file so extract separately
 */
public class TikaUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(TikaUtil.class);
    private static final int REMOVE_CHARACTER_LIMIT = -1;
    final static AutoDetectParser autoDetectParser;

    static {
        DefaultResourceLoader loader = new DefaultResourceLoader();
        String tikaConfigLocation = "classpath:/config/tika-config.xml";
        Resource tikaConfig = loader.getResource(tikaConfigLocation);
        if (!tikaConfig.exists()) {
            LOGGER.warn("static initializer() : Failed to find tika-configuration : {}",
                        tikaConfigLocation);
        }
        TikaConfig config = null;
        try {
            config = new TikaConfig(tikaConfig.getFile());
        } catch (TikaException | IOException | SAXException e) {
            LOGGER.error("static initializer() : Failed to initialise Tika",
                         e);
        }

        autoDetectParser = new AutoDetectParser(config);
    }

    /**
     * Parse the data from a Base64 string representation of the data
     *
     * @param binaryBase64
     * @return
     * @throws ExtractContentException
     */
    public static ParsedData parse(String binaryBase64) throws ExtractContentException {
        return parse(getDecoder().decode(binaryBase64));
    }

    public static ParsedData parse(byte[] binaryData) throws ExtractContentException {
        BodyContentHandler handler = new BodyContentHandler(REMOVE_CHARACTER_LIMIT);

        try (InputStream stream = new ByteArrayInputStream(binaryData)) {
            Metadata metadata = new Metadata();
            autoDetectParser.parse(stream,
                                   handler,
                                   metadata,
                                   new ParseContext());
            ParsedData parsedData = new ParsedData();
            Arrays.stream(metadata.names())
                  .forEach(property -> parsedData.addMetadata(property,
                                                              metadata.getValues(property)));
            parsedData.setText(handler.toString());
            return parsedData;
        } catch (IOException | SAXException | TikaException e) {
            throw new ExtractContentException("Failed to convert the binary array to plain text",
                                              e);
        }
    }

    public static class ParsedData {


        private String text;
        private Map<String, String[]> metadata = new HashMap<>();

        //Instantiate only in Util
        private ParsedData() {
        }

        private ParsedData addMetadata(String name, String[] metaData) {
            this.metadata.put(name,
                              metaData);
            return this;
        }

        public Map<String, String[]> getMetadata() {
            return metadata;
        }

        private ParsedData setText(final String text) {
            this.text = text;
            return this;
        }

        public String getText() {
            return text;
        }
    }
}