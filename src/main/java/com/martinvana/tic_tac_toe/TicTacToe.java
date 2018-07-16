package com.martinvana.tic_tac_toe;

import com.martinvana.tic_tac_toe.config.BoardDimension;
import com.martinvana.tic_tac_toe.config.Config;
import com.martinvana.tic_tac_toe.exception.DomainException;
import com.martinvana.tic_tac_toe.exception.ParseException;
import com.martinvana.tic_tac_toe.game.*;
import com.martinvana.tic_tac_toe.game.board.Board;
import com.martinvana.tic_tac_toe.game.board.BoardImpl;
import com.martinvana.tic_tac_toe.game.player.HumanPlayer;
import com.martinvana.tic_tac_toe.game.player.Player;
import com.martinvana.tic_tac_toe.game.player.RowEatingComputerPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * The TicTacToe game engine.
 */
public final class TicTacToe {

    /**
     * The game board.
     */
    private final Board board;

    /**
     * The list of players.
     */
    private PlayerList playerList;

    /**
     * The winning row configuration.
     */
    private Row row;

    /**
     * The direction where to look for the winning row.
     */
    private static final int[][] DIRECTIONS = {
            {0, -1},
            {-1, -1},
            {-1, 0},
            {-1, 1}
    };

    /**
     * @param board      The game board.
     * @param playerList The list of players.
     * @param row        The winning row configuration.
     */
    private TicTacToe(Board board, PlayerList playerList, Row row) {
        this.board = board;
        this.playerList = playerList;
        this.row = row;
    }

    /**
     * Run the game.
     */
    private void run() {
        boolean gameEnded = false;

        // Play game while not finished
        while (!gameEnded) {
            // Players take turns
            for (Player p : playerList) {
                // Print board
                System.out.println(String.format("\nPlayer %c on the move", p.getCharacter()));
                System.out.print(board);

                // Until a player makes a valid move
                boolean validMove = true;

                Move move;
                do {
                    if (!validMove) {
                        System.out.println(" * Invalid move");
                    }

                    move = p.makeMove();
                    System.out.println(String.format(" * Move [%d, %d]", move.getX(), move.getY()));
                    validMove = board.applyMove(move);
                } while (!validMove);

                // Has the player won?
                if (gameEnded = hasPlayerWon(move)) {
                    // Print board and results
                    System.out.print(board);
                    System.out.println(String.format("\nPlayer %c has won", p.getCharacter()));

                    break;
                } else if (board.isFull()) {
                    // Print board and results
                    System.out.print(board);
                    System.out.println("\nDraw");

                    gameEnded = true;
                    break;
                }
            }
        }
    }

    /**
     * @param move The last move made by player.
     * @return Return <code>true</code> if the last move was winning, <code>false</code> otherwise.
     */
    private boolean hasPlayerWon(final Move move) {
        char playCharacter = move.getCharacter();
        int x, y, cnt;

        // Try to find the row of desired length in four directions
        for (int[] direction : DIRECTIONS) {
            x = move.getX();
            y = move.getY();

            // First move along the direction while we found current player character.
            try {
                while (board.at(x + direction[0], y + direction[1]) == playCharacter) {
                    x += direction[0];
                    y += direction[1];
                }
            } catch (IndexOutOfBoundsException e) {
                // Intentionally left blank
            }

            // Now we move backwards the direction and count the length of the row
            cnt = 1;

            try {
                while (board.at(x - direction[0], y - direction[1]) == playCharacter) {
                    x -= direction[0];
                    y -= direction[1];
                    cnt++;
                }
            } catch (IndexOutOfBoundsException e) {
                // Intentionally left blank
            }

            // Player has formed row of desired length (or longer)
            if (cnt >= row.getLength()) {
                return true;
            }
        }

        return false;
    }

    /**
     * The application entry point.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        // Parse input arguments
        Config cfg = null;
        try {
            cfg = processArgs(args);
        } catch (ParseException | DomainException e) {
            System.out.println(e.getMessage());
            printHelp();
            System.exit(1);
        }

        // Run game
        BoardDimension boardDimension = cfg.getBoardDimension();
        BoardImpl board = new BoardImpl(boardDimension);
        TicTacToe game = new TicTacToe(
                board,
                new PlayerListImpl(
                        new HumanPlayer(cfg.getHumanPlayer1Character(), board, System.in),
                        new HumanPlayer(cfg.getHumanPlayer2Character(), board, System.in),
                        new RowEatingComputerPlayer(cfg.getComputerCharacter(), board)
                ),
                new RowImpl(3, boardDimension)
        );
        game.run();
    }

    /**
     * Process command line arguments.
     *
     * @param args The command line arguments.
     * @return Return configuration of the game.
     * @throws ParseException  Thrown if wrong command line arguments were given.
     * @throws DomainException Thrown if the configuration is invalid.
     */
    private static Config processArgs(String[] args) throws ParseException, DomainException {
        // Check the number of command line arguments provided - only one argument should be provided
        if (args.length == 0) {
            throw new ParseException("No command line argument provided.");
        } else if (args.length > 1) {
            throw new ParseException("Too many command line arguments provided.");
        }

        // Try to open first and only expected argument - a config file
        InputStream configFileStream;

        try {
            configFileStream = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            throw new ParseException("Configuration file not found.");
        }

        return new Config(configFileStream);
    }

    /**
     * Print help describing how the app should be used.
     */
    private static void printHelp() {
        System.out.println("usage: java -jar tic-tac-toe-1.0-SNAPSHOT.jar <file>");
        System.out.println("");
        System.out.println("DESCRIPTION");
        System.out.println(" <file>   path to the configuration file");
    }
}
