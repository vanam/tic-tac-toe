package com.martinvana.tic_tac_toe.game.player;

import com.martinvana.tic_tac_toe.config.BoardDimensionImpl;
import com.martinvana.tic_tac_toe.exception.UnableToMakeMoveException;
import com.martinvana.tic_tac_toe.extension.MockitoExtension;
import com.martinvana.tic_tac_toe.game.Move;
import com.martinvana.tic_tac_toe.game.board.HumanReadableBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HumanPlayerTest extends APlayerTest {

    private HumanReadableBoard humanReadableBoard;

    @Override
    Player getPlayerObject() {
        return getPlayerObject("0,1");
    }

    private Player getPlayerObject(String data) {
        return new HumanPlayer(playCharacter, humanReadableBoard, new ByteArrayInputStream(data.getBytes()));
    }

    @BeforeEach
    void setUp(@Mock HumanReadableBoard humanReadableBoard) {
        this.humanReadableBoard = humanReadableBoard;
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "0,1",
            "\n0,1",
            "afsdf\n0,1\n",
    })
    @DisplayName("Test make a move.")
    void testMakeMove() {
        when(humanReadableBoard.getBoardDimension()).thenReturn(new BoardDimensionImpl(3));

        Move move = getPlayerObject().makeMove();
        assertEquals(0, move.getX());
        assertEquals(1, move.getY());
        assertEquals('C', move.getCharacter());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "0, 1\n",
            "0-1\n",
            "0 1\n",
            "0 ,1\n",
    })
    @DisplayName("Test unable to make a move due to invalid input.")
    void testUnableToMakeMove(String input) {
        when(humanReadableBoard.getBoardDimension()).thenReturn(new BoardDimensionImpl(3));

        assertThrows(UnableToMakeMoveException.class,
                () -> getPlayerObject(input).makeMove());
    }

}