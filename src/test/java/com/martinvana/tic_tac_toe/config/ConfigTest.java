package com.martinvana.tic_tac_toe.config;

import com.martinvana.tic_tac_toe.exception.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    private static final String CONFIG_STRING = "dimension=6\n" +
            "player_1=O\n" +
            "player_2=X\n" +
            "computer=C\n";

    private Config cfg;

    @BeforeEach
    void setUp() throws IOException, DomainException {
        Path path = Files.createTempFile("config", ".properties");
        File file = path.toFile();
        Files.write(path, CONFIG_STRING.getBytes(StandardCharsets.UTF_8));

        cfg = new Config(new FileInputStream(file));
    }

    @Test
    @DisplayName("Construct object with null argument.")
    void testConstructorWithEmptyArgument() {
        assertThrows(DomainException.class,
                () -> new Config(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "dimension=6\n",
            "player_1=O\n",
            "player_2=O\n",
            "computer=C\n",
            "dimension=6\nplayer_1=O\n",
            "dimension=6\nplayer_1=O\nplayer_2=X\n",
            "player_1=O\nplayer_2=X\ncomputer=C\n",
            "dimension=6\nplayer_2=X\ncomputer=C\n",
            "dimension=6\nplayer_1=O\ncomputer=C\n",
            "dimension=2\nplayer_1=O\nplayer_2=X\ncomputer=C\n",
            "dimension=6\nplayer_1=OX\nplayer_2=X\ncomputer=C\n",
            "dimension=6\nplayer_1=O\nplayer_2=OX\ncomputer=C\n",
            "dimension=6\nplayer_1=O\nplayer_2=X\ncomputer=CX\n",
            "dimension=6\nplayer_1=O\nplayer_2=X\ncomputer=O\n",
            "dimension=6\nplayer_1=O\nplayer_2=O\ncomputer=O\n",
    })
    @DisplayName("Construct object with invalid config file.")
    void testConstructorWithInvalidConfigFile(String cfg) throws IOException {
        Path path = Files.createTempFile("config", ".properties");
        File file = path.toFile();
        Files.write(path, cfg.getBytes(StandardCharsets.UTF_8));

        assertThrows(DomainException.class,
                () -> new Config(new FileInputStream(file)));
    }

    @Test
    @DisplayName("Test board dimension getter.")
    void getBoardDimension() throws DomainException {
        assertEquals(new BoardDimensionImpl(6), cfg.getBoardDimension());
    }

    @Test
    @DisplayName("Test human player 1 character getter.")
    void getHumanPlayer1Character() {
        assertEquals(new PlayCharacterImpl("O"), cfg.getHumanPlayer1Character());
    }

    @Test
    @DisplayName("Test human player 2 character getter.")
    void getHumanPlayer2Character() {
        assertEquals(new PlayCharacterImpl("X"), cfg.getHumanPlayer2Character());
    }

    @Test
    @DisplayName("Test computer player character getter.")
    void getComputerCharacter() {
        assertEquals(new PlayCharacterImpl("C"), cfg.getComputerCharacter());
    }

}