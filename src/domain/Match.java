/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import exceptions.invalidPlayerException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Team07
 */
public class Match{
    
    
    
    private List<Player> matchPlayers = new ArrayList<>();
    private final static int AMOUNT_PLAYERS = 2;
    private List<Round> matchRounds = new ArrayList<>();
    private boolean AI;
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
    
    public List<Player> getMatchPlayers() {
        return this.matchPlayers;
    }
    
    public List<Round> getMatchRounds() {
        return this.matchRounds;
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
          /*  if(matchRound.getDraw() == false) {                 
                if (matchRound.getWinner().equals(matchPlayers.get(0)))
                {
                    score[0]++;
                } else
                {
                    score[1]++;
                }
            }*/
            if(!matchRound.getStatus().equals("draw")) {                 
                if (matchRound.getStatus().equals(matchPlayers.get(0).getName()))
                {
                    score[0]++;
                } else
                {
                    score[1]++;
                }
        }
        } 
       return score;
    }
    
    //Round methods
    
    public int determineStartPlayer()
    {   
        int startPlayerIndex = 0;
        if (matchPlayers.get(0).getbirthYear() > matchPlayers.get(1).getbirthYear() && ((matchRounds.size()+1) % 2) != 0)
        {
            startPlayerIndex = 1;
        } else if (matchPlayers.get(0).getbirthYear() == matchPlayers.get(1).getbirthYear())
        {
             List<String> sortedList = new ArrayList<>();
             sortedList.add(matchPlayers.get(0).getName());
             sortedList.add(matchPlayers.get(1).getName());
             java.util.Collections.sort(sortedList);
             if(matchPlayers.get(0).getName().equals(sortedList.get(0))) {
                 startPlayerIndex = 1;
             }
        }
        return startPlayerIndex;
    }

    /**
     * Method to add a new round the match
     */
    public void startNewRound()
    {   
        if(this.AI) {
        matchRounds.add(new RoundAI(determineStartPlayer(), matchPlayers.get(1)));
        } else {
        matchRounds.add(new Round(determineStartPlayer()));
        }
    }
    
    /**
     * Method to go to the nextTurn {@link domain.Round#nextTurn()}
     */
    public void nextTurn() {
        matchRounds.get(matchRounds.size() -1).nextTurn();
    }
    
    /**
     * Method to freeze the board of the current player {@link domain.Round#freezeBoard()} 
     */
    public void freezeBoard() {
          matchRounds.get(matchRounds.size() -1).freezeBoard();
          //matchRounds.get(matchRounds.size() -1).nextTurn();
    }
    
    /**
     * <pre> Method to check if the current round has ended.
     * Incase the round has ended the results of the current round will be set</pre>
     * @return 
     */
    public boolean roundEnded() {
        if(matchRounds.get(matchRounds.size()-1).roundEnded()) {
            matchRounds.get(matchRounds.size()-1).setRoundEndedResults(matchPlayers);
        }
        return matchRounds.get(matchRounds.size()-1).roundEnded();
    }
    
    /**
     * <pre>Method to return an array containing the current round's situation
     * Index [0][] the gameboard of Player 1
     * Index [1][] the gameboard of Player 2
     * Index [2][] scores of the players [0] score player 1, [1] score player 2
     * Index [3][] the deck of the currentplayer, only the current player's deck get shown to prevent cheating
     * Index [4][] the name of the player whose turn it is
     * @return An array containing all information to setup the the situation of a current round (see above for more information)
     */
    public String[][] getRoundSituation() {
        String[][] roundSituation = new String[5][];
        Round currentRound = matchRounds.get(matchRounds.size()-1);
        roundSituation[0] = currentRound.getPlayersGameBoards()[0]; //Player 1 board
        roundSituation[1] =  currentRound.getPlayersGameBoards()[1]; //Player 2 board
        roundSituation[2] = new String[2]; //Scores 0 = player 1, 1 = player 2
        roundSituation[3] = new String[matchPlayers.get(currentRound.getCurrentPlayerIndex()).getMatchDeck(this).size()]; //Current Player deck
      //   roundSituation[4] = new String[matchPlayers.get(1).getMatchDeck(this).size()]; //Player 2 deck
        roundSituation[4] = new String[1];
     //   int[][] gameBoards = currentRound.getPlayersGameBoards();
       // String[][] gameBoardCardSort = currentRound.getPlayerGameBoardCardSorts();
        //Extra manier, check snelheid
      //  roundSituation[0] = Arrays.toString(matchRounds.get(matchRounds.size()-1).getPlayersGameBoards(0)).split("[\\[\\]]")[1].split(", "); 
      //  roundSituation[1] = Arrays.toString(matchRounds.get(matchRounds.size()-1).getPlayersGameBoards(1)).split("[\\[\\]]")[1].split(", ");  
        //   roundSituation[0][0] = matchRounds.get((matchRounds.size()-1)).getPlayersGameBoards(0);    
      //  roundSituation[2][0] = String.valueOf(matchRounds.get(matchRounds.size()-1).getRoundScorePlayer(0));
      //  roundSituation[2][1] = String.valueOf(matchRounds.get(matchRounds.size()-1).getRoundScorePlayer(1));
        for (int i = 0; i < AMOUNT_PLAYERS; i++)
        {
            roundSituation[2][i] = String.valueOf(currentRound.getScores()[i]);
        }
      
     /*   for (int i = 3; i < 5; i++)
        {
            for (int j = 0; j < roundSituation[i].length; j++)
            {
                roundSituation[i][j] = matchPlayers.get(i-3).getMatchDeck(this).get(j).getType() + matchPlayers.get(i-3).getMatchDeck(this).get(j).getValue();
            }
        }*/
        for (int j = 0; j < roundSituation[3].length; j++)
        {
            roundSituation[3][j] = matchPlayers.get(currentRound.getCurrentPlayerIndex()).getMatchDeck(this).get(j).getType() + matchPlayers.get(currentRound.getCurrentPlayerIndex()).getMatchDeck(this).get(j).getValue();
        }
        roundSituation[4][0] = matchPlayers.get(matchRounds.get(matchRounds.size()-1).getCurrentPlayerIndex()).getName();
        return roundSituation;
    }
    
    /**
     * <pre> Method to retrieve the Card from the player his deck
     * and add this to the current round's gameboard {@link domain.Round#playCard(domain.Card)}.
     * This also removes the card from the player's deck</pre>
     * @param cardIndex 
     */
    public void playCard(int cardIndex) {
        Card cardToBePlayed = matchPlayers.get(matchRounds.get(matchRounds.size()-1).getCurrentPlayerIndex()).getMatchDeck(this).get(cardIndex-1);
        matchPlayers.get(matchRounds.get(matchRounds.size()-1).getCurrentPlayerIndex()).getMatchDeck(this).remove(cardIndex-1);
        matchRounds.get(matchRounds.size()-1).playCard(cardToBePlayed);
    }
    
    /**
     * Method to change the sign of the card (whose index was giving) of the current player
     * @param cardIndex The index of the card that needs to be changed
     */
    public void changeCardSign(int cardIndex){
        matchPlayers.get(matchRounds.get(matchRounds.size()-1).getCurrentPlayerIndex()).getMatchDeck(this).get(cardIndex-1).changeSign();
    }
    
    public boolean isAIMatch() {
        return this.AI;
    }
    
    public void setAI(boolean AI) {
        this.AI = AI;
    }
    
    public boolean getAIWantsNextTurn() {
        return ((RoundAI)matchRounds.get(matchRounds.size()-1)).getAIwantsNextTurn();
    }
    
    /**
     * Method to return the current player
     * @return 
     */
    public Player getCurrentPlayer() {
        return  matchPlayers.get(matchRounds.get(matchRounds.size() -1).getCurrentPlayerIndex());
    }
    
    /**
     * Method to add an existing instance of Round to the match
     * @param round Instance of Round, the instance should at least contain the status field 
     */
    public void addRound(Round round) {
        matchRounds.add(round);
    }
    
   
    
}
