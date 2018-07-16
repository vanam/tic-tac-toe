package com.martinvana.tic_tac_toe.config;

import com.martinvana.tic_tac_toe.exception.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BoardDimensionImplTest {
    @Test
    @DisplayName("Construct invalid object with with minimum board size - 1.")
    void testInvalidConstructor1() {
        assertThrows(DomainException.class,
                () -> new BoardDimensionImpl(2));
    }

    @Test
    @DisplayName("Construct invalid object with with maximum board size + 1.")
    void testInvalidConstructor2() {
        assertThrows(DomainException.class,
                () -> new BoardDimensionImpl(11));
    }

    @Test
    @DisplayName("Construct valid object with minimum board size.")
    void testValidConstructor1() throws DomainException {
        new BoardDimensionImpl(3);
    }

    @Test
    @DisplayName("Construct valid object with maximum board size.")
    void testValidConstructor2() throws DomainException {
        new BoardDimensionImpl(10);
    }

    @Test
    @DisplayName("Test dimension getters.")
    void testDimensionGetters() throws DomainException {
        int dimension = 5;

        BoardDimension bd = new BoardDimensionImpl(dimension);

        assertEquals(dimension, bd.getX());
        assertEquals(dimension, bd.getY());
    }

    @ParameterizedTest
    @MethodSource("createValidCoordinates")
    @DisplayName("Test areCoordinatesValid method with valid input.")
    void testAreCoordinatesValid(int x, int y) {
        BoardDimension bd = new BoardDimensionImpl(5);

        assertTrue(bd.areCoordinatesValid(x, y));
    }

    private static Stream<Arguments> createValidCoordinates() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(0, 4),
                Arguments.of(4, 0),
                Arguments.of(4, 4),
                Arguments.of(2, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("createInvalidCoordinates")
    @DisplayName("Test areCoordinatesValid method with invalid input.")
    void testAreCoordinatesInvalid(int x, int y) {
        BoardDimension bd = new BoardDimensionImpl(5);
        assertFalse(bd.areCoordinatesValid(x, y));
    }

    private static Stream<Arguments> createInvalidCoordinates() {
        return Stream.of(
                Arguments.of(-1, 0),
                Arguments.of(0, -1),
                Arguments.of(4, 5),
                Arguments.of(5, 4),
                Arguments.of(5, 5)
        );
    }
}