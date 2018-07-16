package com.martinvana.tic_tac_toe.game.player;

import com.martinvana.tic_tac_toe.config.PlayCharacter;
import com.martinvana.tic_tac_toe.extension.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
abstract class APlayerTest {

    protected PlayCharacter playCharacter;

    @BeforeEach
    void setUp(@Mock PlayCharacter playCharacter) {
        when(playCharacter.getCharacter()).thenReturn('C');

        this.playCharacter = playCharacter;
    }

    abstract Player getPlayerObject();

    @Test
    @DisplayName("Test play character getter.")
    void testGetCharacter() {
        assertEquals('C', getPlayerObject().getCharacter());
    }

}