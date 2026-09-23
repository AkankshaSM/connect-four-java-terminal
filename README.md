# Connect Four - Java Terminal Game

A command-line Connect Four game built with Java 21 and Maven. Play as Red (human) against Yellow (computer with random legal moves).

## Features
- 6-row × 7-column board
- Human vs Computer (random legal moves)
- Column inputs 1–7
- Gravity mechanics (discs fall to lowest empty row)
- Invalid input handling
- Full column rejection
- Win detection: horizontal, vertical, and both diagonals
- Draw detection
- Quit anytime with 'q' or 'quit'
- Play again option
- Clean separation of game logic from terminal I/O
- JUnit 5 tests
- Executable JAR

## Requirements
- Java 21 or later
- Maven 3.6+ (or use the included Maven wrapper if available)

## Build

```bash
mvn clean compile
```

## Run Tests

```bash
mvn test
```

## Package (Creates Executable JAR)

```bash
mvn package
```

This creates `target/connect-four-java-terminal-1.0.0.jar`

## Play

### Run with Maven (direct)
```bash
mvn exec:java -Dexec.mainClass=com.connectfour.Main
```

### Run the Executable JAR
```bash
java -jar target/connect-four-java-terminal-1.0.0.jar
```

## How to Play

1. Run the game using one of the methods above
2. You are **Red (R)**, Computer is **Yellow (Y)**
3. Enter a column number **1–7** to drop your disc
4. Discs fall to the lowest empty row in that column (gravity)
5. First to connect **4** discs horizontally, vertically, or diagonally wins
6. If the board fills completely with no winner, it's a draw
7. Type **'q'** or **'quit'** anytime to exit
8. After a game ends, type **'y'** to play again

## Example Gameplay
```
========================================
     CONNECT FOUR - Java Terminal       
========================================
Human (R) vs Computer (Y)
Enter column 1-7 to drop your disc.
Type 'q' or 'quit' to exit.
========================================

 | | | | | | | |
 | | | | | | | |
 | | | | | | | |
 | | | | | | | |
 | | | | | | | |
 | | | | | | | |
+---+---+---+---+---+---+---+

You (R), enter column 1-7 (or 'q' to quit): 4
Computer (Y) chooses column 3

 | | | | | | | |
 | | | | | | | |
 | | | | | | | |
 | | | | | | | |
 | | | | | | | |
 | | |R| |Y| | |
+---+---+---+---+---+---+---+

You (R), enter column 1-7 (or 'q' to quit): 4
Computer (Y) chooses column 5

 | | | | | | | |
 | | | | | | | |
 | | | | | | | |
 | | | | | | | |
 | | | | | | | |
 | | |R|R|Y|Y| |
+---+---+---+---+---+---+---+

... game continues ...
```

## Project Structure
```
src/
├── main/java/com/connectfour/
│   ├── Board.java          # Board logic, win detection, gravity
│   ├── Player.java         # Abstract player base class
│   ├── HumanPlayer.java    # Human input handling
│   ├── ComputerPlayer.java # Random move selection
│   ├── GameEngine.java     # Game flow, state management
│   └── Main.java           # Entry point, game loop
└── test/java/com/connectfour/
    ├── BoardTest.java      # Board unit tests
    ├── GameEngineTest.java # Game engine unit tests
    └── PlayerTest.java     # Player unit tests
```

## Design Notes
- **Separation of concerns**: Game logic (`Board`, `GameEngine`, `Player`) is completely separate from I/O (`Main`, `HumanPlayer`)
- **Testability**: Core logic can be unit tested without any console I/O
- **Extensibility**: Easy to add new player types (e.g., AI with minimax) by extending `Player` class

## License
MIT
