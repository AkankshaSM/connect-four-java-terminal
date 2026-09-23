package com.connectfour;

import java.util.Scanner;

/**
 * Human player that reads input from console.
 */
public class HumanPlayer extends Player {
    private final Scanner scanner;

    public HumanPlayer(char disc, String name, Scanner scanner) {
        super(disc, name);
        this.scanner = scanner;
    }

    @Override
    public int chooseColumn(Board board) {
        while (true) {
            System.out.print(name + " (" + disc + "), enter column 1-7 (or 'q' to quit): ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
                return -1; // Signal to quit
            }
            
            try {
                int col = Integer.parseInt(input) - 1; // Convert 1-7 to 0-6
                if (col < 0 || col >= Board.COLS) {
                    System.out.println("Invalid column. Please enter a number between 1 and 7.");
                    continue;
                }
                if (board.isColumnFull(col)) {
                    System.out.println("Column " + (col + 1) + " is full. Choose another column.");
                    continue;
                }
                return col;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7, or 'q' to quit.");
            }
        }
    }
}
