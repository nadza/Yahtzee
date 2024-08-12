package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logic.Logic;
import saveGame.SaveGame;

/**
 * Represents the Gui class, which manages the gui-based interface for the application.
 * This class is responsible for displaying and handling gui-based interactions, including
 * initializing and managing different screens or prompts within the gui environment.
 */
public class Gui {
    private JFrame frame;
    private String[] playerUsernames;
    private Logic logic;
    private GamePanel gamePanel;
    private SaveGame saveGame;

    /**
     * Constructor to initialize the GUI and display the start screen.
     */
    public Gui() {
        init(); 
        startScreen(); 
    }
    
    /**
     * Initialize the main JFrame for the game.
     */
    private void init() {
        frame = new JFrame("Yahtzee");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Display the start screen with buttons for Play Game, About, High Scores, and Exit.
     */
    private void startScreen() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(21, 88, 67)); 

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        
        c.gridy = 0;
        JLabel heading = new JLabel("Yahtzee");
        heading.setFont(new Font("Serif", Font.PLAIN, 80));
        heading.setForeground(Color.WHITE); 
        panel.add(heading, c);

        c.gridy++;
        JButton playButton = new JButton("Play Game");
        playButton.setPreferredSize(new Dimension(80, playButton.getPreferredSize().height));
        playButton.addActionListener(e -> chooseGameScreen()); 
        panel.add(playButton, c);

        c.gridy++;
        JButton aboutButton = new JButton("About");
        aboutButton.setPreferredSize(new Dimension(80, aboutButton.getPreferredSize().height));
        aboutButton.addActionListener(e -> aboutScreen()); 
        panel.add(aboutButton, c);
        

        c.gridy++;
        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.setPreferredSize(new Dimension(80, highScoresButton.getPreferredSize().height));
        highScoresButton.addActionListener(e -> displayHighScores());
        panel.add(highScoresButton, c);

