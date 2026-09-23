package com.connectfour;

import java.util.Scanner;

/**
 * Main entry point for the Connect Four terminal game.
 * Human (Red) vs Computer (Yellow) with random moves.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========================================");
        System.out.println("     CONNECT FOUR - Java Terminal       ");
        System.out.println("========================================");
        System.out.println("Human (R) vs Computer (Y)");
        System.out.println("Enter column 1-7 to drop your disc.");
        System.out.println("Type 'q' or 'quit' to exit.");
        System.out.println("========================================\n");

        HumanPlayer human = new HumanPlayer(Board.PLAYER1, "You", scanner);
        ComputerPlayer computer = new ComputerPlayer(Board.PLAYER2, "Computer");
        GameEngine game = new GameEngine(human, computer);

        boolean playAgain = true;
        while (playAgain) {
            playGame(game, scanner);
            
            if (game.getState() == GameEngine.GameState.QUIT) {
                break;
            }
            
            System.out.print("\nPlay again? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            playAgain = input.equals("y") || input.equals("yes");
            
            if (playAgain) {
                game.reset();
                System.out.println("\n--- New Game ---\n");
            }
        }

        System.out.println("\nThanks for playing! Goodbye!");
        scanner.close();
    }

    private static void playGame(GameEngine game, Scanner scanner) {
        while (game.getState() == GameEngine.GameState.IN_PROGRESS) {
            System.out.println(game.getBoard());
            System.out.println();
            
            boolean turnPlayed = game.playTurn();
            
            if (!turnPlayed) {
                // Game ended (quit or error)
                break;
            }
        }

        // Show final board
        System.out.println(game.getBoard());
        System.out.println();

        // Announce result
        switch (game.getState()) {
            case PLAYER1_WON ->
                System.out.println("*** CONGRATULATIONS! You won! ***");
            case PLAYER2_WON ->
                System.out.println("*** Computer wins! Better luck next time. ***");
            case DRAW ->
                System.out.println("*** It's a draw! ***");
            case QUIT ->
                System.out.println("Game quit.");
            default -> {}
        }
    }
}
