package dice;

import java.util.Random;

/**
 * Represents a dice in the game. The Dice class manages the value of the dice and its saved state.
 */
public class Dice {
    private enum Value { ONE, TWO, THREE, FOUR, FIVE, SIX; }
    private Value v;
    private boolean isSaved;

    // Private constructor to prevent direct instantiation without parameters.
    private Dice() {}

    /**
     * Constructor to initialize a dice with a specific value and saved state.
     * @param value The initial value of the dice (1 through 6).
     * @param isSaved1 The initial saved state of the dice.
     */
    public Dice(int value, boolean isSaved1) {
        setValue(value); 
        isSaved = isSaved1;
    }

    /**
     * Gets the current value of the dice.
     * @return The value of the dice (1 through 6).
     */
    public int getValue() { 
        return v.ordinal() + 1; 
    }

    /**
     * Sets the value of the dice.
     * @param value The new value to set (1 through 6).
     * @throws IllegalArgumentException If the value is not between 1 and 6.
     */
    public void setValue(int value) { 
        if(value < 1 || value > 6)
            throw new IllegalArgumentException("Invalid value: must be between 1 and 6."); 
        v = Value.values()[value - 1]; 
    }

    /**
     * Gets the saved state of the dice.
     * @return True if the dice is saved; otherwise, false.
     */
    public boolean getIsSaved() { 
        return isSaved;
    }

    /**
     * Sets the saved state of the dice.
     * @param isSaved1 The new saved state to set.
     */
    public void setIsSaved(boolean isSaved1) { 
        isSaved = isSaved1; 
    }

    /**
     * Rolls the dice by generating a random value between 1 and 6.
     */
    public void throwDice() {
        Random random = new Random();
        int randomNumber = random.nextInt(6) + 1; 
        setValue(randomNumber); 
    }
}
