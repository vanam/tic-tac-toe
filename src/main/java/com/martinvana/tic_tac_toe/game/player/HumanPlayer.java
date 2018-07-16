package com.martinvana.tic_tac_toe.game.player;

import com.martinvana.tic_tac_toe.config.PlayCharacter;
import com.martinvana.tic_tac_toe.exception.DomainException;
import com.martinvana.tic_tac_toe.exception.UnableToMakeMoveException;
import com.martinvana.tic_tac_toe.game.Move;
import com.martinvana.tic_tac_toe.game.MoveImpl;
import com.martinvana.tic_tac_toe.game.board.HumanReadableBoard;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class of a human player.
 */
public final class HumanPlayer extends APlayer {

    /**
     * The board as seen by human player.
     */
    private final HumanReadableBoard board;

    /**
     * The user input scanner.
     */
    private final Scanner scanner;

    /**
     * @param character The play character.
     * @param board     The board as seen by human player.
     * @param input     The user input
     */
    public HumanPlayer(final PlayCharacter character, final HumanReadableBoard board, final InputStream input) {
        super(character);

        this.board = board;
        this.scanner = new Scanner(input);
    }

    @Override
    public Move makeMove() throws UnableToMakeMoveException {
        // Desired input format
        Pattern pattern = Pattern.compile("^(\\d+),(\\d+)$");

        String line;

        try {
            while ((line = scanner.nextLine()) != null) {
                // Match line in format
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));

                    try {
                        return new MoveImpl(x, y, board.getBoardDimension(), character);
                    } catch (DomainException e) {
                        System.out.println(" * Move out of board's bounds");
                        // Intentionally left blank
                    }
                } else {
                    System.out.println(" * Invalid input");
                }
            }
        } catch (NoSuchElementException e) {
            // Intentionally left blank
        }

        throw new UnableToMakeMoveException("Could not read a valid move because stdin was closed.");
    }
}
