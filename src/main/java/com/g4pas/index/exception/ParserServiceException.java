package com.g4pas.index.exception;

/**
 * Generic Exception thrown within The Enriching Flow
 */
public class ParserServiceException extends Exception {
    public ParserServiceException() {
    }

    public ParserServiceException(final String message) {
        super(message);
    }

    public ParserServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ParserServiceException(final Throwable cause) {
        super(cause);
    }

    public ParserServiceException(final String message, final Throwable cause, final boolean enableSuppression,
                                  final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
