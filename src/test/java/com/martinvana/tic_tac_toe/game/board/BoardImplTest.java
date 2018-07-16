package com.martinvana.tic_tac_toe.game.board;

import com.martinvana.tic_tac_toe.config.BoardDimension;
import com.martinvana.tic_tac_toe.config.BoardDimensionImpl;
import com.martinvana.tic_tac_toe.config.PlayCharacter;
import com.martinvana.tic_tac_toe.extension.MockitoExtension;
import com.martinvana.tic_tac_toe.game.MoveImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BoardImplTest {
    private BoardDimension boardDimension;

    private PlayCharacter playCharacter;

    private Board board;

    private ComputerReadableBoard computerReadableBoard;

    private HumanReadableBoard humanReadableBoard;

    @BeforeEach
    void setUp(@Mock BoardDimension boardDimension, @Mock PlayCharacter playCharacter) {
        when(boardDimension.getX()).thenReturn(3);
        when(boardDimension.getY()).thenReturn(3);

        when(boardDimension.areCoordinatesValid(0, 0)).thenReturn(true);
        when(boardDimension.areCoordinatesValid(0, 1)).thenReturn(true);
        when(boardDimension.areCoordinatesValid(0, 2)).thenReturn(true);
        when(boardDimension.areCoordinatesValid(1, 0)).thenReturn(true);
        when(boardDimension.areCoordinatesValid(1, 1)).thenReturn(true);
        when(boardDimension.areCoordinatesValid(1, 2)).thenReturn(true);
        when(boardDimension.areCoordinatesValid(2, 0)).thenReturn(true);
        when(boardDimension.areCoordinatesValid(2, 1)).thenReturn(true);
        when(boardDimension.areCoordinatesValid(2, 2)).thenReturn(true);

        when(playCharacter.getCharacter()).thenReturn('C');

        this.boardDimension = boardDimension;
        this.playCharacter = playCharacter;
        this.board = new BoardImpl(boardDimension);
        this.computerReadableBoard = new BoardImpl(boardDimension);
        this.humanReadableBoard = new BoardImpl(boardDimension);
    }

    @Test
    void testApplyMove() {
        assertTrue(board.applyMove(new MoveImpl(0, 0, boardDimension, playCharacter)));
    }

    @Test
    void testAt() {
        board.applyMove(new MoveImpl(0, 0, boardDimension, playCharacter));

        assertEquals('C', board.at(0, 0));
        assertNotEquals('C', board.at(0, 1));
    }

    @Test
    void testIsFull() {
        board.applyMove(new MoveImpl(0, 0, boardDimension, playCharacter));
        board.applyMove(new MoveImpl(0, 1, boardDimension, playCharacter));
        board.applyMove(new MoveImpl(0, 2, boardDimension, playCharacter));
        board.applyMove(new MoveImpl(1, 0, boardDimension, playCharacter));
        board.applyMove(new MoveImpl(1, 1, boardDimension, playCharacter));
        board.applyMove(new MoveImpl(1, 2, boardDimension, playCharacter));
        board.applyMove(new MoveImpl(2, 0, boardDimension, playCharacter));
        board.applyMove(new MoveImpl(2, 1, boardDimension, playCharacter));
        board.applyMove(new MoveImpl(2, 2, boardDimension, playCharacter));

        assertTrue(board.isFull());
    }

    @Test
    @DisplayName("Test IsEmptyAt at existing position.")
    void testIsEmptyAt1() {
        assert computerReadableBoard.isEmptyAt(1, 2);
    }

    @ParameterizedTest
    @MethodSource("createInvalidCoordinates")
    @DisplayName("Test IsEmptyAt at non-existing position.")
    void testIsEmptyAt2() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> computerReadableBoard.isEmptyAt(5, 3));
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

    @Test
    @DisplayName("Test computerReadableBoard dimension getter.")
    void testGetBoardDimension() {
        BoardDimension boardDimension = new BoardDimensionImpl(3);

        assertEquals(boardDimension.getX(), computerReadableBoard.getBoardDimension().getX());
        assertEquals(boardDimension.getY(), computerReadableBoard.getBoardDimension().getY());
    }

}