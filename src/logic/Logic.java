package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dice.Dice;
import score.Score;

/**
 * Handles the game logic for a Yahtzee game, including dice operations and score calculations.
 */
public class Logic {
    private Dice dice_1;
    private Dice dice_2;
    private Dice dice_3;
    private Dice dice_4;
    private Dice dice_5;
    
    private int numberOfPlayers;
    private Score[] table;
    
    /**
     * Default constructor initializing with default players: "User" and "Computer".
     */
    public Logic() { this(new String[]{"User", "Computer"}); }
    
    /**
     * Constructor initializing the game with a specified list of players.
     * 
     * @param players Array of player names.
     */
    public Logic(String[] players) {
        numberOfPlayers = players.length;
        table = new Score[numberOfPlayers];
        
        for(int i = 0; i < numberOfPlayers; i++) {
            table[i] = new Score();
            getScoreAt(i).setPlayer(players[i]);    
        }
        
        init();
    } 
    
    /**
     * Returns the number of players in the game.
     * 
     * @return Number of players.
     */
    public int getNumberOfPlayers() { return numberOfPlayers; }
    
    /**
     * Initializes the dice objects for the game.
     */
    private void init() {
        dice_1 = new Dice(1, false);
        dice_2 = new Dice(1, false);
        dice_3 = new Dice(1, false);
        dice_4 = new Dice(1, false);
        dice_5 = new Dice(1, false);
    }

    /**
     * Retrieves the Score object for a given player index.
     * 
     * @param i Index of the player.
     * @return The Score object for the player.
     * @throws IllegalArgumentException if the index is out of range.
     */
    private Score getScoreAt(int i) {
        if(i < 0 || i >= numberOfPlayers) 
            throw new IllegalArgumentException("Invalid input: Index out of range.");
        return table[i];
    }
    
    /**
     * Retrieves the score for all categories for a specific player.
     * 
     * @param i Index of the player.
     * @return Array of scores for the player.
     */
    public int[] getPlayerScore(int i) {
        Score player = getScoreAt(i);
        return player.getFieldValues();
    }

    /**
     * Creates an array that counts the number of times each dice value appears.
     * 
     * @return Array of counts for dice values (1-6).
     */
    private int[] createDiceValueRepeatingArray() {
        int[] counts = new int[6]; 
        counts[dice_1.getValue() - 1]++;
        counts[dice_2.getValue() - 1]++;
        counts[dice_3.getValue() - 1]++;
        counts[dice_4.getValue() - 1]++;
        counts[dice_5.getValue() - 1]++;
        return counts; 
    }
    
    /**
     * Calculates the score for 'Aces' based on the dice values.
     * 
     * @return Score for 'Aces'.
     */
    private int calculateAces() { 
        int[] counts = createDiceValueRepeatingArray();
        return counts[0]; 
    }

    /**
     * Calculates the score for 'Twos' based on the dice values.
     * 
     * @return Score for 'Twos'.
     */
    private int calculateTwos() { 
        int[] counts = createDiceValueRepeatingArray();
        return 2 * counts[1]; 
    }

    /**
     * Calculates the score for 'Threes' based on the dice values.
     * 
     * @return Score for 'Threes'.
     */
    private int calculateThrees() { 
        int[] counts = createDiceValueRepeatingArray();
        return 3 * counts[2]; 
    }

    /**
     * Calculates the score for 'Fours' based on the dice values.
     * 
     * @return Score for 'Fours'.
     */
    private int calculateFours() { 
        int[] counts = createDiceValueRepeatingArray();
        return 4 * counts[3]; 
    }

    /**
     * Calculates the score for 'Fives' based on the dice values.
     * 
     * @return Score for 'Fives'.
     */
    private int calculateFives() { 
        int[] counts = createDiceValueRepeatingArray();
        return 5 * counts[4]; 
    }

    /**
     * Calculates the score for 'Sixes' based on the dice values.
     * 
     * @return Score for 'Sixes'.
     */
    private int calculateSixes() { 
        int[] counts = createDiceValueRepeatingArray();
        return 6 * counts[5]; 
    }
    
