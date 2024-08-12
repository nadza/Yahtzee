package console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import logic.Logic;
import saveGame.SaveGame;

/**
 * Represents the Console class, which manages the console-based interface for the application.
 * This class is responsible for displaying and handling console-based interactions, including
 * initializing and managing different screens or prompts within the console environment.
 */
public class Console {
    private Scanner scanner = new Scanner(System.in);
    private String[] playerUsernames;
    private Logic logic;
    private SaveGame saveGame;

    /**
     * Constructor for the Console class. It initializes the Console object and displays the start screen.
     */
    public Console() { startScreen(); }

    /**
     * Displays the start screen of the game with options to start the game, view about information, or exit.
     */
    private void startScreenMessage() {
        System.out.println("==================================================================================================");
        System.out.println("                                      YAHTZEE GAME           ");
        System.out.println("==================================================================================================");
        System.out.println("1. Start Game");
        System.out.println("2. About Game");
        System.out.println("3. High Scores");
        System.out.println("4. Exit");
        System.out.println("==================================================================================================");
        System.out.println("                             Please choose an option (1-4): ");
        System.out.print("Your choice: ");
    }
    
    /**
     * Manages the start screen flow and handles user choice for starting the game, viewing game information, viewing highscores or exiting.
     */
    private void startScreen() {
        startScreenMessage();
        while(true) {
            String choice = scanner.nextLine().trim();
            
            if(choice.equals("1")) {
                chooseGame();
                break;
            } else if(choice.equals("2")) {
                aboutGame();
                waitForReturn();
                startScreen();
                //break;
            } else if(choice.equals("3")) {
	            highScores();
	            waitForReturn();
	            startScreen();
	            //break;
        }
            else if(choice.equals("4")) {
                exitGame();
                break;
            } else System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
        }
    }
    
    /**
     * Displays the high scores.
     */
    private void highScores() {
    	if(logic == null) {
    		logic = new Logic();
    		saveGame = new SaveGame(logic);
    	}
    	String filePath = "highscores/scores.txt";
        String[] playerNames = saveGame.getPlayerNames(filePath);
        int[] playerScores = saveGame.getPlayerScores(filePath);
        
        System.out.println("==================================================================================================");
        System.out.println("                                       HIGH SCORES");
        System.out.println("==================================================================================================");
        System.out.printf("%-20s %s%n", "Username", "Score");
        System.out.println("--------------------------");
        for (int i = 0; i < playerNames.length; i++) 
            System.out.printf("%-20s %s%n", playerNames[i], playerScores[i]);
	    System.out.println();
	}

	/**
     * Displays information about the Yahtzee game.
     */
    private void aboutGame() {
        System.out.println("==================================================================================================");
        System.out.println("                                      ABOUT YAHTZEE            ");
        System.out.println("==================================================================================================");
        System.out.println("Yahtzee is a dice game based on Poker. The object of the game is to roll certain combinations of numbers with five dice.");
        System.out.println("At each turn you throw dice trying to get a good combination of numbers; different combinations give different scores.");
        System.out.println("While luck plays a big role in Yahtzee, strategy makes a significant difference.");
        System.out.println("The reason for this is that you score each combination just once, and the number of different combinations in which you can score is equal to the number of turns in the game.");
        System.out.println("This means that you have to make wise choices about when to score in each combination and you have to be careful about what combinations you seek at each turn.");
        System.out.println();
    }
    
    /**
     * Waits for the user to press Enter to return to the main menu.
     */
    private void waitForReturn() {
        System.out.println("Press Enter to return to the main menu...");
        scanner.nextLine(); 
    }
    
    /**
     * Exits the game and prints a thank you message.
     */
    private void exitGame() {
        System.out.println("==================================================================================================");
        System.out.println("                             Thank you for playing Yahtzee!");
        System.out.println("                                        Goodbye!");
        System.exit(0);
    }
       
