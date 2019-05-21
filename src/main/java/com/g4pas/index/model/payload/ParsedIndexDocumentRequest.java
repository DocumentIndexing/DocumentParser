package com.g4pas.index.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ParsedIndexDocumentRequest implements Request {
    private String id;




    private String content;
    private Map<String, String[]> metadata;
    private String url;
    private String filename;

    public ParsedIndexDocumentRequest(final IndexDocumentBinaryRequest request) {
        super();
        this.setFilename(request.getFilename());
        this.setUrl(request.getUrl());
    }

    public String getContent() {
        return content;
    }

    public ParsedIndexDocumentRequest setContent(final String content) {
        this.content = content;
        return this;
    }

    public ParsedIndexDocumentRequest setMetadata(final Map<String, String[]> metadata) {
        this.metadata = metadata;
        return this;
    }

    public Map<String, String[]> getMetadata() {
        return metadata;
    }

    public String getUrl() {
        return url;
    }

    public ParsedIndexDocumentRequest setUrl(final String url) {
        this.url = url;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public ParsedIndexDocumentRequest setFilename(final String filename) {
        this.filename = filename;
        return this;
    }

    public String getId() {
        return id;
    }

    public ParsedIndexDocumentRequest setId(final String id) {
        this.id = id;
        return this;
    }
}

