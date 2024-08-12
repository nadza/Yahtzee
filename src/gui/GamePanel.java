package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import logic.Logic;
import saveGame.SaveGame;

/**
 * Represents a panel in the game interface that handles game display and interactions.
 * 
 * This panel manages the game state, user interface elements such as dice buttons, score buttons,
 * and controls for rolling dice and saving the game. It also updates the display based on game actions
 * and handles user inputs and game logic interactions.
 * 
 */
public class GamePanel extends JPanel {
	/**
	 * An array of field indices that are considered unchangeable.
	 */
	private int[] unchangeableFields = new int[]{6, 7, 8, 16, 17};

	/**
	 * Label displaying the current player's name.
	 */
	private JLabel currentPlayerLabel;

	/**
	 * Array of buttons representing the dice. Each button allows the player to interact with a die.
	 */
	private JButton[] diceButtons;

	/**
	 * Array holding the current values of the dice.
	 */
	private int[] dices;

	/**
	 * Label showing the number of throws left for the current turn.
	 */
	private JLabel xThrowsLeft;

	/**
	 * Button that triggers a roll of the dice when pressed.
	 */
	private JButton rollButton;

	/**
	 * Panel containing score-related information and components.
	 */
	private JPanel scoreArea;

	/**
	 * Button that allows the user to save the current state of the game.
	 */
	private JButton saveGameButton;

	/**
	 * Button that advances to the next player.
	 */
	private JButton nextButton;

	/**
	 * Array of buttons used to record scores in different categories.
	 */
	private JButton[] scoreButtons;

	/**
	 * Array of usernames for the players participating in the game.
	 */
	private String[] playerUsernames;

	/**
	 * Instance of the Logic class, which contains the game logic and rules.
	 */
	private Logic logic;

	/**
	 * Index representing the current player’s position in the player list.
	 */
	private int playerIndex;

	/**
	 * Counter for the number of throws used in the current turn.
	 */
	private int numberOfThrows;

	/**
	 * Array to hold scores for different categories or rounds.
	 */
	private int[] score;

	/**
	 * Instance of the Gui class that manages the graphical user interface.
	 */
	private Gui gui;

	/**
	 * Instance of the SaveGame class responsible for saving and loading game states.
	 */
	private SaveGame saveGame;


    /**
     * Gets the current player label text.
     * 
     * @return the text of the current player label.
     */
    public String getcurrentPlayerLabel() { return currentPlayerLabel.getText(); }

    /**
     * Updates the current player label to show the turn of the current player.
     */
    private void updatecurrentPlayerLabel() { currentPlayerLabel.setText("TURN: " + playerUsernames[playerIndex]); }

    /**
     * Sets the image for the dice face at the specified index.
     * 
     * @param diceIndex the index of the dice.
     * @param imagePath the path to the image.
     */
    private void setDiceFaceImage(int diceIndex, String imagePath) {
        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            Image scaledImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImg);
            diceButtons[diceIndex].setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the file path for the dice image based on the dice value.
     * 
     * @param diceValue the value of the dice.
     * @return the file path of the dice image.
     */
    private String getDiceImage(int diceValue) {
        if(diceValue == 1) return "images/dice_1.png";
        else if(diceValue == 2) return "images/dice_2.png";
        else if(diceValue == 3) return "images/dice_3.png";
        else if(diceValue == 4) return "images/dice_4.png";
        else if(diceValue == 5) return "images/dice_5.png";
        else if(diceValue == 6) return "images/dice_6.png";
        return "";
    }

    /**
     * Gets the file path for the saved dice image based on the dice value.
     * 
     * @param diceValue the value of the dice.
     * @return the file path of the saved dice image.
     */
    private String getSavedDiceImage(int diceValue) {
        if(diceValue == 1) return "images/saved_dice_1.png";
        else if(diceValue == 2) return "images/saved_dice_2.png";
        else if(diceValue == 3) return "images/saved_dice_3.png";
        else if(diceValue == 4) return "images/saved_dice_4.png";
        else if(diceValue == 5) return "images/saved_dice_5.png";
        else if(diceValue == 6) return "images/saved_dice_6.png";
        return "";
    }

    /**
     * Updates the dice images based on their current state.
     */
    private void setDices() {
        for(int i = 0; i < diceButtons.length; i++) {
            int number = dices[i];
            String imagePath = "";
            if(logic.isDiceSaved(i))
                imagePath = getSavedDiceImage(number);
            else imagePath = getDiceImage(number);
            setDiceFaceImage(i, imagePath);
        }
    }

