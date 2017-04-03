/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Matthias
 */
public class Round {
    private Player winner;
    private Player loser;
    private List <Integer> setDeck;
    private int[][] gameBoard = new int[2][0];
    private String[][] gameBoardCardSort = new String[2][0];
    private boolean[] gameBoardFrozen = new boolean[2];
    private int[] scores = new int[2];
    private int currentTurnPlayerIndex;
    
    public Round(int startPlayerIndex)
    {      
        this.currentTurnPlayerIndex = startPlayerIndex;
        generateSetDeck();
        playCard(setDeck.get(setDeck.size()-1), "setDeckCard");
        setDeck.remove(setDeck.size()-1);
    }
    

    public Player getWinner()
    {
        return winner;
    }

    public void setWinner(Player winner)
    {
        this.winner = winner;
    }

    public Player getLoser()
    {
        return loser;
    }

    public void setLoser(Player loser)
    {
        this.loser = loser;
    }
    
    public int getCurrentPlayerIndex() {
        return this.currentTurnPlayerIndex;
    }
    
    public void setCurrentPlayerIndexNextTurn() {
       if(currentTurnPlayerIndex == 0 && gameBoardFrozen[1] == false) {
            this.currentTurnPlayerIndex = 1;
        }
        else {
            this.currentTurnPlayerIndex = 0;
        }
    }
    
    
    private void generateSetDeck()
    {
        List <Integer> setDeckPrivate= new ArrayList<>();
        for (int i = 1; i <= 4; i++)
        {
            for (int j = 1; j <= 10; j++)
            {
                setDeckPrivate.add(j);
            }
        }
        long seed = System.nanoTime();
        Collections.shuffle(setDeckPrivate, new Random(seed));
        Collections.shuffle(setDeckPrivate, new Random(seed));
        this.setDeck=setDeckPrivate;
       
    }
    
    public List<Integer> getSetDeck() {
        return setDeck;
    }
    
    public int[][] getPlayersGameBoards() {
        return gameBoard;
    }
    
    public String[][] getPlayerGameBoardCardSorts() {
        return gameBoardCardSort;
    }
    
    public int [] getScores() {
        calculateGameBoardScores();
        return this.scores;
    }
    
    public int[] getRoundScorePlayer() {
        int[] playerScore= new int[2];
        for (int i = 0; i < 2; i++)
        {
        for (int j = 0; j < gameBoard[i].length; j++)
        {
            playerScore[i] += gameBoard[i][j];
        }
        }
        return playerScore;
    }
    
    public void nextTurn() {
        if(currentTurnPlayerIndex == 0 && gameBoardFrozen[1] == false) {
            currentTurnPlayerIndex = 1;
        } else if(currentTurnPlayerIndex == 1 && gameBoardFrozen[0] == false) {
            currentTurnPlayerIndex = 0;
        }
        playCard(setDeck.get(setDeck.size()-1), "setDeckCard");
        setDeck.remove(setDeck.size()-1);
    }
    
    public void freezeBoard() {
        gameBoardFrozen[currentTurnPlayerIndex] = true;
    }
    
    
    public void playCard(int cardValue, String cardSort) {
        //Make a copy from the array
        int[] cardValueArrayCopy = gameBoard[currentTurnPlayerIndex];
        String[] cardSortArrayCopy = gameBoardCardSort[currentTurnPlayerIndex];
        
        //Make the old arrays 1 bigger
        gameBoard[currentTurnPlayerIndex] = new int[gameBoard[currentTurnPlayerIndex].length + 1];
        gameBoardCardSort[currentTurnPlayerIndex] = new String[gameBoardCardSort[currentTurnPlayerIndex].length + 1];
        
        //Copy to old array to the new array 
        System.arraycopy(cardValueArrayCopy, 0, gameBoard[currentTurnPlayerIndex], 0, cardValueArrayCopy.length);
        System.arraycopy(cardSortArrayCopy, 0, gameBoardCardSort[currentTurnPlayerIndex], 0, cardSortArrayCopy.length);
        
        //Put card value and sort into last element of newly formed array
        gameBoard[currentTurnPlayerIndex][gameBoard[currentTurnPlayerIndex].length-1] = cardValue;
        gameBoardCardSort[currentTurnPlayerIndex] [gameBoardCardSort[currentTurnPlayerIndex].length-1] = cardSort;
       
    }
    
    public boolean roundEnded() {
        boolean roundEnded = false;
        calculateGameBoardScores();
        if(scores[0] > 20 || scores[1] > 20) {
            roundEnded = true;
        }
        if(gameBoard[0].length == 9 || gameBoard[1].length == 9) {
            roundEnded = true;
        }
        if(gameBoardFrozen[0] == true && gameBoardFrozen[1] == true) {
            roundEnded = true;
        }
        return roundEnded;
    }
    
    private void calculateGameBoardScores() {
       
        scores = new int[2];
        for (int i = 0; i < gameBoard.length; i++)
        {
            for (int scorePlayer : gameBoard[i])
            {
                scores[i] += scorePlayer;
            }
        }
       
    }
}