        c.gridy++;
        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(80, exitButton.getPreferredSize().height));
        exitButton.addActionListener(e -> System.exit(0)); 
        panel.add(exitButton, c);

        initScreen(panel); 
    }

    /**
     * Display the about screen with information about the game.
     */
    private void aboutScreen() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(21, 88, 67)); 

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        
        c.gridy = 0;
        JLabel heading = new JLabel("About Game");
        heading.setFont(new Font("Serif", Font.PLAIN, 40));
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setForeground(Color.WHITE); 
        panel.add(heading, c);

        c.gridy++;
        JTextArea aboutText = new JTextArea("Yahtzee is a dice game based on Poker. The object of the game is to roll certain combinations of numbers with five dice.  "
                  + "At each turn you throw dice trying to get a good combination of numbers; different combinations give different scores.  "
                  + "While luck plays a big role in Yahtzee, strategy makes a significant difference. "
                  + "The reason for this is that you score each combination just once, and the number of different combinations in which you can score is equal to the number of turns in the game. "
                  + "This means that you have to make wise choices about when to score in each combination and you have to be careful about what combinations you seek at each turn. ");
        aboutText.setEditable(false);
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setPreferredSize(new Dimension(350, 200));
        aboutText.setBackground(new Color(21, 88, 67)); 
        aboutText.setForeground(Color.WHITE);
        panel.add(aboutText, c);

        addBackButton(panel, c);
        initScreen(panel); 
    }
    
    /**
     * Display the game selection screen where the user can choose between Singleplayer, Multiplayer, or Load Game.
     */
    public void chooseGameScreen() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(21, 88, 67)); 

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        
        c.gridy = 0;
        JLabel heading = new JLabel("Choose Game");
        heading.setFont(new Font("Serif", Font.PLAIN, 40));
        heading.setForeground(Color.WHITE); 
        panel.add(heading, c);

        c.gridy++;
        JButton singleplayerButton = new JButton("Singleplayer");
        singleplayerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        singleplayerButton.addActionListener(e -> startClassicGame()); 
        panel.add(singleplayerButton, c);

        c.gridy++;
        JButton multiplayerButton = new JButton("Multiplayer");
        multiplayerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        multiplayerButton.addActionListener(e -> startMultiplayerGame()); 
        panel.add(multiplayerButton, c);

        c.gridy++;
        JButton loadButton = new JButton("Load Game");
        loadButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        loadButton.addActionListener(e -> loadGames()); 
        panel.add(loadButton, c);

        c.gridy++;
        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> startScreen()); 
        panel.add(backButton, c);
        initScreen(panel); 
    }

    /**
     * Display the screen for loading saved games.
     */
    private void loadGames() {
        logic = new Logic();
        saveGame = new SaveGame(logic);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(21, 88, 67)); 

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        
        c.gridy = 0;
        JLabel heading = new JLabel("Load Game");
        heading.setFont(new Font("Serif", Font.PLAIN, 80));
        heading.setForeground(Color.WHITE); 
        panel.add(heading, c);

        long numberOfFiles = saveGame.getNumberOfFiles(); 
        
        c.gridy++;
        JButton game1 = new JButton("Game 1");
        game1.setPreferredSize(new Dimension(80, game1.getPreferredSize().height));
        game1.addActionListener(e -> {
            if(numberOfFiles >= 1) {
                saveGame.loadGame("savedGames/game_1.txt"); 
                playerUsernames = saveGame.getPlayerUsernames();
                startGame(); 
            }
        });
        if(numberOfFiles < 1) game1.setEnabled(false);
        panel.add(game1, c);

        c.gridy++;
        JButton game2 = new JButton("Game 2");
        game2.setPreferredSize(new Dimension(80, game2.getPreferredSize().height));
        game2.addActionListener(e -> {
            if(numberOfFiles >= 2) {
                saveGame.loadGame("savedGames/game_2.txt"); 
                playerUsernames = saveGame.getPlayerUsernames();
                startGame(); 
            }
        });
        if(numberOfFiles < 2) game2.setEnabled(false);
        panel.add(game2, c);

        c.gridy++;
        JButton game3 = new JButton("Game 3");
        game3.setPreferredSize(new Dimension(80, game3.getPreferredSize().height));
        game3.addActionListener(e -> {
            if(numberOfFiles == 3) {
                saveGame.loadGame("savedGames/game_3.txt"); 
                playerUsernames = saveGame.getPlayerUsernames();
                startGame(); 
            }
        });
        if(numberOfFiles < 3) game3.setEnabled(false);
        panel.add(game3, c);
        addBackButton(panel, c); 
        initScreen(panel); 
    }
    
    /**
     * Displays the high scores.
     */
    private void displayHighScores() {
    	logic = new Logic();
    	saveGame = new SaveGame(logic);
    	String filePath = "highscores/scores.txt";
    	saveGame.processHighScores(filePath);
    	
    	JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(21, 88, 67)); 

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        
        c.gridy = 0;
        JLabel heading = new JLabel("High Scores");
        heading.setFont(new Font("Serif", Font.PLAIN, 40));
        heading.setForeground(Color.WHITE); 
        panel.add(heading, c);

        c.gridy++;
        JTextArea highScoresArea = new JTextArea();
        highScoresArea.setEditable(false);
        highScoresArea.setLineWrap(true);
        highScoresArea.setWrapStyleWord(true);
        highScoresArea.setPreferredSize(new Dimension(350, 400));
        highScoresArea.setBackground(new Color(21, 88, 67)); 
        highScoresArea.setForeground(Color.WHITE);
        highScoresArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
        
        String[] playerNames = saveGame.getPlayerNames(filePath);
        int[] playerScores = saveGame.getPlayerScores(filePath);

        StringBuilder highScoresText = new StringBuilder();
        highScoresText.append(String.format("%-20s %s%n", "Username:", "Score:"));
        for (int i = 0; i < playerNames.length; i++) 
            highScoresText.append(String.format("%-20s %d%n", playerNames[i], playerScores[i]));

        highScoresArea.setText(highScoresText.toString());
        panel.add(highScoresArea, c);

        c.gridy++;
        addBackButton(panel, c);
        initScreen(panel);
    }

    /**
     * Update the JFrame to display the given panel.
     * 
     * @param panel The JPanel to be displayed.
     */
    private void initScreen(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    /**
     * Add a back button to the given panel with the provided GridBagConstraints.
     * 
     * @param panel The JPanel to add the back button to.
     * @param c The GridBagConstraints used for positioning.
     */
    public void addBackButton(JPanel panel, GridBagConstraints c) {
        c.gridy++;
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(80, backButton.getPreferredSize().height));
        backButton.addActionListener(e -> startScreen());
        panel.add(backButton, c);
    }

    /**
     * Start a classic game with a default set of player usernames.
     */
    private void startClassicGame() {
        playerUsernames = new String[]{"User", "Computer"};
        logic = new Logic(playerUsernames);
        saveGame = new SaveGame(logic); 
        startGame(); 
    }
    
    /**
     * Start a multiplayer game by prompting the user to choose the number of players.
     */
    private void startMultiplayerGame() {        
        chooseNumberOfPlayers(); 
    }

	/**
	 * Displays a screen for the user to choose the number of players.
	 */
	private void chooseNumberOfPlayers() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(21, 88, 67)); 

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;

		c.gridy = 0;
		JLabel heading = new JLabel("Choose Number of Players");
		heading.setFont(new Font("Serif", Font.PLAIN, 40));
		heading.setForeground(Color.WHITE); 
		panel.add(heading, c);

		c.gridy++;
		JLabel instruction = new JLabel("Enter the number of players (2-10):");
		instruction.setFont(new Font("Serif", Font.PLAIN, 20));
		instruction.setForeground(Color.WHITE); 
		panel.add(instruction, c);

		c.gridy++;
		JTextField playerField = new JTextField(10);
		panel.add(playerField, c);

		c.gridy++;
		JButton submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(80, submitButton.getPreferredSize().height));
		submitButton.addActionListener(e -> {
			String text = playerField.getText().trim();
			try {
				int numberOfPlayers = Integer.parseInt(text);
				if (numberOfPlayers >= 2 && numberOfPlayers <= 10) {
					setupPlayers(numberOfPlayers);
				} else {
					JOptionPane.showMessageDialog(frame, "Please enter a number between 2 and 10.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		panel.add(submitButton, c);

		addBackButton(panel, c);
		initScreen(panel);
	}

	/**
	 * Displays a screen for entering player names based on the number of players.
	 * 
	 * @param numberOfPlayers The number of players in the game.
	 */
	private void setupPlayers(int numberOfPlayers) {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(21, 88, 67)); 

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;

		c.gridy = 0;
		JLabel heading = new JLabel("Enter Player Names");
		heading.setFont(new Font("Serif", Font.PLAIN, 40));
		heading.setForeground(Color.WHITE); 
		panel.add(heading, c);

		c.gridy++;
		JPanel namePanel = new JPanel(new GridBagLayout());
		namePanel.setBackground(new Color(21, 88, 67)); 

		GridBagConstraints nameConstraints = new GridBagConstraints();
		nameConstraints.insets = new Insets(10, 10, 10, 10);
		nameConstraints.anchor = GridBagConstraints.WEST;
		nameConstraints.fill = GridBagConstraints.HORIZONTAL;
		nameConstraints.gridx = 0;

		JTextField[] nameFields = new JTextField[numberOfPlayers];

		for (int i = 0; i < numberOfPlayers; i++) {
			JLabel label = new JLabel("Player " + (i + 1) + ":");
			label.setForeground(Color.WHITE);
			nameFields[i] = new JTextField(20);
			
			nameConstraints.gridy = i;
			nameConstraints.gridx = 0;
			namePanel.add(label, nameConstraints);
			
			nameConstraints.gridx = 1;
			namePanel.add(nameFields[i], nameConstraints);
		}

		panel.add(namePanel, c);

		c.gridy++;
		JButton submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(80, submitButton.getPreferredSize().height));
		submitButton.addActionListener(e -> {
			String[] enteredNames = new String[numberOfPlayers];
			boolean hasDuplicates = false;
			
			for (int i = 0; i < numberOfPlayers; i++) {
				String name = nameFields[i].getText().trim();
				if (name.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Player names cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				for (int j = 0; j < i; j++) {
					if (name.equals(enteredNames[j])) {
						hasDuplicates = true;
						break;
					}
				}
				if (hasDuplicates) break;
				enteredNames[i] = name;
			}
			
			if (hasDuplicates) {
				JOptionPane.showMessageDialog(panel, "There are repeating usernames. Please change them.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				playerUsernames = new String[numberOfPlayers];
				for (int i = 0; i < numberOfPlayers; i++) 
					playerUsernames[i] = nameFields[i].getText().trim();

				logic = new Logic(playerUsernames);
				saveGame = new SaveGame(logic);
				startGame();
			}
		});
		panel.add(submitButton, c);

		addBackButton(panel, c);
		initScreen(panel);
	}

	/**
	 * Starts the game by initializing the GamePanel and displaying it.
	 */
	private void startGame() {   
		gamePanel = new GamePanel(this, logic, saveGame, playerUsernames);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		addBackButton(gamePanel, c);
		initScreen(gamePanel);          
	}
}