    /**
     * Creates an array of the current values of all dice.
     * 
     * @return Array of dice values.
     */
    private int[] createDiceValueArray() {
        int[] dices = new int[5];
        dices[0] = dice_1.getValue();
        dices[1] = dice_2.getValue();
        dices[2] = dice_3.getValue();
        dices[3] = dice_4.getValue();
        dices[4] = dice_5.getValue();
        return dices; 
    }
    
    /**
     * Calculates the score for 'X of a Kind' excluding a specific dice value.
     * 
     * @param x The number of matching dice required (3, 4, etc.).
     * @param j The index of the dice value to exclude (-1 for none).
     * @return Array containing the dice value and the calculated score.
     * @throws IllegalArgumentException if the parameters are out of range.
     */
    private int[] calculateXOfAKindWithoutOne(int x, int j) {
        if(x < 1 || x > 5 || j < -1 || j > 5) 
            throw new IllegalArgumentException("Invalid input: The value for 'Number of dices' must be between 1 and 5.");
        
        int[] counts = createDiceValueRepeatingArray();
        for(int i = 0; i < counts.length; i++) 
            if(i != j && counts[i] >= x) {
                int totalScore = calculateChance();
                return new int[]{i, totalScore};
            }
        
        return new int[]{-1, 0};
    }
    
    /**
     * Calculates the score for 'X of a Kind'.
     * 
     * @param x The number of matching dice required (3, 4, etc.).
     * @return Score for 'X of a Kind'.
     */
    private int calculateXOfAKind(int x) {
        int[] score = calculateXOfAKindWithoutOne(x, -1);
        return score[1];
    }
  
    /**
     * Calculates the score for 'Three of a Kind'.
     * 
     * @return Score for 'Three of a Kind'.
     */
    private int calculateThreeOfAKind() { return calculateXOfAKind(3); }

    /**
     * Calculates the score for 'Four of a Kind'.
     * 
     * @return Score for 'Four of a Kind'.
     */
    private int calculateFourOfAKind() { return calculateXOfAKind(4); }
    
    /**
     * Calculates the score for a 'Full House'.
     * 
     * @return Score for 'Full House', 25 if valid, otherwise 0.
     */
    private int calculateFullHouse() {
        int[] checkThree = calculateXOfAKindWithoutOne(3, -1);
        boolean threeOfAKind = (checkThree[0] != -1);
        if(threeOfAKind) {
            int[] checkTwo = calculateXOfAKindWithoutOne(2, checkThree[0]);
            boolean twoOfAKind = checkTwo[1] != 0;
            if(twoOfAKind) 
                return 25;
        }
        return 0;
    }

    /**
     * Calculates the number of consecutive dice values.
     * 
     * @return The maximum number of consecutive dice values.
     */
    private int calculateNumberOfConsecutives() {
        int[] diceValues = createDiceValueArray();
        Arrays.sort(diceValues);        
        
        int currentConsecutives = 1;
        int maxConsecutives = 1;

        for(int i = 1; i < diceValues.length; i++) {
            if (diceValues[i] == diceValues[i - 1] + 1)
                currentConsecutives++;
            else if(diceValues[i] != diceValues[i - 1]) {
                maxConsecutives = Math.max(maxConsecutives, currentConsecutives);
                currentConsecutives = 1;
            }
        }

        maxConsecutives = Math.max(maxConsecutives, currentConsecutives);
        return maxConsecutives;
    }

    /**
     * Calculates the score for a 'Small Straight'.
     * 
     * @return Score for 'Small Straight', 30 if valid, otherwise 0.
     */
    private int calculateSmallStraight() {
        int consecutives = calculateNumberOfConsecutives();
        if(consecutives >= 4) 
            return 30;
        return 0;
    }

    /**
     * Calculates the score for a 'Large Straight'.
     * 
     * @return Score for 'Large Straight', 40 if valid, otherwise 0.
     */
    private int calculateLargeStraight() {
        int consecutives = calculateNumberOfConsecutives();
        if(consecutives == 5)
            return 40;
        return 0;
    }

