package com.connectfour;

/**
 * Represents a player in the game.
 */
public abstract class Player {
    protected final char disc;
    protected final String name;

    public Player(char disc, String name) {
        this.disc = disc;
        this.name = name;
    }

    public char getDisc() {
        return disc;
    }

    public String getName() {
        return name;
    }

    /**
     * Choose a column to play (0-6).
     * @param board current board state
     * @return column index (0-6)
     */
    public abstract int chooseColumn(Board board);
}
