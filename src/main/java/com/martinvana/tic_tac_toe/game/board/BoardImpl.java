package com.martinvana.tic_tac_toe.game.board;

import com.martinvana.tic_tac_toe.config.BoardDimension;
import com.martinvana.tic_tac_toe.exception.DomainException;
import com.martinvana.tic_tac_toe.game.Move;

import java.util.Arrays;

/**
 * The class represents a game board.
 */
public final class BoardImpl implements Board, HumanReadableBoard, ComputerReadableBoard {

    /**
     * The character representing an empty space on board.
     */
    private static final char CHAR_EMPTY = '.';

    /**
     * The gama board representation.
     */
    private char[][] board;

    /**
     * The board dimension and bound checker.
     */
    private BoardDimension boardDimension;

    /**
     * The number of empty positions on the board.
     */
    private int emptyPositions;

    /**
     * @param boardDimension The board dimension
     */
    public BoardImpl(final BoardDimension boardDimension) {
        this.boardDimension = boardDimension;
        this.board = new char[boardDimension.getX()][boardDimension.getY()];
        this.emptyPositions = boardDimension.getX() * boardDimension.getY();

        // Initialize board empty character
        for (char[] row : board) {
            Arrays.fill(row, CHAR_EMPTY);
        }
    }

    @Override
    public boolean applyMove(final Move move) throws DomainException {
        if (move == null) {
            throw new DomainException("Move cannot be null.");
        }

        int x = move.getX();
        int y = move.getY();

        if (board[x][y] == CHAR_EMPTY) {
            board[x][y] = move.getCharacter();
            // Decrement the number of free positions
            emptyPositions--;

            return true;
        }

        return false;
    }

    @Override
    public char at(final int x, final int y) throws IndexOutOfBoundsException {
        if (!boardDimension.areCoordinatesValid(x, y)) {
            throw new IndexOutOfBoundsException();
        }

        return board[x][y];
    }

    @Override
    public boolean isEmptyAt(final int x, final int y) throws IndexOutOfBoundsException {
        if (!boardDimension.areCoordinatesValid(x, y)) {
            throw new IndexOutOfBoundsException();
        }

        return board[x][y] == CHAR_EMPTY;
    }

    @Override
    public BoardDimension getBoardDimension() {
        return boardDimension;
    }

    @Override
    public boolean isFull() {
        return emptyPositions == 0;
    }

    /**
     * @return Return string representation of the board.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");

        for (char[] row : board) {
            sb.append(String.format("%s\n", new String(row)));
        }
        return sb.toString();
    }
}
