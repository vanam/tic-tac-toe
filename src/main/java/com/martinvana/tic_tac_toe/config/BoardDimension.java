package com.martinvana.tic_tac_toe.config;

/**
 * The interface for a board dimension.
 */
public interface BoardDimension {

    /**
     * Get the length of x axis.
     *
     * @return the length of x axis
     */
    int getX();

    /**
     * Get the length of y axis.
     *
     * @return the length of y axis
     */
    int getY();

    /**
     * Decide whether the coordinates are in bounds of a board.
     * <p>
     * The coordinates are in bounds if x in <0, maxX) and y in <0, maxY).
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return <code>true</code> if coordinates are valid, <code>false</code> otherwise
     */
    boolean areCoordinatesValid(final int x, final int y);
}
