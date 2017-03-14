/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import exceptions.invalidPlayerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Matthias
 */
public class Match {
    private List<Player> matchPlayers = new ArrayList<>();
    private final static int AMOUNT_PLAYERS = 2;
    
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
              if(player.getMatchDeck() != null) {
                playersNoMatchDeck.add(player.getName());
            }
        }     
        return playersNoMatchDeck;
    }
    
    private void playerAlreadySelected(Player player) {
        if(matchPlayers.contains(player)) {
           ResourceBundle rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
             throw new invalidPlayerException(rs.getString("userAlreadyInMatch"));
        }
    }
    
    
    
    
    
    
}
