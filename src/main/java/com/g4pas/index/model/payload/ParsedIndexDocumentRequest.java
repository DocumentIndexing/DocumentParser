package com.g4pas.index.model.payload;

import java.util.Map;

public class ParsedIndexDocumentRequest extends IndexDocumentRequest {
    private String rawContent;
    private Map<String, String[]> metadata;

    public ParsedIndexDocumentRequest(final IndexDocumentRequest request) {
        super();
        this.setContent(request.getContent());
        this.setFilename(request.getFilename());
        this.setUrl(request.getUrl());
    }

    public String getRawContent() {
        return rawContent;
    }

    public ParsedIndexDocumentRequest setRawContent(final String rawContent) {
        this.rawContent = rawContent;
        return this;
    }

    public ParsedIndexDocumentRequest setMetadata(final Map<String, String[]> metadata) {
        this.metadata = metadata;
        return this;
    }

    public Map<String, String[]> getMetadata() {
        return metadata;
    }
}