    /**
     * Calculates the score for a 'Yahtzee'.
     * 
     * @return Score for 'Yahtzee', 50 if all dice have the same value, otherwise 0.
     */
    private int calculateYahtzee() {
        int value = dice_1.getValue();
        if((value == dice_2.getValue()) && (value == dice_3.getValue()) && (value == dice_4.getValue()) && (value == dice_5.getValue()))
            return 50;
        return 0;
    }

    /**
     * Calculates the score for 'Chance', which is the sum of all dice values.
     * 
     * @return Score for 'Chance'.
     */
    private int calculateChance() {
        return dice_1.getValue() + dice_2.getValue() + dice_3.getValue() + dice_4.getValue() + dice_5.getValue();
    }
    
    /**
     * Calculates the score for all categories for a specific player and updates the player's score.
     * 
     * @param playerIndex Index of the player whose score is to be calculated.
     * @return Array of calculated scores.
     */
    public int[] calculateScore(int playerIndex) {
        Score playerScore = getScoreAt(playerIndex);
    
        int aces = playerScore.getAces();
        int twos = playerScore.getTwos();
        int threes = playerScore.getThrees(); 
        int fours = playerScore.getFours();   
        int fives = playerScore.getFives();    
        int sixes = playerScore.getSixes();    
        int threeOfAKind = playerScore.getThreeOfAKind(); 
        int fourOfAKind = playerScore.getFourOfAKind();   
        int fullHouse = playerScore.getFullHouse();       
        int smallStraight = playerScore.getSmallStraight(); 
        int largeStraight = playerScore.getLargeStraight(); 
        int chance = playerScore.getChance();             
        int yahtzee = playerScore.getYahtzee();           

        int totalWithoutBonus = playerScore.getTotalWithoutBonus(); 
        int bonus = playerScore.getBonus();                     
        int finalUpperTotal = playerScore.getFinalUpperTotal();  
        int finalLowerTotal = playerScore.getFinalLowerTotal();  
        int finalTotal = playerScore.getFinalTotal();
        
        if(totalWithoutBonus == -1) totalWithoutBonus = 0;
        if(bonus == -1) bonus = 0;
        if(finalUpperTotal == -1) finalUpperTotal = 0;
        if(finalLowerTotal == -1) finalLowerTotal = 0;
        if(finalTotal == -1) finalTotal = 0;
            
        if(aces == -1) aces = calculateAces();
        if(twos == -1) twos = calculateTwos();
        if(threes == -1) threes = calculateThrees();
        if(fours == -1) fours = calculateFours();
        if(fives == -1) fives = calculateFives();
        if(sixes == -1) sixes = calculateSixes();

        if(threeOfAKind == -1) threeOfAKind = calculateThreeOfAKind();
        if(fourOfAKind == -1) fourOfAKind = calculateFourOfAKind();
        if(fullHouse == -1) fullHouse = calculateFullHouse();
        if(smallStraight == -1) smallStraight = calculateSmallStraight();
        if(largeStraight == -1) largeStraight = calculateLargeStraight();

        int skip = -1;
        // If yahtzee is set to 0, it can't later changed
        if(yahtzee != 0) {
            int yahtzeeCheck = calculateYahtzee();
            if(yahtzeeCheck == 50) {
                playerScore.setYahtzee(yahtzeeCheck);
                yahtzee = playerScore.getYahtzee();
                skip = 1;
            } else if(yahtzee == -1) yahtzee = 0; 
        }
        
        if(chance == -1) chance = calculateChance();

        return new int[] {
                aces, twos, threes, fours, fives, sixes,
                totalWithoutBonus, bonus, finalUpperTotal,
                threeOfAKind, fourOfAKind, fullHouse, smallStraight, largeStraight, yahtzee, chance,
                finalLowerTotal, finalTotal, skip
            };
    }

