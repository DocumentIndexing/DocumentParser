package com.g4pas.index.model.payload;

public class IndexDocumentJsonRequest implements Request {

    private String url;
    private String filename;
    private String content;

    public String getUrl() {
        return url;
    }

    public IndexDocumentJsonRequest setUrl(final String url) {
        this.url = url;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public IndexDocumentJsonRequest setFilename(final String filename) {
        this.filename = filename;
        return this;
    }

    public String getContent() {
        return content;
    }

    public IndexDocumentJsonRequest setContent(final String content) {
        this.content = content;
        return this;
    }
}

