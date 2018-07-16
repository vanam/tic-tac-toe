package com.martinvana.tic_tac_toe.game;

import com.martinvana.tic_tac_toe.config.BoardDimension;
import com.martinvana.tic_tac_toe.exception.DomainException;

/**
 * The class is immutable and represents the winning row.
 */
public final class RowImpl implements Row {

    /**
     * The length of the winning row.
     */
    private final int length;

    /**
     * @param length The length of the winning row.
     * @param boardDimension The board dimension.
     * @throws DomainException Thrown if the <code>length</code> is out of bound set by the board dimension.
     */
    public RowImpl(final int length, final BoardDimension boardDimension) throws DomainException {
        int dimension = Math.min(boardDimension.getX(), boardDimension.getY());

        if (length < 1 || length > dimension) {
            throw new DomainException(String.format("Winning row length must be in interval <1, %d>, %d given.", dimension, length));
        }

        this.length = length;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RowImpl row = (RowImpl) o;

        return length == row.length;
    }

    @Override
    public int hashCode() {
        return length;
    }
}
