package score;

/**
 * Represents the score for a Yahtzee game. Includes scores for various categories and methods to access and modify them.
 */
public class Score {
    String player;
    
    private int aces;
    private int twos;
    private int threes;
    private int fours;
    private int fives;
    private int sixes;
    private int totalWithoutBonus;
    private int bonus;
    private int finalUpperTotal;
    private int threeOfAKind;
    private int fourOfAKind;
    private int fullHouse;
    private int smallStraight;
    private int largeStraight;
    private int yahtzee;
    private int chance;
    private int finalLowerTotal;
    private int finalTotal;

    /**
     * Default constructor initializing the player to an empty string.
     */
    public Score() {
        this("");
    }

    /**
     * Constructor initializing the player with the provided name.
     * 
     * @param player1 Username of the player.
     */
    public Score(String player1) {
        player = player1;
        init();
    }

    /**
     * Sets the score for all categories.
     * 
     * @param score An array containing scores for each category.
     */
    public void setScore(int[] score) {
        aces = score[0];
        twos = score[1];
        threes = score[2];
        fours = score[3];
        fives = score[4];
        sixes = score[5];
        totalWithoutBonus = score[6];
        bonus = score[7];
        finalUpperTotal = score[8];
        threeOfAKind = score[9];
        fourOfAKind = score[10];
        fullHouse = score[11];
        smallStraight = score[12];
        largeStraight = score[13];
        yahtzee = score[14];
        chance = score[14];
        finalLowerTotal = score[15];
        finalTotal = score[16];
    }

    /**
     * Initializes all scores to -1, indicating they have not been set.
     */
    private void init() {
        aces = -1;
        twos = -1;
        threes = -1;
        fours = -1;
        fives = -1;
        sixes = -1;
        totalWithoutBonus = -1;
        bonus = -1;
        finalUpperTotal = -1;
        threeOfAKind = -1;
        fourOfAKind = -1;
        fullHouse = -1;
        smallStraight = -1;
        largeStraight = -1;
        yahtzee = -1;
        chance = -1;
        finalLowerTotal = -1;
        finalTotal = -1;
    }

	/**
	 * Getter for the private variable player.
	 * 
	 * @return the name of the player.
	 */
    public String getPlayer() { return player; }
    
    /**
     * Sets the player's name if it has not already been set.
     * 
     * @param player1 Name of the player.
     * @throws IllegalArgumentException if the name is null or empty, or if it is being changed after being set.
     */
    public void setPlayer(String player1) {
        if (getPlayer().isEmpty()) {
            if (player1 == null || player1.isEmpty()) {
                throw new IllegalArgumentException("Invalid input: name must be non-null and not empty.");
            }
            player = player1;
        } else {
            throw new IllegalArgumentException("Invalid input: The name for 'Player' has already been set and cannot be changed.");
        }
    }

	/**
	 * Getter for the private variable aces.
	 * 
	 * @return the score for 'Aces'.
	 */
	public int getAces() { return aces; }
    
    /**
     * Sets the score for 'Aces' if it has not already been set.
     * 
     * @param aces1 Score for 'Aces'.
     * @throws IllegalArgumentException if the score has already been set.
     */
    public void setAces(int aces1) {
        if (getAces() == -1) {
            aces = aces1;
        } else {
            throw new IllegalArgumentException("Invalid input: The value for 'Aces' has already been set and cannot be changed.");
        }
    }

	/**
	 * Getter for the private variable twos.
	 * 
	 * @return the score for 'Twos'.
	 */
    public int getTwos() { return twos; }
    
    /**
     * Sets the score for 'Twos' if it has not already been set.
     * 
     * @param twos1 Score for 'Twos'.
     * @throws IllegalArgumentException if the score has already been set.
     */
    public void setTwos(int twos1) {
        if (getTwos() == -1) {
            twos = twos1;
        } else {
            throw new IllegalArgumentException("Invalid input: The value for 'Twos' has already been set and cannot be changed.");
        }
    }

	/**
	 * Getter for the private variable threes.
	 * 
	 * @return the score for 'Threes'.
	 */
    public int getThrees() { return threes; }
    
    /**
     * Sets the score for 'Threes' if it has not already been set.
     * 
     * @param threes1 Score for 'Threes'.
     * @throws IllegalArgumentException if the score has already been set.
     */
    public void setThrees(int threes1) {
        if (getThrees() == -1) {
            threes = threes1;
        } else {
            throw new IllegalArgumentException("Invalid input: The value for 'Threes' has already been set and cannot be changed.");
        }
    }

	/**
	 * Getter for the private variable fours.
	 * 
	 * @return the score for 'Fours'.
	 */
    public int getFours() { return fours; }
    
