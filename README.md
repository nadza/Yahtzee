# Yahtzee Game in Java

Welcome to the Yahtzee game repository! This project includes both console and graphical user interface (GUI) versions of the classic Yahtzee game, implemented in Java.

## Features

- **Console Version**: Play Yahtzee directly in your terminal with a simple text-based interface.
- **GUI Version**: Enjoy a fully interactive graphical interface with buttons, dice images, and dynamic score updates.
- **Game Modes**: 
  - **Singleplayer**: Play against the computer.
  - **Multiplayer**: Play with other players.
- **Save/Load Functionality**: Save your game progress and load previously saved games.
- **High Scores**: View high scores.

## Getting Started

To get started with the Yahtzee game, follow these steps:

1. **Clone the Repository**:
```
git clone https://github.com/nadza/Yahtzee
```

3. **Running the Game**:

- **Navigate to the `src/main` directory**:
  
  Open your file explorer or terminal and go to the root directory of the project.
- **Compile the Project**:
  
  Use the following command to compile all Java files in the src directory and place the compiled classes in the bin directory:
  ```
  find src -name "*.java" -print | xargs javac -d bin
  ```
- **Run the Game**:
  
  After compilation, execute the following command to start the game:
  ```
  java -cp bin main.Main
  ```

Feel free to reach out if you have any questions or need further assistance.

Enjoy playing Yahtzee!