	/**
	 * Updates automatic score values for a specific player based on their current scores.
	 * It adjusts values like total without bonus, bonus, final upper total, final lower total,
	 * and final total if they are not already set.
	 * 
	 * @param playerIndex Index of the player whose scores are being updated.
	 */
	public void updateAutomaticValues(int playerIndex) {
		Score playerScore = getScoreAt(playerIndex);

		int totalWithoutBonus = playerScore.getTotalWithoutBonus(); 
		int bonus = playerScore.getBonus();                     
		int finalUpperTotal = playerScore.getFinalUpperTotal();  
		int finalLowerTotal = playerScore.getFinalLowerTotal();  
		int finalTotal = playerScore.getFinalTotal();

		if(totalWithoutBonus == -1) {
			boolean isUpperSectionFilled = playerScore.isUpperSectionFilled();
			if(isUpperSectionFilled) {
				playerScore.setTotalWithoutBonus();
				totalWithoutBonus = playerScore.getTotalWithoutBonus();
			} 
		}

		if(bonus == -1) {
			if(totalWithoutBonus != -1) {
				playerScore.setBonus();
				bonus = playerScore.getBonus();
			} 
		} 

		if(finalUpperTotal == -1) {
			if(totalWithoutBonus != -1) {
				playerScore.setFinalUpperTotal();
				finalUpperTotal = playerScore.getFinalUpperTotal();
			} 
		} 

		if(finalLowerTotal == -1) {
			boolean isLowerSectionFilled = playerScore.isLowerSectionFilled();
			if(isLowerSectionFilled) {
				playerScore.setFinalLowerTotal();
				finalLowerTotal = playerScore.getFinalLowerTotal();
			} 
		}

		if(finalTotal == -1) {
			if(finalLowerTotal != -1 && finalUpperTotal != -1) {
				playerScore.setFinalTotal();
				finalTotal = playerScore.getFinalTotal();
			} 
		} 
	}

	/**
	 * Throws the dice that are not marked as saved.
	 * Each die's value is updated only if it has not been saved by the player.
	 */
	public void throwDices() {
		if(!dice_1.getIsSaved())
			dice_1.throwDice(); 
		if(!dice_2.getIsSaved())
			dice_2.throwDice(); 
		if(!dice_3.getIsSaved())
			dice_3.throwDice();
		if(!dice_4.getIsSaved())
			dice_4.throwDice();
		if(!dice_5.getIsSaved())
			dice_5.throwDice();
	} 

	/**
	 * Retrieves the current values of all dice.
	 * 
	 * @return An array of integers representing the values of the five dice.
	 */
	public int[] getDices() {
		int[] dicesValues = new int[5];
		
		dicesValues[0] = dice_1.getValue();
		dicesValues[1] = dice_2.getValue();
		dicesValues[2] = dice_3.getValue();
		dicesValues[3] = dice_4.getValue();
		dicesValues[4] = dice_5.getValue();
		
		return dicesValues;
	}

	/**
	 * Updates the save status of dice based on the chosen dice and the provided option.
	 * 
	 * @param chosenDices Array of dice indices to update.
	 * @param option String indicating whether to save or unsave the dice ("s" for save, other for unsave).
	 */
	public void processDices(int[] chosenDices, String option) {
		for(int i = 0; i < 5; i++) {
			if(option.equals("s")) {
				if(chosenDices[i] != 0) {
					if(chosenDices[i]  == 1) 
						dice_1.setIsSaved(true);
					else if(chosenDices[i] == 2) 
						dice_2.setIsSaved(true);
					else if(chosenDices[i] == 3) 
						dice_3.setIsSaved(true);
					else if(chosenDices[i] == 4)
						dice_4.setIsSaved(true);
					else dice_5.setIsSaved(true);
				}
			} else {
				if(chosenDices[i] != 0) {
					if(chosenDices[i] == 1)
						dice_1.setIsSaved(false);
					else if(chosenDices[i] == 2)
						dice_2.setIsSaved(false);
					else if(chosenDices[i] == 3)
						dice_3.setIsSaved(false);
					else if(chosenDices[i] == 4)
						dice_4.setIsSaved(false);
					else dice_5.setIsSaved(false);
				}
			}
		}
	}