    /**
     * Sets the score for 'Fours' if it has not already been set.
     * 
     * @param fours1 Score for 'Fours'.
     * @throws IllegalArgumentException if the score has already been set.
     */
    public void setFours(int fours1) {
        if (getFours() == -1) {
            fours = fours1;
        } else {
            throw new IllegalArgumentException("Invalid input: The value for 'Fours' has already been set and cannot be changed.");
        }
    }

	/**
	 * Getter for the private variable fives.
	 * 
	 * @return the score for 'Fives'.
	 */
    public int getFives() { return fives; }
    
    /**
     * Sets the score for 'Fives' if it has not already been set.
     * 
     * @param fives1 Score for 'Fives'.
     * @throws IllegalArgumentException if the score has already been set.
     */
    public void setFives(int fives1) {
        if (getFives() == -1) {
            fives = fives1;
        } else {
            throw new IllegalArgumentException("Invalid input: The value for 'Fives' has already been set and cannot be changed.");
        }
    }

	/**
	 * Getter for the private variable sixes.
	 * 
	 * @return the score for 'Sixes'.
	 */
    public int getSixes() { return sixes; }
    
    /**
     * Sets the score for 'Sixes' if it has not already been set.
     * 
     * @param sixes1 Score for 'Sixes'.
     * @throws IllegalArgumentException if the score has already been set.
     */
    public void setSixes(int sixes1) {
        if (getSixes() == -1) {
            sixes = sixes1;
        } else {
            throw new IllegalArgumentException("Invalid input: The value for 'Sixes' has already been set and cannot be changed.");
        }
    }

    /**
     * Checks if the upper section of the score is filled.
     * 
     * @return true if all upper section scores are set; false otherwise.
     */
    public boolean isUpperSectionFilled() {
        return (getAces() != -1) && (getTwos() != -1) && (getThrees() != -1) && (getFours() != -1) && (getFives() != -1) && (getSixes() != -1);
    }

    /**
     * Calculates the total score for the upper section.
     * 
     * @return The sum of all upper section scores.
     */
    private int calculateUpperSectionTotal() {
        return getAces() + getTwos() + getThrees() + getFours() + getFives() + getSixes();
    }

	/**
	 * Getter for the private variable getTotalWithoutBonus.
	 * 
	 * @return the final total of upper section without bonus.
	 */
    public int getTotalWithoutBonus() { return totalWithoutBonus; }
    
    /**
     * Sets the total score without bonus if it has not already been set.
     * 
     * @throws IllegalArgumentException if the total has already been set.
     */
    public void setTotalWithoutBonus() {
        if (getTotalWithoutBonus() == -1) {
            totalWithoutBonus = calculateUpperSectionTotal();
        } else {
            throw new IllegalArgumentException("Invalid input: The value for 'Total without bonus' has already been set and cannot be changed.");
        }
    }

	/**
	 * Getter for the private variable bonus.
	 * 
	 * @return the bonus points.
	 */
	public int getBonus() { return bonus; }
    
    /**
     * Sets the bonus score if it has not already been set. A bonus is awarded if the total without bonus is 63 or more.
     * 
     * @throws IllegalArgumentException if the bonus has already been set.
     */
    public void setBonus() {
        if(getBonus() == -1) {
            if(getTotalWithoutBonus() >= 63) {
                bonus = 35;
            }
            bonus = -2;
        } else {
            throw new IllegalArgumentException("Invalid input: The value for 'Bonus' has already been set and cannot be changed.");
        }
    }

	/**
	 * Getter for the private variable finalUpperTotal.
	 * 
	 * @return the final total of upper section.
	 */
    public int getFinalUpperTotal() { return finalUpperTotal; }
    
    /**
     * Sets the final upper section total if it has not already been set.
     * 
     * @throws IllegalArgumentException if the final upper total has already been set.
     */
    public void setFinalUpperTotal() {
        if (getFinalUpperTotal() == -1) {
            int bonus = getBonus();
            if (bonus == -2) {
                finalUpperTotal = getTotalWithoutBonus();
            } else {
                finalUpperTotal = getTotalWithoutBonus() + bonus;
            }
        } else {
            throw new IllegalArgumentException("Invalid input: The value for 'Final upper section total' has already been set and cannot be changed.");
        }
    }

	/**
	 * Retrieves the score for the 'Three of a Kind' category.
	 * 
	 * @return the score for 'Three of a Kind'.
	 */
	public int getThreeOfAKind() { return threeOfAKind; }

    /**
     * Sets the score for 'Three of a Kind' if it has not already been set.
     * 
     * @param threeOfAKind1 Score for 'Three of a Kind'.
     * @throws IllegalArgumentException if the score has already been set.
     */
    public void setThreeOfAKind(int threeOfAKind1) {
        if (getThreeOfAKind() == -1) {
            threeOfAKind = threeOfAKind1;
        } else {
            throw new IllegalArgumentException("Invalid input: The value for 'Three of a kind' has already been set and cannot be changed.");
        }
    }

