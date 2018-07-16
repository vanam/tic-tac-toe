package com.martinvana.tic_tac_toe.config;

import com.martinvana.tic_tac_toe.exception.DomainException;

/**
 * The class is immutable and it is responsible for carrying information
 * about the square board and check the coordinates against its bounds.
 */
public final class BoardDimensionImpl implements BoardDimension {

    /**
     * Minimal dimension of the board.
     */
    private static final int MIN_DIMENSION = 3;

    /**
     * Maximal dimension of the board.
     */
    private static final int MAX_DIMENSION = 10;

    /**
     * The dimension of the board.
     */
    private final int dimension;

    /**
     * @param dimension The dimension of the board must be in range <<code>MIN_DIMENSION</code>, <code>MAX_DIMENSION</code>>
     * @throws DomainException Thrown if constraints of <code>dimension</code> attribute are not met.
     */
    public BoardDimensionImpl(final int dimension) throws DomainException {
        if (dimension < MIN_DIMENSION || dimension > MAX_DIMENSION) {
            throw new DomainException(String.format("A board dimension must be between %d and %d, %d given.", MIN_DIMENSION, MAX_DIMENSION, dimension));
        }

        this.dimension = dimension;
    }

    @Override
    public int getX() {
        return dimension;
    }

    @Override
    public int getY() {
        return dimension;
    }

    @Override
    public boolean areCoordinatesValid(final int x, final int y) {
        return x >= 0 && y >= 0 && x < dimension && y < dimension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BoardDimensionImpl that = (BoardDimensionImpl) o;

        return dimension == that.dimension;
    }

    @Override
    public int hashCode() {
        return dimension;
    }
}
