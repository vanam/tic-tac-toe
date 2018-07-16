package com.martinvana.tic_tac_toe.game;

import com.martinvana.tic_tac_toe.exception.DomainException;
import com.martinvana.tic_tac_toe.game.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/**
 * The class is immutable and represents a shuffled list of players.
 */
public final class PlayerListImpl implements PlayerList {

    /**
     * The minimum number of players in the list.
     */
    private static final int MIN_PLAYERS = 3;

    /**
     * The maximum number of players in the list.
     */
    private static final int MAX_PLAYERS = 3;

    /**
     * The list of players.
     */
    private final ArrayList<Player> players = new ArrayList<>();

    /**
     * @param players The players to put in the list.
     * @throws DomainException Thrown if constraints of the number of players are not met.
     */
    public PlayerListImpl(final Player... players) throws DomainException {
        int noPlayers = players.length;

        if (noPlayers < MIN_PLAYERS || noPlayers > MAX_PLAYERS) {
            throw new DomainException(String.format("The number of players must in interval <%d, %d>, %d given.", MIN_PLAYERS, MAX_PLAYERS, noPlayers));
        }

        this.players.addAll(Arrays.asList(players));
        shuffle();
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }

    /**
     * Shuffle players randomly to assign their playing order.
     */
    private void shuffle() {
        Collections.shuffle(players);
    }
}