    /**
     * Displays the game mode selection screen with options to start a classic game, play with friends, load a game, or return to the main menu.
     */
    private void chooseGameMessage() {
        System.out.println("==================================================================================================");
        System.out.println("                                    CHOOSE GAME MODE                                         ");
        System.out.println("==================================================================================================");
        System.out.println("1. Classic Game (Against Computer)");
        System.out.println("2. Play with Friends");
        System.out.println("3. Load Game");
        System.out.println("4. Return to Main Menu");
        System.out.println("==================================================================================================");
        System.out.println("Please choose an option (1-4): ");
        System.out.print("Your choice: ");
    }

    /**
     * Handles the game mode selection and directs the user to the appropriate method based on their choice.
     */
    private void chooseGame() {
        chooseGameMessage();
        while(true) {
            String choice = scanner.nextLine().trim();

            if(choice.equals("1")) {
                startClassicGame();
                break; 
            } else if(choice.equals("2")) {
                startMultiplayerGame(); 
                break; 
            } else if(choice.equals("3")) {
                loadGames();
                break;
            } else if(choice.equals("4")) {
                startScreen();
            } else System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
        }
    }
    
    /**
     * Handles loading of saved games. Displays available saved games and prompts the user to choose one.
     */
    private void loadGames() {
        System.out.println("==================================================================================================");
        System.out.println("                                        LOAD GAME");
        System.out.println("==================================================================================================");
        
        // Basic setup of private variables; will be changed later
        logic = new Logic();
        saveGame = new SaveGame(logic);
        long numberOfFiles = saveGame.getNumberOfFiles();

        if(numberOfFiles == 0) {
            System.out.println("There are no saved games available.");
            waitForReturn();
            startScreen();
        }
        
        while(true) { 
            if(numberOfFiles == 1) {
                System.out.println("1. Game 1");
                System.out.println("2. Back");
                System.out.println("==================================================================================================");
                System.out.println("Please choose an option (1-2): ");
                System.out.print("Your choice: ");
            } else if(numberOfFiles == 2) {
                System.out.println("1. Game 1");
                System.out.println("2. Game 2");
                System.out.println("3. Back");
                System.out.println("==================================================================================================");
                System.out.println("Please choose an option (1-3): ");
                System.out.print("Your choice: ");
            } else if(numberOfFiles == 3) {
                System.out.println("1. Game 1");
                System.out.println("2. Game 2");
                System.out.println("3. Game 3");
                System.out.println("4. Back");
                System.out.println("==================================================================================================");
                System.out.println("Please choose an option (1-4): ");
                System.out.print("Your choice: ");
            }

            String choice = scanner.nextLine().trim();

            if(numberOfFiles == 1) {
                if(choice.equals("1")) {
                    saveGame.loadGame("savedGames/game_1.txt");
                    playerUsernames = saveGame.getPlayerUsernames();
                    startGame();
                    break;
                } else if(choice.equals("2")) {
                    chooseGame();
                } else System.out.println("Invalid choice. Please enter 1 or 2.");
            } else if (numberOfFiles == 2) {
                if(choice.equals("1")) {
                    saveGame.loadGame("savedGames/game_1.txt");
                    playerUsernames = saveGame.getPlayerUsernames();
                    startGame();
                    break;
                } else if(choice.equals("2")) {
                    saveGame.loadGame("savedGames/game_2.txt");
                    playerUsernames = saveGame.getPlayerUsernames();
                    startGame();
                    break;
                } else if(choice.equals("3")) {
                    chooseGame();
                } else System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                
            } else if (numberOfFiles == 3) {
                if(choice.equals("1")) {
                    saveGame.loadGame("savedGames/game_1.txt");
                    playerUsernames = saveGame.getPlayerUsernames();
                    startGame();
                    break;
                } else if(choice.equals("2")) {
                    saveGame.loadGame("savedGames/game_2.txt");
                    playerUsernames = saveGame.getPlayerUsernames();
                    startGame();
                    break;
                } else if(choice.equals("3")) {
                    saveGame.loadGame("savedGames/game_3.txt");
                    playerUsernames = saveGame.getPlayerUsernames();
                    startGame();
                    break;
                } else if(choice.equals("4")) {
                    chooseGame();
                } else System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }
    }
   
    /**
     * Starts a classic game mode with a user and a computer.
     */
    private void startClassicGame() {
        playerUsernames = new String[]{"User", "Computer"};
        logic = new Logic(playerUsernames);
        startGame();    
    }
    
    /**
     * Starts a multiplayer game mode where the user chooses the number of players.
     */
    private void startMultiplayerGame() {
        chooseNumberOfPlayers();
        logic = new Logic(playerUsernames);
        startGame();
    }
    
    /**
     * Prompts the user to enter the number of players for a multiplayer game.
     */
    private void chooseNumberOfPlayers() {
        System.out.println("==================================================================================================");
        System.out.println("                                   NUMBER OF PLAYERS          ");
        System.out.println("==================================================================================================");
        
        int numberOfPlayers;
        
        while(true) {
            try {
                System.out.print("Enter the number of players (between 2 and 10): ");
                numberOfPlayers = Integer.parseInt(scanner.nextLine());
                if(numberOfPlayers < 2 || numberOfPlayers > 10) {
                    System.out.println("");
                    throw new IllegalArgumentException("Number of players must be between 2 and 10.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. " + e.getMessage());
            }
        }
        
        playerUsernames = new String[numberOfPlayers];
        for(int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Enter the username for Player " + (i + 1) + ": ");
            playerUsernames[i] = scanner.nextLine();
        }
    }

	/**
	 * Displays the list of player usernames that have been set.
	 */
	private void printUsers() {
		System.out.println("Player usernames have been set.");
		for (String username : playerUsernames) 
			System.out.println("Player: " + username);
		System.out.println("==================================================================================================");
	}

	/**
	 * Shows an introduction message.
	 */
	private void displayIntroduction() {
		System.out.println("                                    WELCOME TO THE GAME                                     ");
		System.out.println("==================================================================================================");
		System.out.println("In this game, each player will take their turn one by one.");
		System.out.println("After every player has played their turn, you can save the game if you wish.");
		System.out.println("==================================================================================================");
	}

	/**
	 * Prompts the user to decide whether to save the game after all players have completed their turns.
	 */
	private void promptForSave() {
		System.out.println("==================================================================================================");
		System.out.println("All players have completed their turns. Would you like to save the game before continuing?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		System.out.print("Your choice: ");
		
		String choice = scanner.nextLine().trim();
		
		if (choice.equals("1"))
			saveGame();    
		else if (choice.equals("2")) 
			System.out.println("Game not saved.");    
		else {
			System.out.println("Invalid choice. Please enter 1 or 2.");
			promptForSave();
		}
	}

	/**
	 * Prompts the user to decide whether to exit the game.
	 */
	private void promptForExit() {
		System.out.println("==================================================================================================");
		System.out.println("Would you like to exit the game?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		System.out.print("Your choice: ");
		
		int choice = scanner.nextInt();
		if(choice == 1) {
			startScreen();
		} else if(!(choice == 2)){
			System.out.println("Invalid choice. Please enter 1 or 2.");
			promptForExit();
		}
	}

	/**
	 * Saves the current game state to a file.
	 * Chooses the file path based on the number of existing saved game files.
	 */
	private void saveGame() {
		saveGame = new SaveGame(logic);
		long numberOfFiles = saveGame.getNumberOfFiles();
		String filePath = "";
		
		if (numberOfFiles == 0)
			filePath = "savedGames/game_1.txt";
		else if (numberOfFiles == 1)
			filePath = "savedGames/game_2.txt";
		else if (numberOfFiles == 2)
			filePath = "savedGames/game_3.txt";
		else 
			filePath = "savedGames/game_1.txt";

		saveGame.saveGame(filePath);

		System.out.print("Saving game.");
		waiting();
	}

	/**
	 * Starts the game loop where players take their turns, dice are thrown, and scores are calculated.
	 */
	private void startGame() {   
		printUsers();
		displayIntroduction();
		
		int numberOfPlayers = playerUsernames.length;
		int playerIndex = 0;
		int throwsOfDice = 1;

		while (!logic.isGameOver()) {
			if (logic.isPlayerDone(playerIndex))
				continue;
			
			System.out.println();
			System.out.println("Playing: " + playerUsernames[playerIndex]);
			while (throwsOfDice < 4) {
				logic.throwDices();
				int[] dices = logic.getDices();
				printDices(dices);
				
				if(!playerUsernames[playerIndex].equals("Computer")) {
					if(throwsOfDice != 3)
						processDices(throwsOfDice);
					throwsOfDice = playAgain(throwsOfDice);
				} else 
					throwsOfDice = 4;
			}   
			
			int[] score = logic.calculateScore(playerIndex);          
			printScore(score, playerIndex);
			
			if(score[18] == -1)
				promptUserForFieldChange(playerIndex, score);
			logic.updateAutomaticValues(playerIndex); 
			
			promptForExit();
			if (playerIndex == numberOfPlayers - 1) {
				promptForSave();             
				playerIndex = 0;
			} else 
				playerIndex++;

			logic.unsaveDices();
			throwsOfDice = 1;
		}
		
		printScores();
		String winner = logic.getWinner();
		System.out.println();
		System.out.println("CONGRATULATIONS! The winner of the game is: " + winner + ".");
		
		waitForReturn();
		startScreen();
	}

	/**
	 * Prints the faces of the dice based on their current values.
	 */
	private void printDices(int[] dices) {
		System.out.print("Shaking dices.");
		waiting();
		
		String[] firstRow = new String[5];
		String[] secondRow = new String[5];
		String[] thirdRow = new String[5];
		String[] fourthRow = new String[5];

		for (int i = 0; i < 5; i++) {
			int face = dices[i];
			if (face == 1) {
				firstRow[i]  = " _____ ";
				secondRow[i] = "|     |";
				thirdRow[i]  = "|  o  |";
				fourthRow[i] = "|     |";
			} else if (face == 2) {
				firstRow[i]  = " _____ ";
				secondRow[i] = "|     |";
				thirdRow[i]  = "|o   o|";
				fourthRow[i] = "|     |";
			} else if (face == 3) {
				firstRow[i]  = " _____ ";
				secondRow[i] = "|o    |";
				thirdRow[i]  = "|  o  |";
				fourthRow[i] = "|    o|";
			} else if (face == 4) {
				firstRow[i]  = " _____ ";
				secondRow[i] = "|o   o|";
				thirdRow[i]  = "|     |";
				fourthRow[i] = "|o   o|";
			} else if (face == 5) {
				firstRow[i]  = " _____ ";
				secondRow[i] = "|o   o|";
				thirdRow[i]  = "|  o  |";
				fourthRow[i] = "|o   o|";
			} else if (face == 6) {
				firstRow[i]  = " _____ ";
				secondRow[i] = "|o   o|";
				thirdRow[i]  = "|o   o|";
				fourthRow[i] = "|o   o|";
			}
		}

		printRow(firstRow);
		printRow(secondRow);
		printRow(thirdRow);
		printRow(fourthRow);

		System.out.print(" ‾‾‾‾‾ ");
		for (int i = 0; i < 4; i++) 
			System.out.print("  ‾‾‾‾‾ ");
		System.out.println();
	}

	/**
	 * Prints a row of strings with each string separated by a space.
	 * 
	 * @param row Array of strings to be printed as a single row.
	 */
	private void printRow(String[] row) {
		for (String s : row) 
			System.out.print(s + " ");
		System.out.println();
	}

	/**
	 * Processes the dice based on user input to either save or unsave them.
	 * 
	 * @param throwsOfDice The number of dice throws remaining.
	 */
	private void processDices(int throwsOfDice) {
		System.out.println("You have rolled the dice. Would you like to save or unsave any of them?");
		while (true) {
			System.out.println();
			System.out.println("Type 's' for Save, 'u' for Unsave, or 'n' for No change.");
			String choice = scanner.nextLine().trim().toLowerCase();

			if (choice.equals("s") || choice.equals("u") || choice.equals("n")) {
				if (choice.equals("n")) 
					break;
				
				int[] chosenDices = getDiceSelection(choice.equals("s") ? "save" : "unsave");
				logic.processDices(chosenDices, choice);
			} else System.out.println("Invalid choice. Please enter 's' for Save, 'u' for Unsave, or 'n' for No change.");
		}
	}

	/**
	 * Prompts the user to enter the numbers of the dice they want to save or unsave.
	 * 
	 * @param action The action to be performed ('save' or 'unsave').
	 * @return An array of chosen dice numbers.
	 */
	private int[] getDiceSelection(String action) {
		int[] chosenDices = new int[5];
		boolean validDiceInput = false;
		while(!validDiceInput) {
			System.out.println("Enter the dice numbers to " + action + " (e.g., 1 3 5):");
			String input = scanner.nextLine().trim();
			String[] diceNumbers = input.split("\\s+");

			validDiceInput = true;
			int i = 0;
			for(String number: diceNumbers) {
				try {
					int diceNumber = Integer.parseInt(number);
					if (diceNumber >= 1 && diceNumber <= 5) {
						chosenDices[i] = diceNumber;
						i++;
					} else {
						System.out.println("Invalid dice number: " + diceNumber);
						validDiceInput = false;
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input: " + number);
					validDiceInput = false;
					break;
				}
			}
		}
		return chosenDices;
	}

	/**
	 * Asks the user if they want to throw the dice again.
	 * Updates the number of throws remaining based on the user's choice.
	 * 
	 * @param throwsOfDice The current number of throws.
	 * @return The updated number of throws.
	 */
	private int playAgain(int throwsOfDice) {
		if(throwsOfDice < 3) {
			int throwsLeft = 3 - throwsOfDice;
			System.out.println("You have " + throwsLeft + " throw" + (throwsLeft > 1 ? "s" : "") + " left. Do you want to throw again? Type 'y' for Yes, 'n' for No.");
			
			String answer = scanner.nextLine().trim().toLowerCase();
			while(!answer.equals("y") && !answer.equals("n")) {
				System.out.println("Invalid input. Please enter 'y' or 'n'.");
				answer = scanner.nextLine().trim().toLowerCase();
			}
			if(answer.equals("y")) 
				throwsOfDice++;
			else throwsOfDice = 4; 
		} else throwsOfDice = 4; 
		
		if(throwsOfDice == 4) {
			System.out.print("Calculating your score.");
			waiting();
		}
		return throwsOfDice;
	}

	/**
	 * Prompts the user to choose a field to change based on their score and available fields.
	 * 
	 * @param playerIndex The index of the current player.
	 * @param scores The array of scores for the player.
	 */
	private void promptUserForFieldChange(int playerIndex, int[] scores) {
		boolean isValid = false;
		int fieldIndex = -1;

		int[] emptyFields = logic.getEmptyFields(playerIndex);
		
		int count = 5;
		for(int i = 0; i < emptyFields.length; i++) 
			if(emptyFields[i] == 0) 
				count++;
		
		int[] unchangeableFields = new int[count];
		unchangeableFields[0] = 6;
		unchangeableFields[1] = 7;
		unchangeableFields[2] = 8;
		unchangeableFields[3] = 16;
		unchangeableFields[4] = 17;
		
		int j = 5;
		for(int i = 0; i < emptyFields.length; i++) 
			if(emptyFields[i] == 0) {
				unchangeableFields[j] = i;
				j++;
			}

		while(!isValid) {
			if(playerUsernames[playerIndex].equals("Computer")) {
				isValid = true;
				fieldIndex = logic.getBestIndex(emptyFields, scores);
				logic.saveField(playerIndex, fieldIndex, scores);
			} else {
				System.out.println();
				System.out.println("Enter the number corresponding to the field you want to change (0-17):");
				System.out.println("0: Aces, 1: Twos, 2: Threes, 3: Fours, 4: Fives, 5: Sixes, 9: Three Of A Kind, 10: Four Of A Kind, "
								+ "11: Full House, 12: Small Straight, 13: Large Straight, 14: Yahtzee, 15: Chance");
				System.out.println("(Fields 6: Total Without Bonus, 7: Bonus, 8: Final Upper Total, 16: Final Lower Total, 17: Final Total cannot be changed)");
				
				fieldIndex = scanner.nextInt();
			
				if(fieldIndex >= 0 && fieldIndex <= 17) {
					boolean isChangeable = true;
					for(int unchangeableField: unchangeableFields) 
						if(fieldIndex == unchangeableField) {
							isChangeable = false;
							break;
						}
				
					if(isChangeable) {
						isValid = true;
						logic.saveField(playerIndex, fieldIndex, scores);
					} else System.out.println("The selected field cannot be changed.");
				} else System.out.println("Invalid field index. Please enter a number between 0 and 17.");
			}
		}
	}

	/**
	 * Prints the score card for a specific player, including both upper and lower sections.
	 * 
	 * @param score The array of scores for the player.
	 * @param playerIndex The index of the current player.
	 */
	private void printScore(int[] score, int playerIndex) {
		String[] fieldNames = logic.getFieldNames();
		
		System.out.println();
		String player = playerUsernames[playerIndex];
		System.out.println(player + "'s SCORE CARD");
		System.out.println("===============================");
		
		System.out.println("UPPER SECTION");
		System.out.println("-------------");
		printSection(fieldNames, score, 0, 8, playerIndex);
		System.out.println();
		System.out.println("LOWER SECTION");
		System.out.println("-------------");
		printSection(fieldNames, score, 9, 16, playerIndex);
		System.out.println();
		System.out.printf("%-24s %s%n", "FINAL TOTAL", score[17]);
	}

	/**
	 * Prints a section of the score card with field names and corresponding values.
	 * 
	 * @param fieldNames Array of field names.
	 * @param fieldValues Array of field values.
	 * @param startIndex The starting index of the section.
	 * @param endIndex The ending index of the section.
	 * @param playerIndex The index of the current player.
	 */
	private void printSection(String[] fieldNames, int[] fieldValues, int startIndex, int endIndex, int playerIndex) {
		int[] emptyFields = logic.getEmptyFields(playerIndex);
				
		for(int i = startIndex; i <= endIndex; i++) {
			String name = fieldNames[i].toUpperCase().replace(" ", " ");
			String checkMark = "";
			if(emptyFields[i] != 1) 
				checkMark = " ✓";
			System.out.printf("%-24s %s%s%n", name, ((fieldValues[i] == -1 ) || (fieldValues[i] == -2)) ? "---" : fieldValues[i], checkMark);
		}
	}

	/**
	 * Simulates a delay with a loading animation.
	 */
	private void waiting() {
		for(int i = 0; i < 2; i++) {
			try {
				Thread.sleep(1000);
				System.out.print(".");
			} catch (InterruptedException e) {
				System.out.println("The sleep operation was interrupted.");
				e.printStackTrace();
			}
		}
		System.out.println();
	}

	/**
	 * Prints the final scores for all players.
	 */
	private void printScores() {
		System.out.println();
		System.out.println("==================================================================================================");
		System.out.println("SCORES:");
		System.out.printf("%-20s %s%n", "Player", "Final Total");
		System.out.println("--------------------------------");

		for(int i = 0; i < playerUsernames.length; i++) {
			String playerName = playerUsernames[i];
			int[] finalScore = logic.getPlayerScore(i);
			int finalTotal = finalScore[17];
			System.out.printf("%-20s %d%n", playerName, finalTotal);
		}
	}    
}