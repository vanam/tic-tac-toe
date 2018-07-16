package com.martinvana.tic_tac_toe.game.player;

import com.martinvana.tic_tac_toe.exception.UnableToMakeMoveException;
import com.martinvana.tic_tac_toe.game.Move;

/**
 * The player interface.
 */
public interface Player {

    /**
     * @return Return player character.
     */
    char getCharacter();

    /**
     * @return Return a move which player wants to make.
     * @throws UnableToMakeMoveException Thrown if player is unable to make move.
     */
    Move makeMove() throws UnableToMakeMoveException;
}