    /**
     * Constructs the GamePanel with specified parameters and initializes the panel.
     * 
     * @param gui1 the GUI object.
     * @param logic1 the logic object.
     * @param saveGame1 the save game object.
     * @param playerUsernames1 the array of player usernames.
     */
    public GamePanel(Gui gui1, Logic logic1, SaveGame saveGame1, String[] playerUsernames1) {
        gui = gui1;
        logic = logic1;
        saveGame = saveGame1;
        playerUsernames = playerUsernames1;
        playerIndex = 0;
        numberOfThrows = 1;
        init();
    }

    /**
     * Initializes the game panel layout and components.
     */
    private void init() {
        setLayout(new GridBagLayout());
        setBackground(new Color(21, 88, 67)); 

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;

        c.gridy = 0;
        currentPlayerLabel = new JLabel("TURN: " + playerUsernames[playerIndex]);
        currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        currentPlayerLabel.setForeground(Color.WHITE);
        add(currentPlayerLabel, c);
         
        logic.throwDices();
        dices = logic.getDices();
        JPanel dicePanel = new JPanel();
        dicePanel.setBackground(new Color(21, 88, 67)); 
        dicePanel.setLayout(new GridLayout(1, 5, 10, 10));
        diceButtons = new JButton[5];
        for(int i = 0; i < diceButtons.length; i++) {
            diceButtons[i] = new JButton();
            diceButtons[i].setPreferredSize(new Dimension(80, 80));
            diceButtons[i].addActionListener(new DiceButtonListener(i));
            dicePanel.add(diceButtons[i]);
        }
        setDices();
        c.gridy++;
        add(dicePanel, c);

        c.gridy++;
        xThrowsLeft = new JLabel(3-numberOfThrows + " throws left.");
        xThrowsLeft.setForeground(Color.WHITE);
        add(xThrowsLeft, c);

        c.gridy++;
        rollButton = new JButton("Roll");
        rollButton.addActionListener(new RollButtonListener());
        add(rollButton, c);
             
        score = logic.calculateScore(playerIndex);
        logic.calculateScore(playerIndex);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 5; 
        scoreArea = initScorePanel(); 
        add(scoreArea, c);
        c.gridheight = 1; 

        c.gridx = 1;
        c.gridy = 5;
        saveGameButton = new JButton("Save Game");
        saveGameButton.addActionListener(new SaveGameButtonListener());
        saveGameButton.setEnabled(false);
        add(saveGameButton, c);
        
        c.gridx = 2; 
        c.gridy = 5; 
        nextButton = new JButton("Next");
        nextButton.addActionListener(new NextButtonListener());
        nextButton.setEnabled(false);
        add(nextButton, c);
    }

    /**
     * Initializes the score panel with field names and score buttons.
     * 
     * @return the initialized score panel.
     */
    private JPanel initScorePanel() {    	
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(21, 88, 67)); 

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0; 
        c.weighty = 0.0; 

        String[] fieldNames = logic.getFieldNames(); 
        scoreButtons = new JButton[fieldNames.length];

