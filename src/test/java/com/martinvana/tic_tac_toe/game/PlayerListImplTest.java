package com.martinvana.tic_tac_toe.game;

import com.martinvana.tic_tac_toe.config.PlayCharacterImpl;
import com.martinvana.tic_tac_toe.exception.DomainException;
import com.martinvana.tic_tac_toe.extension.MockitoExtension;
import com.martinvana.tic_tac_toe.game.board.ComputerReadableBoard;
import com.martinvana.tic_tac_toe.game.board.HumanReadableBoard;
import com.martinvana.tic_tac_toe.game.player.HumanPlayer;
import com.martinvana.tic_tac_toe.game.player.Player;
import com.martinvana.tic_tac_toe.game.player.RowEatingComputerPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.io.InputStream;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PlayerListImplTest {
    private Player player1;

    private Player player2;

    private Player player3;

    private Player player4;

    @BeforeEach
    void setUp(@Mock HumanReadableBoard humanReadableBoard, @Mock ComputerReadableBoard computerReadableBoard, @Mock InputStream inputStream) {
        this.player1 = new HumanPlayer(new PlayCharacterImpl("A"), humanReadableBoard, inputStream);
        this.player2 = new HumanPlayer(new PlayCharacterImpl("B"), humanReadableBoard, inputStream);
        this.player3 = new RowEatingComputerPlayer(new PlayCharacterImpl("C"), computerReadableBoard);
        this.player4 = new HumanPlayer(new PlayCharacterImpl("D"), humanReadableBoard, inputStream);
    }

    @Test
    @DisplayName("Construct invalid object with just two players.")
    void testInvalidConstructor1() {
        assertThrows(DomainException.class,
                () -> new PlayerListImpl(player1, player2));
    }

    @Test
    @DisplayName("Construct invalid object with four players.")
    void testInvalidConstructor2() {
        assertThrows(DomainException.class,
                () -> new PlayerListImpl(player1, player2, player3, player4));
    }

    @Test
    @DisplayName("Test iterator.")
    void testIterator() {
        // Note: players are in shuffled randomly on initialization

        HashSet<Player> players = new HashSet<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        PlayerList playerList = new PlayerListImpl(player1, player2, player3);

        HashSet<Player> playersInList = new HashSet<>();
        for (Player p : playerList) {
            playersInList.add(p);
        }

        assertEquals(3, playersInList.size());
        assertEquals(players, playersInList);
    }

}