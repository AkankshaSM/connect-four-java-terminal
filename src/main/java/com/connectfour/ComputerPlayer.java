package com.connectfour;

import java.util.Random;

/**
 * Computer player that chooses random legal moves.
 */
public class ComputerPlayer extends Player {
    private final Random random;

    public ComputerPlayer(char disc, String name) {
        super(disc, name);
        this.random = new Random();
    }

    @Override
    public int chooseColumn(Board board) {
        int[] available = board.getAvailableColumns();
        if (available.length == 0) {
            return -1; // No moves available
        }
        int index = random.nextInt(available.length);
        int col = available[index];
        System.out.println(name + " (" + disc + ") chooses column " + (col + 1));
        return col;
    }
}
