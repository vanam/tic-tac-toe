package com.martinvana.tic_tac_toe.game.player;

import com.martinvana.tic_tac_toe.config.PlayCharacter;
import com.martinvana.tic_tac_toe.exception.UnableToMakeMoveException;
import com.martinvana.tic_tac_toe.game.Move;

/**
 * The abstract class for a player.
 */
public abstract class APlayer implements Player {

    /**
     * The play character.
     */
    protected PlayCharacter character;

    /**
     * @param character The play character.
     */
    public APlayer(final PlayCharacter character) {
        this.character = character;
    }

    @Override
    public char getCharacter() {
        return character.getCharacter();
    }

    @Override
    abstract public Move makeMove() throws UnableToMakeMoveException;
}
