package com.martinvana.tic_tac_toe.exception;

/**
 * The <code>DomainException</code> signals that object could not be constructed with given arguments.
 */
public class DomainException extends IllegalArgumentException {
    public DomainException(String s) {
        super(s);
    }
}
