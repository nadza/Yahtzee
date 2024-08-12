package main;

import java.util.Scanner;
import console.Console;
import gui.Gui;

/**
 * The entry point of the application that allows the user to choose between different versions of the Yahtzee game.
 */
public class Main {
    /**
     * Default constructor for the Main class. This constructor does not perform any specific actions.
     */
    public Main() {}
	
    /**
     * The main method that executes the application.
     * It prompts the user to select between the GUI or Console version of the Yahtzee game.
     * Based on the user's choice, it initializes the corresponding version of the game.
     * 
     * @param args Command-line arguments passed to the application.
     *             These arguments are not used in this implementation.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which version of the game Yahtzee would you like to play?");
        System.out.println("1. GUI");
        System.out.println("2. Console");
        System.out.println();
        System.out.print("Your choice: ");

        while(true) {
            int choice = scanner.nextInt();

            if(choice == 1) {
                Gui g = new Gui();
                break; 
            } else if (choice == 2) {
                Console c = new Console();
                break; 
            } else {
                System.out.println("Invalid choice. Please choose either 1 or 2.");
            }
        }

        scanner.close();        
    }
}
