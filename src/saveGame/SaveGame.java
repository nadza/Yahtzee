package saveGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import logic.Logic;
import score.Score;

/**
 * Manages the saving and loading of game state to and from a file.
 * This class handles the serialization of player usernames and scores.
 */
public class SaveGame {
    private Logic logic;
    String[] playerUsernames;

    /**
     * Gets the array of player usernames.
     * @return Array of player usernames.
     */
    public String[] getPlayerUsernames() { return playerUsernames; }  

    /**
     * Constructor for the SaveGame class.
     * @param logic1 An instance of the Logic class containing game data.
     */
    public SaveGame(Logic logic1) { logic = logic1; }

    /**
     * Saves the current game state to a specified file.
     * @param filePath The path to the file where the game state will be saved.
     */
    public void saveGame(String filePath) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            String[] players = logic.getPlayers();
            int[][] scores = logic.getScores();
            
            for(int i = 0; i < logic.getNumberOfPlayers(); i++) {
                writer.write(players[i] + "\n"); 
                for (int score : scores[i]) 
                    writer.write(score + " "); 
                writer.write("\n"); 
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    /**
     * Loads game state from a specified file.
     * @param filePath The path to the file from which the game state will be loaded.
     */
    public void loadGame(String filePath) {
        List<String> playerUsernamesList = new ArrayList<>();
        List<int[]> scoresList = new ArrayList<>();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while((line = reader.readLine()) != null) {
                String username = line.trim();
                playerUsernamesList.add(username);
                
                line = reader.readLine();
                String[] scoreStrings = line.trim().split(" ");
                int[] playerScores = new int[scoreStrings.length];
                
                for(int i = 0; i < scoreStrings.length; i++)
                    playerScores[i] = Integer.parseInt(scoreStrings[i]);
                
                scoresList.add(playerScores);
            }
            
            String[] playerUsernames1 = playerUsernamesList.toArray(new String[0]);
            int[][] scores = scoresList.toArray(new int[0][]);
            playerUsernames = playerUsernames1;
            logic = new Logic(playerUsernames1);
            logic.setScores(scores);      
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    /**
     * Counts the number of files in a specified folder.
     * @param folderPath The path to the folder to count files in.
     * @return The number of files in the folder.
     * @throws IOException If the folder does not exist or is not a directory.
     */
    public static long countFilesInFolder(Path folderPath) throws IOException {
        if(!Files.exists(folderPath) || !Files.isDirectory(folderPath)) 
            throw new IOException("The directory does not exist or is not a directory.");
        
        try(Stream<Path> paths = Files.walk(folderPath)) {
            return paths.filter(Files::isRegularFile).count();
        }
    }

    /**
     * Gets the number of files in the "savedGames" directory.
     * @return The number of files in the "savedGames" directory.
     */
    public long getNumberOfFiles() {
        Path directoryPath = Paths.get("savedGames/");
        try {
            long fileCount = countFilesInFolder(directoryPath);
            return fileCount;
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        return 0;
    }
    
    /**
     * Saves the final scores of all players to a specified file. 
     * 
     * @param filePath The path to the file where the scores will be saved.
     */
    public void saveFinalScores(String filePath) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String[] players = logic.getPlayers();
            int[][] scores = logic.getScores(); 
            
            for(int i = 0; i < players.length; i++) {
                writer.write(players[i] + "\n"); 
                int[] playerScore = scores[i];
                writer.write(playerScore[17] + " "); 
                writer.newLine(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Processes high scores by reading from a file, sorting the scores, and saving the sorted scores.
     * 
     * @param filePath The path to the file containing the high scores.
     */
    public void processHighScores(String filePath) {
        List<String> playersList = new ArrayList<>();
        List<Integer> scoresList = new ArrayList<>();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while((line = reader.readLine()) != null) {
                String username = line.trim();
                playersList.add(username);
                
                line = reader.readLine();
                String scoreString = line.trim();
                scoresList.add(Integer.valueOf(scoreString));
            }
            
            String[] playersArray = playersList.toArray(new String[0]);
            int[] scoresArray = scoresList.stream().mapToInt(i -> i).toArray();
            
            sortByScores(playersArray, scoresArray);
            
            saveSortedScores(filePath, playersArray, scoresArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sorts players and their scores in descending order based on scores.
     * 
     * @param players Array of player names.
     * @param scores Array of player scores corresponding to the names.
     */
    private void sortByScores(String[] players, int[] scores) {
        Integer[] indices = new Integer[scores.length];
        for (int i = 0; i < indices.length; i++) 
            indices[i] = i;

        Arrays.sort(indices, (i1, i2) -> Integer.compare(scores[i2], scores[i1]));

        String[] sortedPlayers = new String[players.length];
        int[] sortedScores = new int[scores.length];
        for (int i = 0; i < indices.length; i++) {
            sortedPlayers[i] = players[indices[i]];
            sortedScores[i] = scores[indices[i]];
        }

        System.arraycopy(sortedPlayers, 0, players, 0, players.length);
        System.arraycopy(sortedScores, 0, scores, 0, scores.length);
    }

    /**
     * Saves sorted player names and scores to a specified file.
     * 
     * @param filePath The path to the file where the sorted scores will be saved.
     * @param players Array of sorted player names.
     * @param scores Array of sorted scores corresponding to the player names.
     */
    private void saveSortedScores(String filePath, String[] players, int[] scores) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for(int i = 0; i < players.length; i++) {
                writer.write(players[i] + "\n");
                writer.write(scores[i] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Reads player names from the specified file.
     *
     * @param filePath the path to the file containing the high scores
     * @return an array of player names, or an empty array if an error occurs
     */
    public String[] getPlayerNames(String filePath) {
        List<String> playerNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                playerNames.add(line.trim());
                reader.readLine(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerNames.toArray(new String[0]);
    }

    /**
     * Reads player scores from the specified file.
     *
     * @param filePath the path to the file containing the high scores
     * @return an array of player scores, or an empty array if an error occurs
     */
    public int[] getPlayerScores(String filePath) {
        List<Integer> playerScores = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null) {
                line = reader.readLine();
                playerScores.add(line != null ? Integer.parseInt(line.trim()) : 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerScores.stream().mapToInt(Integer::intValue).toArray();
    }
}
