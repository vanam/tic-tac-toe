package com.martinvana.tic_tac_toe.config;

import com.martinvana.tic_tac_toe.exception.DomainException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * The class which carries configuration of the application.
 */
public final class Config {

    /**
     * The configuration is red from property file.
     */
    private final Properties prop = new Properties();

    /**
     * The board dimension.
     */
    private BoardDimension boardDimension;

    /**
     * Play character for human player 1.
     */
    private PlayCharacter humanPlayer1Character;

    /**
     * Play character for human player 2.
     */
    private PlayCharacter humanPlayer2Character;

    /**
     * Play character for computer player.
     */
    private PlayCharacter computerCharacter;

    /**
     * @param configFileStream Configuration file stream.
     * @throws DomainException Thrown if an error occurs during configuration loading.
     */
    public Config(final InputStream configFileStream) throws DomainException {
        // Load a config file
        try {
            prop.load(configFileStream);
        } catch (NullPointerException | IllegalArgumentException | IOException e) {
            throw new DomainException("An error occurred during config file reading.");
        }

        createConfigurationObjects();
    }

    /**
     * Create configuration objects from properties.
     */
    private void createConfigurationObjects() {
        boardDimension = createBoardDimension();
        humanPlayer1Character = createPlayCharacter("player_1");
        humanPlayer2Character = createPlayCharacter("player_2");
        computerCharacter = createPlayCharacter("computer");

        // Check play character uniqueness
        Set<Character> uniquePlayerCharacters = new HashSet<>();
        uniquePlayerCharacters.add(humanPlayer1Character.getCharacter());
        uniquePlayerCharacters.add(humanPlayer2Character.getCharacter());
        uniquePlayerCharacters.add(computerCharacter.getCharacter());

        if (uniquePlayerCharacters.size() != 3) {
            throw new DomainException("Player characters are not unique.");
        }
    }

    /**
     * @param propertyKey The property key which identifies a player.
     * @return Return <code>PlayCharacter</code> of a player.
     */
    private PlayCharacter createPlayCharacter(String propertyKey) {
        return new PlayCharacterImpl(prop.getProperty(propertyKey));
    }

    /**
     * @return Return board dimension.
     * @throws DomainException Thrown if a dimension integer could not be parsed.
     */
    private BoardDimension createBoardDimension() throws DomainException {
        int dimension;

        try {
            dimension = Integer.parseInt(prop.getProperty("dimension"));
        } catch (NumberFormatException e) {
            throw new DomainException("Could not parse dimension property from config file.");
        }

        return new BoardDimensionImpl(dimension);
    }

    /**
     * @return Return board dimension.
     */
    public BoardDimension getBoardDimension() {
        return boardDimension;
    }

    /**
     * @return Return play character of human player 1.
     */
    public PlayCharacter getHumanPlayer1Character() {
        return humanPlayer1Character;
    }

    /**
     * @return Return play character of human player 2.
     */
    public PlayCharacter getHumanPlayer2Character() {
        return humanPlayer2Character;
    }

    /**
     * @return Return play character of computer player.
     */
    public PlayCharacter getComputerCharacter() {
        return computerCharacter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Config config = (Config) o;

        if (prop != null ? !prop.equals(config.prop) : config.prop != null) return false;
        if (boardDimension != null ? !boardDimension.equals(config.boardDimension) : config.boardDimension != null)
            return false;
        if (humanPlayer1Character != null ? !humanPlayer1Character.equals(config.humanPlayer1Character) : config.humanPlayer1Character != null)
            return false;
        if (humanPlayer2Character != null ? !humanPlayer2Character.equals(config.humanPlayer2Character) : config.humanPlayer2Character != null)
            return false;
        return computerCharacter != null ? computerCharacter.equals(config.computerCharacter) : config.computerCharacter == null;
    }

    @Override
    public int hashCode() {
        int result = prop != null ? prop.hashCode() : 0;
        result = 31 * result + (boardDimension != null ? boardDimension.hashCode() : 0);
        result = 31 * result + (humanPlayer1Character != null ? humanPlayer1Character.hashCode() : 0);
        result = 31 * result + (humanPlayer2Character != null ? humanPlayer2Character.hashCode() : 0);
        result = 31 * result + (computerCharacter != null ? computerCharacter.hashCode() : 0);
        return result;
    }
}