	/**
	 * Retrieves the score for the 'Four of a Kind' category.
	 * 
	 * @return the score for 'Four of a Kind'.
	 */
    public int getFourOfAKind() { return fourOfAKind; }
    
    /**
     * Sets the score for 'Four of a Kind' if it has not already been set.
     * 
     * @param fourOfAKind1 Score for 'Four of a Kind'.
     * @throws IllegalArgumentException if the score has already been set.
     */
    public void setFourOfAKind(int fourOfAKind1) {
        if (getFourOfAKind() == -1) {
            fourOfAKind = fourOfAKind1;
        } else {
            throw new IllegalArgumentException("Invalid input: The value for 'Four of a kind' has already been set and cannot be changed.");
        }
    }

	/**
	 * Retrieves the score for the 'Full House' category.
	 * 
	 * @return the score for 'Full House'.
	 */
    public int getFullHouse() { return fullHouse; }
    
    /**
     * Sets the score for 'Full House' if it has not already been set.
     * 
     * @param fullHouse1 Score for 'Full House'. 25 points for a full house, 0 for not a full house.
     * @throws IllegalArgumentException if the score has already been set.
     */
    public void setFullHouse(int fullHouse1) {
        if (getFullHouse() == -1) {
            if (fullHouse1 == 0) {
                fullHouse = 0;
            } else {
                fullHouse = 25;
            }
        } else {
            throw new IllegalArgumentException("Invalid input: The value for 'Full house' has already been set and cannot be changed.");
        }
    }
    
	/**
	 * Retrieves the score for the 'Small Straight' category.
	 * 
	 * @return the score for 'Small Straight'. Returns 0 if not set, otherwise 30.
	 */
	public int getSmallStraight() { return smallStraight; }

	/**
	 * Sets the score for the 'Small Straight' category if it has not already been set.
	 * 
	 * @param smallStraight1 the score for 'Small Straight'. Valid values are 0 or 30.
	 * @throws IllegalArgumentException if the score has already been set.
	 */
	public void setSmallStraight(int smallStraight1) {
		if (getSmallStraight() == -1) {
			if (smallStraight1 == 0)
				smallStraight = 0;
			else
				smallStraight = 30;
		} else {
			throw new IllegalArgumentException("Invalid input: The value for 'Small straight' has already been set and cannot be changed.");
		}
	}

	/**
	 * Retrieves the score for the 'Large Straight' category.
	 * 
	 * @return the score for 'Large Straight'. Returns 0 if not set, otherwise 40.
	 */
	public int getLargeStraight() { return largeStraight; }

	/**
	 * Sets the score for the 'Large Straight' category if it has not already been set.
	 * 
	 * @param largeStraight1 the score for 'Large Straight'. Valid values are 0 or 40.
	 * @throws IllegalArgumentException if the score has already been set.
	 */
	public void setLargeStraight(int largeStraight1) {
		if (getLargeStraight() == -1) {
			if (largeStraight1 == 0)
				largeStraight = 0;
			else
				largeStraight = 40;
		} else {
			throw new IllegalArgumentException("Invalid input: The value for 'Large straight' has already been set and cannot be changed.");
		}
	}

	/**
	 * Retrieves the score for the 'Yahtzee' category.
	 * 
	 * @return the score for 'Yahtzee'. Returns 0 if not set or 50 for the first Yahtzee, and 100 for additional Yahtzees.
	 */
	public int getYahtzee() { return yahtzee; }

	/**
	 * Sets the score for the 'Yahtzee' category. Adds 100 points for additional Yahtzees after the first.
	 * 
	 * @param yahtzee1 the score for 'Yahtzee'. Must be 50 for the first Yahtzee or 0 for no Yahtzee.
	 * @throws IllegalArgumentException if the score is invalid.
	 */
	public void setYahtzee(int yahtzee1) {
		if (yahtzee1 == 50) {
			if (getYahtzee() == -1) 
				yahtzee = 50;
			else 
				yahtzee += 100;
		} else {
			yahtzee = 0;
		}
	}

	/**
	 * Retrieves the score for the 'Chance' category.
	 * 
	 * @return the score for 'Chance'.
	 */
	public int getChance() { return chance; }

	/**
	 * Sets the score for the 'Chance' category if it has not already been set.
	 * 
	 * @param chance1 the score for 'Chance'.
	 * @throws IllegalArgumentException if the score has already been set.
	 */
	public void setChance(int chance1) {
		if (getChance() == -1) 
			chance = chance1;
		else 
			throw new IllegalArgumentException("Invalid input: The value for 'Chance' has already been set and cannot be changed.");
	}

