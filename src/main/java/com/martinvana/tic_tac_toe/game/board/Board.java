package com.martinvana.tic_tac_toe.game.board;

import com.martinvana.tic_tac_toe.exception.DomainException;
import com.martinvana.tic_tac_toe.game.Move;

/**
 * The interface for a game board as seen by game engine.
 */
public interface Board extends HumanReadableBoard {

    /**
     * Apply move if position on the board is empty.
     *
     * @param move A move to apply
     * @return Return true if move was applied, false otherwise.
     * @throws DomainException Thrown if <code>move</code> parameter is null.
     */
    boolean applyMove(Move move) throws DomainException;

    /**
     * Return character on the specified coordinates.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return Return character on board.
     * @throws IndexOutOfBoundsException Thrown if coordinates are out of bounds.
     */
    char at(int x, int y) throws IndexOutOfBoundsException;

    /**
     * @return Return <code>true</code> if the board is full, <code>false</code> otherwise.
     */
    boolean isFull();
}
