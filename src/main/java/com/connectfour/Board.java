package com.connectfour;

/**
 * Represents the Connect Four game board.
 * 6 rows (0-5, bottom to top), 7 columns (0-6).
 */
public class Board {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    public static final char EMPTY = ' ';
    public static final char PLAYER1 = 'R';  // Red - Human
    public static final char PLAYER2 = 'Y';  // Yellow - Computer

    private final char[][] grid;

    public Board() {
        grid = new char[ROWS][COLS];
        clear();
    }

    /** Reset the board to empty state */
    public void clear() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                grid[r][c] = EMPTY;
            }
        }
    }

    /**
     * Drop a disc in the given column (0-indexed).
     * Gravity: disc falls to lowest empty row.
     * @param col column index (0-6)
     * @param player player disc character
     * @return row index where disc landed, or -1 if column is full
     */
    public int dropDisc(int col, char player) {
        if (col < 0 || col >= COLS) {
            return -1;
        }
        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][col] == EMPTY) {
                grid[row][col] = player;
                return row;
            }
        }
        return -1; // column full
    }

    /** Check if a column has space */
    public boolean isColumnFull(int col) {
        if (col < 0 || col >= COLS) {
            return true;
        }
        return grid[0][col] != EMPTY;
    }

    /** Get all columns that are not full */
    public int[] getAvailableColumns() {
        int count = 0;
        for (int c = 0; c < COLS; c++) {
            if (!isColumnFull(c)) count++;
        }
        int[] available = new int[count];
        int idx = 0;
        for (int c = 0; c < COLS; c++) {
            if (!isColumnFull(c)) {
                available[idx++] = c;
            }
        }
        return available;
    }

    /** Get the disc at a position */
    public char getDisc(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            return EMPTY;
        }
        return grid[row][col];
    }

    /** Check if board is completely full (draw) */
    public boolean isFull() {
        for (int c = 0; c < COLS; c++) {
            if (!isColumnFull(c)) return false;
        }
        return true;
    }

    /** Check for a win starting from the last placed disc */
    public boolean checkWin(int row, int col, char player) {
        // Check horizontal
        if (countInDirection(row, col, 0, 1, player) + countInDirection(row, col, 0, -1, player) >= 3) {
            return true;
        }
        // Check vertical
        if (countInDirection(row, col, 1, 0, player) + countInDirection(row, col, -1, 0, player) >= 3) {
            return true;
        }
        // Check diagonal (bottom-left to top-right)
        if (countInDirection(row, col, 1, 1, player) + countInDirection(row, col, -1, -1, player) >= 3) {
            return true;
        }
        // Check diagonal (bottom-right to top-left)
        if (countInDirection(row, col, 1, -1, player) + countInDirection(row, col, -1, 1, player) >= 3) {
            return true;
        }
        return false;
    }

    /** Count consecutive discs in a direction (excluding the starting position) */
    private int countInDirection(int row, int col, int dRow, int dCol, char player) {
        int count = 0;
        int r = row + dRow;
        int c = col + dCol;
        while (r >= 0 && r < ROWS && c >= 0 && c < COLS && grid[r][c] == player) {
            count++;
            r += dRow;
            c += dCol;
        }
        return count;
    }

    /** Get a copy of the grid for display */
    public char[][] getGrid() {
        char[][] copy = new char[ROWS][COLS];
        for (int r = 0; r < ROWS; r++) {
            System.arraycopy(grid[r], 0, copy[r], 0, COLS);
        }
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Column headers
        sb.append(" ");
        for (int c = 0; c < COLS; c++) {
            sb.append(" ").append(c + 1);
        }
        sb.append("\n");
        // Grid rows (top to bottom for display)
        for (int r = 0; r < ROWS; r++) {
            sb.append("|");
            for (int c = 0; c < COLS; c++) {
                sb.append(grid[r][c]).append("|");
            }
            sb.append("\n");
        }
        // Bottom border
        sb.append("+---+---+---+---+---+---+---+");
        return sb.toString();
    }
}
