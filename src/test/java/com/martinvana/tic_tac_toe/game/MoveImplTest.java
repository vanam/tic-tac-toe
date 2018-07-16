package com.martinvana.tic_tac_toe.game;

import com.martinvana.tic_tac_toe.config.BoardDimension;
import com.martinvana.tic_tac_toe.config.PlayCharacter;
import com.martinvana.tic_tac_toe.exception.DomainException;
import com.martinvana.tic_tac_toe.extension.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoveImplTest {
    private BoardDimension boardDimension;

    private PlayCharacter playCharacter;

    @BeforeEach
    void setUp(@Mock BoardDimension boardDimension, @Mock PlayCharacter playCharacter) {
        this.boardDimension = boardDimension;
        this.playCharacter = playCharacter;
    }

    @Test
    @DisplayName("Construct move object on non-empty position.")
    void testInvalidConstructor() {
        when(boardDimension.areCoordinatesValid(1, 2)).thenReturn(false);

        assertThrows(DomainException.class,
                () -> new MoveImpl(1, 2, boardDimension, playCharacter));
    }

    @Test
    @DisplayName("Test x coordinate getter.")
    void testGetX() {
        when(boardDimension.areCoordinatesValid(1, 2)).thenReturn(true);

        Move move = new MoveImpl(1, 2, boardDimension, playCharacter);
        assertEquals(1, move.getX());
    }

    @Test
    @DisplayName("Test y coordinate getter.")
    void testGetY() {
        when(boardDimension.areCoordinatesValid(1, 2)).thenReturn(true);

        Move move = new MoveImpl(1, 2, boardDimension, playCharacter);
        assertEquals(2, move.getY());
    }

    @Test
    @DisplayName("Test play character getter.")
    void testGetCharacter() {
        when(boardDimension.areCoordinatesValid(1, 2)).thenReturn(true);
        when(playCharacter.getCharacter()).thenReturn('T');

        Move move = new MoveImpl(1, 2, boardDimension, playCharacter);
        assertEquals('T', move.getCharacter());
    }

}