	/**
	 * Checks if a specific die is saved based on its index.
	 * 
	 * @param index Index of the die to check.
	 * @return True if the die is saved, false otherwise.
	 * @throws IllegalArgumentException If the index is out of range.
	 */
	public boolean isDiceSaved(int index) {
		if(index < 0 || index > 4) 
			throw new IllegalArgumentException("Invalid input: Index out of range.");
		
		if(index == 0) return dice_1.getIsSaved();
		else if(index == 1) return dice_2.getIsSaved();
		else if(index == 2) return dice_3.getIsSaved();
		else if(index == 3) return dice_4.getIsSaved();
		else if(index == 4) return dice_5.getIsSaved();
		return false;
	}

	/**
	 * Marks a specific die as saved based on its index.
	 * 
	 * @param index Index of the die to be marked as saved.
	 * @throws IllegalArgumentException If the index is out of range.
	 */
	public void saveDice(int index) {
		if(index < 0 || index > 4) 
			throw new IllegalArgumentException("Invalid input: Index out of range.");
		
		if(index == 0) dice_1.setIsSaved(true);
		else if(index == 1)  dice_2.setIsSaved(true);
		else if(index == 2)  dice_3.setIsSaved(true);
		else if(index == 3)  dice_4.setIsSaved(true);
		else if(index == 4) dice_5.setIsSaved(true); 
	}

	/**
	 * Marks a specific die as unsaved based on its index.
	 * 
	 * @param index Index of the die to be marked as unsaved.
	 * @throws IllegalArgumentException If the index is out of range.
	 */
	public void unsaveDice(int index) {
		if(index < 0 || index > 4) 
			throw new IllegalArgumentException("Invalid input: Index out of range.");
		
		if(index == 0) dice_1.setIsSaved(false);
		else if(index == 1)  dice_2.setIsSaved(false);
		else if(index == 2)  dice_3.setIsSaved(false);
		else if(index == 3)  dice_4.setIsSaved(false);
		else if(index == 4) dice_5.setIsSaved(false);
	}

	/**
	 * Marks all dice as unsaved.
	 */
	public void unsaveDices() {
		dice_1.setIsSaved(false); 
		dice_2.setIsSaved(false); 
		dice_3.setIsSaved(false);
		dice_4.setIsSaved(false);
		dice_5.setIsSaved(false);
	}

	/**
	 * Checks if the game is over by verifying if all players have filled all their fields.
	 * 
	 * @return True if the game is over, false otherwise.
	 */
	public boolean isGameOver() {
		for(int i = 0; i < numberOfPlayers; i++) 
			if(!getScoreAt(i).areFieldsFilled())
				return false;
		return true;     
	}

	/**
	 * Checks if a specific player has finished their game by verifying if all their fields are filled.
	 * 
	 * @param playerIndex Index of the player to check.
	 * @return True if the player is done, false otherwise.
	 */
	public boolean isPlayerDone(int playerIndex) {
		if(getScoreAt(playerIndex).areFieldsFilled())
			return true;
		return false;     
	}

	/**
	 * Retrieves the names of all fields in the score table.
	 * 
	 * @return Array of field names.
	 */
	public String[] getFieldNames() {
		return table[0].getFieldNames();
	}

	/**
	 * Gets the indices of empty fields for a specific player.
	 * 
	 * @param playerIndex Index of the player.
	 * @return Array of indices of empty fields.
	 */
	public int[] getEmptyFields(int playerIndex) {
		Score playerScore = getScoreAt(playerIndex);      
		return playerScore.emptyFields();
	}

