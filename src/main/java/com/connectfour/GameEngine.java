package com.connectfour;

/**
 * Core game logic - handles game flow, win/draw detection, turn management.
 * Separated from I/O for testability.
 */
public class GameEngine {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private GameState state;
    private int lastMoveRow;
    private int lastMoveCol;

    public enum GameState {
        IN_PROGRESS,
        PLAYER1_WON,
        PLAYER2_WON,
        DRAW,
        QUIT
    }

    public GameEngine(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.state = GameState.IN_PROGRESS;
    }

    /**
     * Execute one turn for the current player.
     * @return true if turn was played, false if game ended (quit)
     */
    public boolean playTurn() {
        if (state != GameState.IN_PROGRESS) {
            return false;
        }

        int col = currentPlayer.chooseColumn(board);
        
        // Check for quit signal
        if (col == -1) {
            state = GameState.QUIT;
            return false;
        }

        int row = board.dropDisc(col, currentPlayer.getDisc());
        if (row == -1) {
            // Should not happen if chooseColumn validates, but handle gracefully
            return false;
        }

        lastMoveRow = row;
        lastMoveCol = col;

        // Check for win
        if (board.checkWin(row, col, currentPlayer.getDisc())) {
            state = (currentPlayer == player1) ? GameState.PLAYER1_WON : GameState.PLAYER2_WON;
        } else if (board.isFull()) {
            state = GameState.DRAW;
        } else {
            // Switch player
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        return true;
    }

    public Board getBoard() {
        return board;
    }

    public GameState getState() {
        return state;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getLastMoveRow() {
        return lastMoveRow;
    }

    public int getLastMoveCol() {
        return lastMoveCol;
    }

    /** Reset game for a new round */
    public void reset() {
        board.clear();
        currentPlayer = player1;
        state = GameState.IN_PROGRESS;
        lastMoveRow = -1;
        lastMoveCol = -1;
    }
}