        for(int i = 0; i < fieldNames.length; i++) {               
            c.gridy = i;
            JLabel fieldNameLabel = new JLabel(fieldNames[i]);
            fieldNameLabel.setForeground(Color.WHITE);
            fieldNameLabel.setPreferredSize(new Dimension(180, 20)); 
            panel.add(fieldNameLabel, c);

            c.gridx = 1;
            scoreButtons[i] = new JButton(String.valueOf(score[i]));
            scoreButtons[i].setPreferredSize(new Dimension(80, 20)); 
            scoreButtons[i].addActionListener(new ScoreButtonListener(i));
            for(int unchangeableField: unchangeableFields) 
                if(i == unchangeableField) {
                    scoreButtons[i].setEnabled(false);
                    break;
                }
            panel.add(scoreButtons[i], c);

            c.gridx = 0; 
        }
        return panel;
    }

	/**
	 * Constructs a DiceButtonListener for the specified dice index.
	 * 
	 * @param diceIndex the index of the dice.
	 */
    private class DiceButtonListener implements ActionListener {
        private int diceIndex;
        public DiceButtonListener(int diceIndex) {
            this.diceIndex = diceIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean saved = logic.isDiceSaved(diceIndex);
            if(!saved) {
                int value = dices[diceIndex];
                String imagePath = getSavedDiceImage(value);
                setDiceFaceImage(diceIndex, imagePath);
                logic.saveDice(diceIndex);
            } else {
                int value = dices[diceIndex];
                String imagePath = getDiceImage(value);
                setDiceFaceImage(diceIndex, imagePath);
                logic.unsaveDice(diceIndex);
            }
        }
    }

	/**
	 * Handles the action event when the roll button is pressed.
	 * Rolls the dice and updates the UI.
	 * Disables the roll button after the third roll.
	 */
    private class RollButtonListener implements ActionListener {
        /**
         * This method is called when the roll button is pressed.
         * It rolls the dice, updates the UI, and disables the roll button
         * after the third roll.
         * 
         * @param e The ActionEvent triggered by pressing the roll button.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            logic.throwDices();
            dices = logic.getDices();
            setDices();

            numberOfThrows++;
            updateXThrowsLeft();
            
            score = logic.calculateScore(playerIndex);
            updateScoreButtons();
              
            if(numberOfThrows == 3) 
                rollButton.setEnabled(false);
        }
    }

	/**
	 * Handles the action for the Next button.
	 */
	private class NextButtonListener implements ActionListener {
		/**
	     * Constructs a new NextButtonListener instance.
	     * The default constructor does not perform any specific actions.
	     */
	    public NextButtonListener() {}
	    
	    /**
	     * Handles the action event when the "Next" button is pressed.
	     * Cycles through the playerIndex to switch to the next player.
	     * If the current player is the last one in the list, it wraps around to the first player.
	     *
	     * @param e the action event that triggered this method
	     */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (playerIndex == playerUsernames.length - 1)
				playerIndex = 0;
			else playerIndex++;
		}
	}

	/**
	 * Listens for actions on the Score button and updates the score.
	 * Constructs a ScoreButtonListener for a specific index.
	 * 
	 * @param index The index of the score button.
	 */
	private class ScoreButtonListener implements ActionListener {
		private int index;
		public ScoreButtonListener(int index) {
			this.index = index;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int[] scores = new int[1];

			String message = "Set score for " + logic.getFieldNames()[index] + " to " + score[index] + "?";
			int choice = JOptionPane.showConfirmDialog(scoreArea, message, "Confirm Score", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				logic.saveField(playerIndex, index, score);
				updateScoreButtons();

				logic.updateAutomaticValues(playerIndex);

				if (logic.isGameOver())
					displayWinner();
				else {
					if (playerIndex == playerUsernames.length - 1)
						playerIndex = 0;
					else playerIndex++;

					if (logic.isPlayerDone(playerIndex))
						nextButton.setEnabled(true);
					else nextButton.setEnabled(false);

					updateScreen();
				}
			}
		}
	}

	/**
	 * Updates the state of score buttons based on current game state.
	 */
	private void updateScoreButtons() {
		if (score[18] != 1) {
			int[] emptyFields = logic.getEmptyFields(playerIndex);
			enableScoreButtons();
			for (int i = 0; i < scoreButtons.length; i++) {
				if (emptyFields[i] == 0)
					scoreButtons[i].setEnabled(false);
				if (i == 7)
					scoreButtons[i].setText("0");
				else scoreButtons[i].setText(String.valueOf(score[i]));
			}
		} else {
			for (int i = 0; i < scoreButtons.length; i++) {
				if (i == 7)
					scoreButtons[i].setText("0");
				else scoreButtons[i].setText(String.valueOf(score[i]));
			}
			disableScoreButtons();
			String message = "Yahtzee! Congratulations!";
			int choice = JOptionPane.showConfirmDialog(scoreArea, message, "Confirm Score", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				logic.updateAutomaticValues(playerIndex);

				if (logic.isGameOver())
					displayWinner();

				if (playerIndex == playerUsernames.length - 1)
					playerIndex = 0;
				else playerIndex++;

				updateScreen();
			}
		}
	}

	/**
	 * Enables all score buttons and disables those that cannot be used.
	 */
	private void enableScoreButtons() {
		for (int i = 0; i < scoreButtons.length; i++) {
			scoreButtons[i].setEnabled(true);
			for (int unchangeableField : unchangeableFields) {
				if (i == unchangeableField) {
					scoreButtons[i].setEnabled(false);
					break;
				}
			}
		}
	}

	/**
	 * Disables all score buttons.
	 */
	private void disableScoreButtons() {
		for (int i = 0; i < scoreButtons.length; i++)
			scoreButtons[i].setEnabled(false);
	}

	/**
	 * Handles the action for the Save Game button.
	 */
	private class SaveGameButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String message = "Would you like to save your game?";
			int choice = JOptionPane.showConfirmDialog(scoreArea, message, "Yes", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				long numberOfFiles = saveGame.getNumberOfFiles();
				String filePath = "";
				if (numberOfFiles == 0)
					filePath = "savedGames/game_1.txt";
				else if (numberOfFiles == 1)
					filePath = "savedGames/game_2.txt";
				else if (numberOfFiles == 2)
					filePath = "savedGames/game_3.txt";
				else filePath = "savedGames/game_1.txt";

				saveGame.saveGame(filePath);
			}
		}
	}

	/**
	 * Returns the number of throws left.
	 * 
	 * @return The number of throws left.
	 */
	public int getNumberOfThrows() {
		return numberOfThrows;
	}

	/**
	 * Updates the display text showing the number of throws left.
	 */
	private void updateXThrowsLeft() {
		if (numberOfThrows == 2)
			xThrowsLeft.setText("1 throw left.");
		else if (numberOfThrows == 1)
			xThrowsLeft.setText("2 throws left.");
		else
			xThrowsLeft.setText("Choose and add your score to the table to finalize your turn.");
	}

	/**
	 * Updates the game screen with the current game state.
	 */
	private void updateScreen() {
		updatecurrentPlayerLabel();
		logic.unsaveDices();

		logic.throwDices();
		dices = logic.getDices();
		setDices();

		numberOfThrows = 1;
		updateXThrowsLeft();
		score = logic.calculateScore(playerIndex);

		rollButton.setEnabled(true);
		updateScoreButtons();

		if (playerIndex == playerUsernames.length - 1)
			saveGameButton.setEnabled(true);

		if (playerUsernames[playerIndex].equals("Computer"))
			makeComputerMove();
	}

	/**
	 * Displays the winner of the game.
	 */
	private void displayWinner() {
		String filePath = "highscores/scores.txt";
		saveGame.saveFinalScores(filePath);
		
	    JPanel scoresPanel = new JPanel();
	    scoresPanel.setLayout(new GridBagLayout());
	    scoresPanel.setBackground(new Color(21, 88, 67)); 

	    GridBagConstraints c = new GridBagConstraints();
	    c.gridx = 0;
	    c.gridy = 0;
	    c.anchor = GridBagConstraints.WEST;
	    c.insets = new Insets(10, 10, 10, 10);

	    JLabel scoresTitle = new JLabel("Final Scores:");
	    scoresTitle.setFont(new Font("Arial", Font.BOLD, 18));
	    scoresTitle.setForeground(Color.WHITE);
	    scoresPanel.add(scoresTitle, c);

	    c.gridy++;
	    JTextArea scoresArea = new JTextArea();
	    scoresArea.setEditable(false);
	    scoresArea.setLineWrap(true);
	    scoresArea.setWrapStyleWord(true);
	    scoresArea.setPreferredSize(new Dimension(350, 400));
	    scoresArea.setBackground(new Color(21, 88, 67));
	    scoresArea.setForeground(Color.WHITE);
	    scoresArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
	    
	    StringBuilder scoresText = new StringBuilder();
	    scoresText.append(String.format("%-20s %s%n", "Username:", "Score:"));
	    for(int i = 0; i < playerUsernames.length; i++) {
	        String playerName = playerUsernames[i];
	        int[] finalScore = logic.getPlayerScore(i);
	        int finalTotal = finalScore[17];
	        scoresText.append(String.format("%-20s %d%n", playerName, finalTotal));
	    }
	    
	    scoresArea.setText(scoresText.toString());
	    c.fill = GridBagConstraints.HORIZONTAL;
	    scoresPanel.add(scoresArea, c);

	    c.gridy++;
	    String winner = logic.getWinner();
	    JLabel winnerLabel = new JLabel("Winner: " + winner);
	    winnerLabel.setFont(new Font("Arial", Font.BOLD, 24));
	    winnerLabel.setForeground(Color.WHITE);
	    scoresPanel.add(winnerLabel, c);

	    c.gridy++;
	    c.anchor = GridBagConstraints.CENTER;
	    gui.addBackButton(scoresPanel, c);

	    removeAll();
	    add(scoresPanel);
	    revalidate();
	    repaint();
	}
	   
	/**
	 * Makes the computer perform its move by selecting the best score button.
	 */
	private void makeComputerMove() {
		int[] emptyFields = logic.getEmptyFields(playerIndex);
		int bestScoreIndex = logic.getBestIndex(emptyFields, score);

		JButton buttonToPress = scoreButtons[bestScoreIndex];
		buttonToPress.doClick();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
