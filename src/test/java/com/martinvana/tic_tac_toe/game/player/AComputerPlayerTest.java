package com.martinvana.tic_tac_toe.game.player;

import com.martinvana.tic_tac_toe.extension.MockitoExtension;
import com.martinvana.tic_tac_toe.game.board.ComputerReadableBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
abstract class AComputerPlayerTest extends APlayerTest {

    protected ComputerReadableBoard computerReadableBoard;

    @BeforeEach
    void setUp(@Mock ComputerReadableBoard computerReadableBoard) {
        this.computerReadableBoard = computerReadableBoard;
    }

}