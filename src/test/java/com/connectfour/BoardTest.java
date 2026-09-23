package com.connectfour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testInitialBoardEmpty() {
        for (int r = 0; r < Board.ROWS; r++) {
            for (int c = 0; c < Board.COLS; c++) {
                assertEquals(Board.EMPTY, board.getDisc(r, c));
            }
        }
    }

    @Test
    void testDropDiscFirstRow() {
        int row = board.dropDisc(0, Board.PLAYER1);
        assertEquals(Board.ROWS - 1, row); // Bottom row
        assertEquals(Board.PLAYER1, board.getDisc(row, 0));
    }

    @Test
    void testDropDiscStacks() {
        board.dropDisc(0, Board.PLAYER1);
        int row = board.dropDisc(0, Board.PLAYER2);
        assertEquals(Board.ROWS - 2, row); // Second from bottom
        assertEquals(Board.PLAYER2, board.getDisc(row, 0));
        assertEquals(Board.PLAYER1, board.getDisc(Board.ROWS - 1, 0));
    }

    @Test
    void testColumnFull() {
        // Fill column 0
        for (int i = 0; i < Board.ROWS; i++) {
            assertFalse(board.isColumnFull(0));
            board.dropDisc(0, Board.PLAYER1);
        }
        assertTrue(board.isColumnFull(0));
        assertEquals(-1, board.dropDisc(0, Board.PLAYER1));
    }

    @Test
    void testInvalidColumn() {
        assertEquals(-1, board.dropDisc(-1, Board.PLAYER1));
        assertEquals(-1, board.dropDisc(Board.COLS, Board.PLAYER1));
        assertTrue(board.isColumnFull(-1));
        assertTrue(board.isColumnFull(Board.COLS));
    }

    @Test
    void testGetAvailableColumns() {
        int[] available = board.getAvailableColumns();
        assertEquals(Board.COLS, available.length);
        
        board.dropDisc(0, Board.PLAYER1);
        available = board.getAvailableColumns();
        assertEquals(Board.COLS - 1, available.length);
        assertFalse(contains(available, 0));
    }

    @Test
    void testHorizontalWin() {
        // Place 4 in a row horizontally at bottom row
        for (int c = 0; c < 4; c++) {
            board.dropDisc(c, Board.PLAYER1);
        }
        // Last move was at column 3, row 5
        assertTrue(board.checkWin(Board.ROWS - 1, 3, Board.PLAYER1));
    }

    @Test
    void testHorizontalWinNotAtEdge() {
        // Place discs to set up a horizontal win in the middle
        // Fill columns 1-4 at bottom row
        for (int c = 1; c <= 4; c++) {
            board.dropDisc(c, Board.PLAYER1);
        }
        assertTrue(board.checkWin(Board.ROWS - 1, 3, Board.PLAYER1));
    }

    @Test
    void testVerticalWin() {
        // Drop 4 in same column
        for (int i = 0; i < 4; i++) {
            board.dropDisc(0, Board.PLAYER1);
        }
        // Last move at row 2 (0-indexed from top, so row 2 = 3rd from bottom)
        assertTrue(board.checkWin(2, 0, Board.PLAYER1));
    }

    @Test
    void testDiagonalWinBottomLeftToTopRight() {
        // Create diagonal from (5,0) to (2,3)
        // Column 0: 1 disc at bottom
        board.dropDisc(0, Board.PLAYER1);
        // Column 1: 2 discs
        board.dropDisc(1, Board.PLAYER2);
        board.dropDisc(1, Board.PLAYER1);
        // Column 2: 3 discs
        board.dropDisc(2, Board.PLAYER2);
        board.dropDisc(2, Board.PLAYER2);
        board.dropDisc(2, Board.PLAYER1);
        // Column 3: 4 discs, last one completes diagonal
        board.dropDisc(3, Board.PLAYER2);
        board.dropDisc(3, Board.PLAYER2);
        board.dropDisc(3, Board.PLAYER2);
        int row = board.dropDisc(3, Board.PLAYER1);
        
        assertTrue(board.checkWin(row, 3, Board.PLAYER1));
    }

    @Test
    void testDiagonalWinBottomRightToTopLeft() {
        // Create diagonal from (5,6) to (2,3)
        // Column 6: 1 disc at bottom
        board.dropDisc(6, Board.PLAYER1);
        // Column 5: 2 discs
        board.dropDisc(5, Board.PLAYER2);
        board.dropDisc(5, Board.PLAYER1);
        // Column 4: 3 discs
        board.dropDisc(4, Board.PLAYER2);
        board.dropDisc(4, Board.PLAYER2);
        board.dropDisc(4, Board.PLAYER1);
        // Column 3: 4 discs, last one completes diagonal
        board.dropDisc(3, Board.PLAYER2);
        board.dropDisc(3, Board.PLAYER2);
        board.dropDisc(3, Board.PLAYER2);
        int row = board.dropDisc(3, Board.PLAYER1);
        
        assertTrue(board.checkWin(row, 3, Board.PLAYER1));
    }

    @Test
    void testNoWinYet() {
        board.dropDisc(0, Board.PLAYER1);
        board.dropDisc(1, Board.PLAYER1);
        board.dropDisc(2, Board.PLAYER1);
        // Only 3 in a row, not 4
        assertFalse(board.checkWin(Board.ROWS - 1, 2, Board.PLAYER1));
    }

    @Test
    void testIsFull() {
        assertFalse(board.isFull());
        // Fill entire board
        for (int c = 0; c < Board.COLS; c++) {
            for (int r = 0; r < Board.ROWS; r++) {
                board.dropDisc(c, (r % 2 == 0) ? Board.PLAYER1 : Board.PLAYER2);
            }
        }
        assertTrue(board.isFull());
    }

    @Test
    void testClear() {
        board.dropDisc(0, Board.PLAYER1);
        board.dropDisc(1, Board.PLAYER2);
        board.clear();
        assertFalse(board.isColumnFull(0));
        assertFalse(board.isColumnFull(1));
        assertEquals(Board.EMPTY, board.getDisc(Board.ROWS - 1, 0));
    }

    private boolean contains(int[] arr, int value) {
        for (int v : arr) {
            if (v == value) return true;
        }
        return false;
    }
}
