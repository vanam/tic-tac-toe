package com.martinvana.tic_tac_toe.config;

import com.martinvana.tic_tac_toe.exception.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PlayCharacterImplTest {
    @Test
    @DisplayName("Construct object with null argument.")
    void testConstructorWithNullArgument() {
        assertThrows(DomainException.class,
                () -> new PlayCharacterImpl(null));
    }

    @Test
    @DisplayName("Construct object with empty string as an argument.")
    void testConstructorWithEmptyArgument() {
        assertThrows(DomainException.class,
                () -> new PlayCharacterImpl(""));
    }

    @Test
    @DisplayName("Construct object with too long string as an argument.")
    void testConstructorWithTooLongStringArgument() {
        assertThrows(DomainException.class,
                () -> new PlayCharacterImpl("ab"));
    }

    @Test
    @DisplayName("Construct valid object.")
    void testValidConstructor() throws DomainException {
        new PlayCharacterImpl("a");
    }

    @Test
    @DisplayName("Test character getter.")
    void testGetCharacter() throws DomainException {
        String character = "X";

        PlayCharacter pc = new PlayCharacterImpl(character);

        assertEquals(character.charAt(0), pc.getCharacter());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "a",
            "o",
            "x",
            "z",
            "A",
            "O",
            "X",
            "Z",
    })
    @DisplayName("Construct object with valid play character.")
    void testValidCharacters(String character) {
        new PlayCharacterImpl(character);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "@",
            ".",
            "[",
            "`",
            ";",
            "{",
    })
    @DisplayName("Construct object with invalid play character.")
    void testInvalidCharacters(String character) {
        assertThrows(DomainException.class,
                () -> new PlayCharacterImpl(character));
    }
}
