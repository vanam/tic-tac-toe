package com.martinvana.tic_tac_toe.exception;

/**
 * The <code>ParseException</code> is used when command line argument parsing failed.
 */
public class ParseException extends Exception {
    public ParseException(String s) {
        super(s);
    }
}
