package com.g4pas.index.model.payload;

public class IndexDocumentBinaryRequest implements Request {

    private String url;
    private String filename;
    private byte[] content;

    public String getUrl() {
        return url;
    }

    public IndexDocumentBinaryRequest setUrl(final String url) {
        this.url = url;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public IndexDocumentBinaryRequest setFilename(final String filename) {
        this.filename = filename;
        return this;
    }

    public byte[] getContent() {
        return content;
    }

    public IndexDocumentBinaryRequest setContent(final byte[] content) {
        this.content = content;
        return this;
    }
}