	/**
	 * Saves the score for a specific field and player.
	 * 
	 * @param playerIndex Index of the player.
	 * @param fieldIndex Index of the field to save the score in.
	 * @param scores Array of scores to be saved.
	 * @throws IllegalArgumentException If the fieldIndex is out of range.
	 */
	public void saveField(int playerIndex, int fieldIndex, int[] scores) {
		int n = scores.length;
		if(fieldIndex < 0 || fieldIndex > n-1) 
			throw new IllegalArgumentException("Invalid input: Index out of range.");

		Score player = getScoreAt(playerIndex);
		
		if(fieldIndex == 0)
	        player.setAces(scores[0]);
	    else if (fieldIndex == 1)
	    	player.setTwos(scores[1]);
	    else if (fieldIndex == 2)
	    	player.setThrees(scores[2]);
	    else if (fieldIndex == 3)
	    	player.setFours(scores[3]);
	    else if (fieldIndex == 4)
	    	player.setFives(scores[4]);
	    else if (fieldIndex == 5)
	    	player.setSixes(scores[5]);
	    else if (fieldIndex == 9)
	    	player.setThreeOfAKind(scores[9]);
	    else if (fieldIndex == 10)
	    	player.setFourOfAKind(scores[10]);
	    else if (fieldIndex == 11)
	    	player.setFullHouse(scores[11]);
	    else if (fieldIndex == 12)
	    	player.setSmallStraight(scores[12]);
	    else if (fieldIndex == 13)
	    	player.setLargeStraight(scores[13]);
	    else if (fieldIndex == 14)
	    	player.setYahtzee(scores[14]);
	    else if (fieldIndex == 15)
	    	player.setChance(scores[15]);
    }

	/**
	 * Determines the winner(s) of the game based on the highest final score.
	 * If there are multiple players with the highest score, their names are all included.
	 * 
	 * @return A string containing the names of the winner(s), separated by commas. 
	 *         Returns an empty string if no winners are found.
	 */
	public String getWinner() {
		List<String> winners = new ArrayList<>();
		int bestScore = -1;

		for(int i = 0; i < numberOfPlayers; i++) {
			Score playerScore = getScoreAt(i);
			int currentScore = playerScore.getFinalTotal();

			if(currentScore > bestScore) {
				bestScore = currentScore;
				winners.clear(); 
				winners.add(playerScore.getPlayer());
			} 
			else if (currentScore == bestScore) {
				winners.add(playerScore.getPlayer());
			}
		}

		if(!winners.isEmpty()) 
			return String.join(", ", winners); 
		return "";
	}

	/**
	 * Retrieves the names of all players participating in the game.
	 * 
	 * @return An array of strings where each element is the name of a player.
	 */
	public String[] getPlayers() {
		String[] players = new String[numberOfPlayers];
		
		for(int i = 0; i < numberOfPlayers; i++)
			players[i] = getScoreAt(i).getPlayer();
		return players;
	}

	/**
	 * Retrieves the scores for all players, organized by field.
	 * 
	 * @return A 2D array where each row represents a player and each column represents a field score.
	 */
	public int[][] getScores() {
		int[][] scores = new int[numberOfPlayers][];
		
		for(int i = 0; i < numberOfPlayers; i++)
			scores[i] = getScoreAt(i).getFieldValues();
		return scores;
	}

	/**
	 * Updates the scores for all players based on the provided 2D array.
	 * 
	 * @param scores A 2D array where each row contains the new scores for a player.
	 */
	public void setScores(int[][] scores) {     
		for(int i = 0; i < numberOfPlayers; i++) 
			getScoreAt(i).setScore(scores[i]);
	}

	/**
	 * Determines the index of the best field to score based on the current empty fields and scores.
	 * The function excludes certain fields that cannot be changed and selects the field with the highest score.
	 * 
	 * @param emptyFields An array indicating which fields are available for scoring.
	 * @param scores An array containing scores for each field.
	 * @return The index of the field with the highest score among the available fields.
	 */
	public int getBestIndex(int[] emptyFields, int[] scores) {
		int[] unchangeableFields = new int[]{6, 7, 8, 16, 17}; 
		int[] validIndexes = new int[emptyFields.length];
		int count = 0;

		for(int i = 0; i < emptyFields.length; i++) {
			if(emptyFields[i] == 1) {
				for(int unchangeableField : unchangeableFields) {
					if(i != unchangeableField) {
						validIndexes[count] = i;
						count++;
						break;
					}
				}
			}
		}

		validIndexes = Arrays.copyOf(validIndexes, count);

		int maxIndex = validIndexes[0];
		int maxValue = scores[validIndexes[0]];

		for(int i = 1; i < validIndexes.length; i++) 
			if(scores[validIndexes[i]] > maxValue) {
				maxValue = scores[validIndexes[i]];
				maxIndex = validIndexes[i];
			}

		return maxIndex;
	}
}
