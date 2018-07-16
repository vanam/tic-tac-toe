package com.martinvana.tic_tac_toe.game.player;

import com.martinvana.tic_tac_toe.config.BoardDimensionImpl;
import com.martinvana.tic_tac_toe.exception.UnableToMakeMoveException;
import com.martinvana.tic_tac_toe.extension.MockitoExtension;
import com.martinvana.tic_tac_toe.game.Move;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RowEatingComputerPlayerTest extends AComputerPlayerTest {

    @Override
    Player getPlayerObject() {
        return new RowEatingComputerPlayer(playCharacter, computerReadableBoard);
    }

    @Test
    @DisplayName("Test two consecutive moves to be in a row.")
    void testMakeMove() {
        when(computerReadableBoard.getBoardDimension()).thenReturn(new BoardDimensionImpl(3));

        when(computerReadableBoard.isEmptyAt(0, 0)).thenReturn(true);

        Move move = getPlayerObject().makeMove();

        assertEquals(0, move.getX());
        assertEquals(0, move.getY());
        assertEquals('C', move.getCharacter());

        when(computerReadableBoard.isEmptyAt(0, 0)).thenReturn(false);
        when(computerReadableBoard.isEmptyAt(0, 1)).thenReturn(true);

        move = getPlayerObject().makeMove();
        assertEquals(0, move.getX());
        assertEquals(1, move.getY());
        assertEquals('C', move.getCharacter());
    }

    @Test
    @DisplayName("Test unable to make a move.")
    void testUnableToMakeMove() {
        when(computerReadableBoard.getBoardDimension()).thenReturn(new BoardDimensionImpl(3));

        assertThrows(UnableToMakeMoveException.class,
                () -> getPlayerObject().makeMove());
    }

}