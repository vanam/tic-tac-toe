package com.martinvana.tic_tac_toe.game.board;

import com.martinvana.tic_tac_toe.config.BoardDimension;

/**
 * The interface for a game board as seen by human player.
 */
public interface HumanReadableBoard {

    /**
     * @return Return board dimensions.
     */
    BoardDimension getBoardDimension();
}
