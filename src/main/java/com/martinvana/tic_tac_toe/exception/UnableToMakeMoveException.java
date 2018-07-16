package com.martinvana.tic_tac_toe.exception;

/**
 * The <code>UnableToMakeMoveException</code> is thrown if a player is unable to make move.
 */
public class UnableToMakeMoveException extends RuntimeException {
    public UnableToMakeMoveException(String s) {
        super(s);
    }
}
