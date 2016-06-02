package com.sparksdev.flo.dao.exception;

/**
 * @author bengill
 */
public class DuplicateEntityException extends RuntimeException {

    /**
     *
     * @param message
     */
    public DuplicateEntityException(final String message) {
        super(message);
    }


}
