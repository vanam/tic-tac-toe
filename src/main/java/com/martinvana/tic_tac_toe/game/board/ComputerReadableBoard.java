package com.martinvana.tic_tac_toe.game.board;

import com.martinvana.tic_tac_toe.config.BoardDimension;

/**
 * The interface for a game board as seen by computer player.
 */
public interface ComputerReadableBoard {

    /**
     * Return character on the specified coordinates.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return Return character on board.
     * @throws IndexOutOfBoundsException Thrown if coordinates are out of bounds.
     */
    boolean isEmptyAt(final int x, final int y) throws IndexOutOfBoundsException;

    /**
     * @return Return board dimensions.
     */
    BoardDimension getBoardDimension();
}
