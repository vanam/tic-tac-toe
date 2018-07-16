package com.martinvana.tic_tac_toe.game.player;

import com.martinvana.tic_tac_toe.config.PlayCharacter;
import com.martinvana.tic_tac_toe.game.board.ComputerReadableBoard;

/**
 * The abstract class for computer player.
 */
public abstract class ComputerPlayer extends APlayer {

    /**
     * The board as seen by computer.
     */
    protected final ComputerReadableBoard board;

    /**
     * @param character The play character.
     * @param board     The board as seen by computer.
     */
    public ComputerPlayer(final PlayCharacter character, final ComputerReadableBoard board) {
        super(character);

        this.board = board;
    }
}