	/**
	 * Checks if all fields in the lower section have been filled.
	 * 
	 * @return true if all fields in the lower section are filled, false otherwise.
	 */
	public boolean isLowerSectionFilled() {
		return (getThreeOfAKind() != -1) && (getFourOfAKind() != -1) && (getFullHouse() != -1) &&
			(getSmallStraight() != -1) && (getLargeStraight() != -1) && (getChance() != -1) &&
			(getYahtzee() != -1);
	}

	/**
	 * Calculates the total score for the lower section by summing up all individual category scores.
	 * 
	 * @return the total score for the lower section.
	 */
	private int calculateLowerSectionTotal() {
		return getThreeOfAKind() + getFourOfAKind() + getFullHouse() +
			getSmallStraight() + getLargeStraight() + getChance() + getYahtzee();
	}

	/**
	 * Retrieves the final total score for the lower section.
	 * 
	 * @return the final total score for the lower section.
	 */
	public int getFinalLowerTotal() { return finalLowerTotal; }

	/**
	 * Sets the final total score for the lower section if it has not already been set.
	 * 
	 * @throws IllegalArgumentException if the final lower total has already been set.
	 */
	public void setFinalLowerTotal() { 
		if (getFinalLowerTotal() == -1) {
			finalLowerTotal = calculateLowerSectionTotal(); 
		} else {
			throw new IllegalArgumentException("Invalid input: The value for 'Final lower section total' has already been set and cannot be changed.");
		}
	}

	/**
	 * Retrieves the final total score for the entire game, combining both the upper and lower sections.
	 * 
	 * @return the final total score for the entire game.
	 */
	public int getFinalTotal() { return finalTotal; }

	/**
	 * Sets the final total score for the entire game if it has not already been set.
	 * 
	 * @throws IllegalArgumentException if the final total has already been set.
	 */
	public void setFinalTotal() { 
		if (getFinalTotal() == -1) 
			finalTotal = getFinalUpperTotal() + getFinalLowerTotal();
		else 
			throw new IllegalArgumentException("Invalid input: The value for 'Final total' has already been set and cannot be changed.");
	}

	/**
	 * Returns an array indicating which fields have not yet been filled.
	 * 
	 * @return an array where each element corresponds to a specific field; a value of 1 indicates the field is empty.
	 */
	public int[] emptyFields() {
		int[] emptyFields = new int[18];
		
		if (aces == -1) emptyFields[0] = 1;
		if (twos == -1) emptyFields[1] = 1;
		if (threes == -1) emptyFields[2] = 1;
		if (fours == -1) emptyFields[3] = 1;
		if (fives == -1) emptyFields[4] = 1;
		if (sixes == -1) emptyFields[5] = 1;
		if (totalWithoutBonus == -1) emptyFields[6] = 1;
		if (bonus == -1) emptyFields[7] = 1;
		if (finalUpperTotal == -1) emptyFields[8] = 1;
		if (threeOfAKind == -1) emptyFields[9] = 1;
		if (fourOfAKind == -1) emptyFields[10] = 1;
		if (fullHouse == -1) emptyFields[11] = 1;
		if (smallStraight == -1) emptyFields[12] = 1;
		if (largeStraight == -1) emptyFields[13] = 1;
		if (yahtzee == -1) emptyFields[14] = 1;
		if (chance == -1) emptyFields[15] = 1;
		if (finalLowerTotal == -1) emptyFields[16] = 1;
		if (finalTotal == -1) emptyFields[17] = 1;

		return emptyFields;
	}

	/**
	 * Checks if all fields in the scorecard are filled.
	 * 
	 * @return true if all fields are filled, false otherwise.
	 */
	public boolean areFieldsFilled() {
		int[] fieldValues = getFieldValues();
		
		for (int value : fieldValues) 
			if (value == -1) 
				return false; 
		return true; 
	}

	/**
	 * Returns an array of field names for display or processing purposes.
	 * 
	 * @return an array of field names as strings.
	 */
	public String[] getFieldNames() {
		return new String[] {
			"Aces", "Twos", "Threes", "Fours", "Fives", "Sixes",
			"Total Without Bonus", "Bonus", "Final Upper Total",
			"Three of a Kind", "Four of a Kind", "Full House",
			"Small Straight", "Large Straight", "Yahtzee", "Chance",
			"Final Lower Total", "Final Total"
		};
	}

	/**
	 * Returns an array of field values for display or processing purposes.
	 * 
	 * @return an array of field values as integers.
	 */
	public int[] getFieldValues() {
		return new int[] {
			getAces(), getTwos(), getThrees(), getFours(), getFives(), getSixes(),
			getTotalWithoutBonus(), getBonus(), getFinalUpperTotal(),
			getThreeOfAKind(), getFourOfAKind(), getFullHouse(),
			getSmallStraight(), getLargeStraight(), getYahtzee(), getChance(), 
			getFinalLowerTotal(), getFinalTotal()
		};
	}
}