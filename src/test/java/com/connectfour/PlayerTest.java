package com.connectfour;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void testComputerPlayerChoosesValidColumn() {
        Board board = new Board();
        ComputerPlayer computer = new ComputerPlayer(Board.PLAYER2, "Computer");
        
        int col = computer.chooseColumn(board);
        assertTrue(col >= 0 && col < Board.COLS);
        assertFalse(board.isColumnFull(col));
    }

    @Test
    void testComputerPlayerChoosesFromAvailableOnly() {
        Board board = new Board();
        // Fill columns 0-5
        for (int c = 0; c < 6; c++) {
            for (int r = 0; r < Board.ROWS; r++) {
                board.dropDisc(c, Board.PLAYER1);
            }
        }
        // Only column 6 should be available
        ComputerPlayer computer = new ComputerPlayer(Board.PLAYER2, "Computer");
        int col = computer.chooseColumn(board);
        assertEquals(6, col);
    }

    @Test
    void testComputerPlayerNoMovesReturnsMinusOne() {
        Board board = new Board();
        // Fill entire board
        for (int c = 0; c < Board.COLS; c++) {
            for (int r = 0; r < Board.ROWS; r++) {
                board.dropDisc(c, Board.PLAYER1);
            }
        }
        ComputerPlayer computer = new ComputerPlayer(Board.PLAYER2, "Computer");
        int col = computer.chooseColumn(board);
        assertEquals(-1, col);
    }
}
