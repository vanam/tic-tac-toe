package com.martinvana.tic_tac_toe.game.player;

import com.martinvana.tic_tac_toe.config.BoardDimension;
import com.martinvana.tic_tac_toe.config.PlayCharacter;
import com.martinvana.tic_tac_toe.exception.UnableToMakeMoveException;
import com.martinvana.tic_tac_toe.game.Move;
import com.martinvana.tic_tac_toe.game.MoveImpl;
import com.martinvana.tic_tac_toe.game.board.ComputerReadableBoard;

/**
 * The class of a computer player who makes a move on a first empty position on a row.
 */
public final class RowEatingComputerPlayer extends ComputerPlayer {

    /**
     * @param character The play character.
     * @param board     The board as seen by computer.
     */
    public RowEatingComputerPlayer(final PlayCharacter character, final ComputerReadableBoard board) {
        super(character, board);
    }

    @Override
    public Move makeMove() throws UnableToMakeMoveException {
        BoardDimension boardDimension = board.getBoardDimension();

        int dimX = boardDimension.getX();
        int dimY = boardDimension.getY();

        // Loop the board row by row until it finds empty position
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                if (board.isEmptyAt(i, j)) {
                    return new MoveImpl(i, j, boardDimension, character);
                }
            }
        }

        throw new UnableToMakeMoveException("Unable to make a move due to full board.");
    }
}
