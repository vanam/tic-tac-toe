package com.martinvana.tic_tac_toe.game;

import com.martinvana.tic_tac_toe.config.BoardDimension;
import com.martinvana.tic_tac_toe.config.PlayCharacter;
import com.martinvana.tic_tac_toe.exception.DomainException;

/**
 * The class is immutable and represents a move made by player.
 */
public final class MoveImpl implements Move {

    /**
     * The x coordinate
     */
    private final int x;

    /**
     * The y coordinate
     */
    private final int y;

    /**
     * The play character
     */
    private PlayCharacter character;

    /**
     * @param x              x coordinate
     * @param y              y coordinate
     * @param boardDimension The board dimension
     * @param character      The play character
     * @throws DomainException Thrown if coordinate is out of board's bounds.
     */
    public MoveImpl(final int x, final int y, final BoardDimension boardDimension, final PlayCharacter character) throws DomainException {
        this.character = character;
        if (!boardDimension.areCoordinatesValid(x, y)) {
            throw new DomainException("Move is out of board bounds.");
        }

        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public char getCharacter() {
        return character.getCharacter();
    }
}
