package com.martinvana.tic_tac_toe.game;

import com.martinvana.tic_tac_toe.config.BoardDimension;
import com.martinvana.tic_tac_toe.exception.DomainException;
import com.martinvana.tic_tac_toe.extension.MockitoExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RowImplTest {

    @ParameterizedTest
    @ValueSource(ints = {
            1, 2, 3, 4
    })
    @DisplayName("Construct object with valid row length.")
    void testValidLength(int length, @Mock BoardDimension boardDimension) {
        when(boardDimension.getX()).thenReturn(4);
        when(boardDimension.getY()).thenReturn(4);

        Row row = new RowImpl(length, boardDimension);
        assertEquals(length, row.getLength());
    }

    @ParameterizedTest
    @ValueSource(ints = {
            0, 5
    })
    @DisplayName("Construct object with invalid row length.")
    void testInvalidLength(int length, @Mock BoardDimension boardDimension) {
        when(boardDimension.getX()).thenReturn(4);
        when(boardDimension.getY()).thenReturn(4);

        assertThrows(DomainException.class,
                () -> new RowImpl(length, boardDimension));
    }
}