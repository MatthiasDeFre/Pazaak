/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import exceptions.invalidPlayerException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Team07
 */
public class Match {
    private List<Player> matchPlayers = new ArrayList<>();
    private final static int AMOUNT_PLAYERS = 2;
    private List<Round> matchRounds = new ArrayList<>();
    
    /**
     * Method to add a player to the match
     * @param player Instance of player
     */
    public void addPlayer(Player player) {
        playerAlreadySelected(player);
        matchPlayers.add(player);
    }
    
    /**
     * Method to get the amount of players still needed to start a match
     * @return The amount of players still needed to start the match
     */
    public int getAmountPlayersStillNeeded() {
        return AMOUNT_PLAYERS - matchPlayers.size();
    }
    
    /**
     * Method to get the player names that were chosen
     * @return A String array containing the chosen players names
     */
    public String[] getChoosenPlayers() {
       String[] playerNames = new String[matchPlayers.size()];
        for (int i = 0; i < matchPlayers.size(); i++)
        {
        playerNames[i] = matchPlayers.get(i).getName();
        }
        return playerNames;
    }
    
    /**
     * Method to get all the players without a matchdeck
     * @return A list of string of player names without a matchdeck
     */
    public List<String> getPlayersWithoutMatchDeck() {
        List<String> playersNoMatchDeck = new ArrayList<>();
        for (Player player : matchPlayers)
        {
            if(player.getMatchDeck() == null) {
                playersNoMatchDeck.add(player.getName());
            }
        }     
        return playersNoMatchDeck;
    }
    
 /*   public void addMatchDeckToPlayer(Player currentPlayer, String[][] selectedCards){
        for (Player matchPlayer : matchPlayers)
        {
            if(currentPlayer.equals(matchPlayer)) {
                matchPlayer.addMatchDeck(this, selectedCards);
            }
        }
    }
 */  
    
    public Player selectPlayer(String name) {
        Player selectedPlayer = null;
        for (Player matchPlayer : matchPlayers)
        {
            if(matchPlayer.getName().equals(name)) {
               selectedPlayer = matchPlayer;
            }
        }
        return selectedPlayer;
    }
    /**
     * Method to check if the chosen player was already selected for the match
     * @param player Player object that needs to be checked
     */
    private void playerAlreadySelected(Player player) {
        if(matchPlayers.contains(player)) {
           ResourceBundle rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
             throw new invalidPlayerException(rs.getString("userAlreadyInMatch"));
        }
    }
    
    //New round methods
    
    /**
     * Method to add a new round to the current matchRounds
     */
    public void addNewRound() {
        matchRounds.add(new Round());
    }
    
    //Match over methods
    
    /**
     * <pre>Method to return the player that won the match
     * Method should only be called if matchEnded has returned true</pre>
     * @return Instance of {@link Player} who won the match
     */
    public Player whoWon() {
        Player winner;
        if (getScoring()[0] == 3)
        {
            winner = matchPlayers.get(0);
        } else
        {
            winner = matchPlayers.get(1);
        }
        addCreditToWinner(winner);
        return winner;

    }
    
    private void addCreditToWinner(Player winner )
    {
        winner.setCredit(winner.getCredit()+5);
    }
    
    

    /**
     * Method to check if the match has ended or not
     * @return true if match has ended, will return false if match has not ended yet
     */
    public boolean matchEnded()
    {
        boolean gg = false;
        
         if (getScoring()[0] == 3 || getScoring()[1] == 3)
        {
            gg = true;
        }
        return gg;
    } 
    
    /**
     * Method to return the current score between the two players
     * @return int[], index 0 contains player 1 his score, index 1 contains player 2 his score
     */
    public int[] getScoring() {
        int[] score = new int[AMOUNT_PLAYERS];
        for (Round matchRound : matchRounds)
        {
            if(matchRound.getWinner().equals(matchPlayers.get(0))) {
                score[0]++;
            }
            else {
               score[1]++;
            }
        }
       return score;
    }
    
    public String[][] getRoundSituation() {
        String[][] roundSituation = new String[4][];
        roundSituation[0] = new String[matchRounds.get(matchRounds.size()-1).getPlayersGameBoards()[0].length]; //Done
        roundSituation[1] = new String[matchRounds.get(matchRounds.size()-1).getPlayersGameBoards()[1].length]; //Done
        roundSituation[2] = new String[2]; //Done
        roundSituation[3] = new String[matchPlayers.get(0).getMatchDeck(this).size()];
        roundSituation[4] = new String[matchPlayers.get(1).getMatchDeck(this).size()];
            
        int[][] gameBoards = matchRounds.get(matchRounds.size()-1).getPlayersGameBoards();
        
        //Extra manier, check snelheid
      //  roundSituation[0] = Arrays.toString(matchRounds.get(matchRounds.size()-1).getPlayersGameBoards(0)).split("[\\[\\]]")[1].split(", "); 
      //  roundSituation[1] = Arrays.toString(matchRounds.get(matchRounds.size()-1).getPlayersGameBoards(1)).split("[\\[\\]]")[1].split(", "); 
        for (int i = 0; i < AMOUNT_PLAYERS; i++)
        {
            for (int j = 0; i < roundSituation[i].length; j++)
            {
                roundSituation[i][j] = String.valueOf(gameBoards[i][j]);
            }
        }
             
        //   roundSituation[0][0] = matchRounds.get((matchRounds.size()-1)).getPlayersGameBoards(0);
          
      //  roundSituation[2][0] = String.valueOf(matchRounds.get(matchRounds.size()-1).getRoundScorePlayer(0));
      //  roundSituation[2][1] = String.valueOf(matchRounds.get(matchRounds.size()-1).getRoundScorePlayer(1));
        
        for (int i = 3; i < 5; i++)
        {
            for (int j = 0; j < roundSituation[j].length; j++)
            {
                roundSituation[i][j] = String.valueOf(matchPlayers.get(i-3).getMatchDeck(this));
            }
        }
        
        roundSituation[4][0] = matchRounds.get(matchRounds.size()-1).getWinner().getName();
        
        
        return roundSituation;
    }
    
}
