package com.martinvana.tic_tac_toe.config;

import com.martinvana.tic_tac_toe.exception.DomainException;

/**
 * The class is immutable and carries valid play character.
 */
public final class PlayCharacterImpl implements PlayCharacter {

    /**
     * The pattern which specify a valid play character.
     */
    private static final String PATTERN = "[a-zA-Z]";

    /**
     * The play character
     */
    private final char character;

    /**
     * @param playCharacter The play character string.
     * @throws DomainException Thrown if invalid play character string is provided.
     */
    public PlayCharacterImpl(final String playCharacter) throws DomainException {
        if (playCharacter == null || playCharacter.length() != 1) {
            throw new DomainException(String.format("Only one character expected, %s given.", playCharacter));
        }

        if (!playCharacter.matches(PATTERN)) {
            throw new DomainException(String.format("Only %s character is allowed, %s given.", PATTERN, playCharacter));
        }

        this.character = playCharacter.charAt(0);
    }

    @Override
    public char getCharacter() {
        return character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayCharacterImpl that = (PlayCharacterImpl) o;

        return character == that.character;
    }

    @Override
    public int hashCode() {
        return (int) character;
    }
}
