package com.martinvana.tic_tac_toe.game;

/**
 * The interface for a move made by players
 */
public interface Move {

    /**
     * @return Return x coordinate of the move.
     */
    int getX();

    /**
     * @return Return y coordinate of the move.
     */
    int getY();

    /**
     * @return Return play character of player who made this move.
     */
    char getCharacter();
}
