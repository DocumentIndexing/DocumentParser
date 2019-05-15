package com.g4pas.index.model.payload;

public class ParsedIndexDocumentRequest extends IndexDocumentRequest {
    private String rawContent;

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
}

