/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Matthias
 */
public class Round{
    
    //RecycleBin
   /* private Player winner;
    private Player loser;
    private boolean draw;*/
    
    //Status
    private String status = null;
    
    
    private List <Integer> setDeck;
  //  private int[][] gameBoard = new int[2][0];
 //   private String[][] gameBoardCardSort = new String[2][0];
  //  private boolean[] gameBoardFrozen = new boolean[2];
  //  private int[] scores = new int[2];
    private int currentTurnPlayerIndex;
    
    private final List<GameBoard> gameBoardList = new ArrayList<>();
    
    public Round(int startPlayerIndex)
    {      
        if(startPlayerIndex == 0) {
            this.currentTurnPlayerIndex = 1;
        } else {
            this.currentTurnPlayerIndex = 0;
        }
        this.gameBoardList.add(new GameBoard());
        this.gameBoardList.add(new GameBoard());
        generateSetDeck();
    }
    
   

   /* public Player getWinner()
    {
        return winner;
    }

    public boolean getDraw() {
        return this.draw;
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
    */
    public int getCurrentPlayerIndex() {
        return this.currentTurnPlayerIndex;
    }

    public List<GameBoard> getGameBoardList()
    {
        return gameBoardList;
    }
    protected void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentTurnPlayerIndex = currentPlayerIndex;
    }
    
    public List<Integer> getSetDeck() {
        return this.setDeck;
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
    
    
    public String[][] getPlayersGameBoards() {
       String[][] gameBoards = new String[2][];
        for (int i = 0; i < gameBoardList.size(); i++)
        {
            gameBoards[i] = new String[gameBoardList.get(i).getGameBoardCards().size()];
            for (int j = 0; j < gameBoards[i].length; j++)
            {
                Card gameBoardCard = gameBoardList.get(i).getGameBoardCards().get(j);
                gameBoards[i][j] = gameBoardCard.getType() + gameBoardCard.getValue();
            }
        }
        return gameBoards;
    }
    
  /*  public String[][] getPlayerGameBoardCardSorts() {
        return gameBoardCardSort;
    }
    */
    public int [] getScores() {
      int[] scores=  calculateGameBoardScores();
        return scores;
    }
    public String getStatus() {
        return this.status;
    }
    
    /**
     * <pre> Method to go to the nextTurn {@link domain.Round#nextTurn()}
     * Depending on the status of the gameboard ({@link domain.GameBoard#getFrozen()}
     * will set the turn to the next player.
     * If the next turn's player's board is not frozen a new Card will be added to his gameboard and removed from the setdeck</pre>
     */
    public void nextTurn() {
        if(currentTurnPlayerIndex == 0 && gameBoardList.get(1).getFrozen() == false) {
            currentTurnPlayerIndex = 1;
        } else if(currentTurnPlayerIndex == 1 && gameBoardList.get(0).getFrozen() == false) {
            currentTurnPlayerIndex = 0;
        } 
        if(gameBoardList.get(currentTurnPlayerIndex).getFrozen() == false) {
            playCard(new Card("setDeckCard",setDeck.get(setDeck.size()-1)));
             setDeck.remove(setDeck.size()-1);
        }
       
    }
    
    public void freezeBoard() {
       gameBoardList.get(currentTurnPlayerIndex).freezeBoard();
      // this.nextTurn();
    }
    
    
    public void playCard(Card card) {
        //Make a copy from the array
    /*    int[] cardValueArrayCopy = gameBoard[currentTurnPlayerIndex];
        String[] cardSortArrayCopy = gameBoardCardSort[currentTurnPlayerIndex];
        
        //Make the old arrays 1 bigger
        gameBoard[currentTurnPlayerIndex] = new int[gameBoard[currentTurnPlayerIndex].length + 1];
        gameBoardCardSort[currentTurnPlayerIndex] = new String[gameBoardCardSort[currentTurnPlayerIndex].length + 1];
        
        //Copy to old array to the new array 
        System.arraycopy(cardValueArrayCopy, 0, gameBoard[currentTurnPlayerIndex], 0, cardValueArrayCopy.length);
        System.arraycopy(cardSortArrayCopy, 0, gameBoardCardSort[currentTurnPlayerIndex], 0, cardSortArrayCopy.length);
        
        //Put card value and sort into last element of newly formed array
        gameBoard[currentTurnPlayerIndex][gameBoard[currentTurnPlayerIndex].length-1] = cardValue;
        gameBoardCardSort[currentTurnPlayerIndex] [gameBoardCardSort[currentTurnPlayerIndex].length-1] = cardSort;*/
        gameBoardList.get(currentTurnPlayerIndex).playCard(card);
        /*if(card.getType().equals("xT")){
            nextTurn();
        }*/
    
        
       
    }
    
    public boolean roundEnded() {
        boolean roundEnded = false;
        int[] scores = calculateGameBoardScores();
        if(scores[0] > 20 || scores[1] > 20) {
            roundEnded = true;
        }
        if(gameBoardList.get(0).getGameBoardCards().size() == 9 || gameBoardList.get(1).getGameBoardCards().size() == 9) {
            roundEnded = true;
        }
        if(gameBoardList.get(0).getFrozen() == true && gameBoardList.get(1).getFrozen()  == true) {
            roundEnded = true;
        }
        return roundEnded;
    }
    
    public void setRoundEndedResults(List<Player> roundPlayers) {
       
       int[] scores = calculateGameBoardScores();
        if(scores[0] == scores[1])
        {
            //   this.draw = true;
            boolean player1T = false;
            boolean player2T = false;
            
            for (int i = 0; i < gameBoardList.size(); i++)
            {
                
                for (Card card : gameBoardList.get(i).getGameBoardCards())
                {
                    if(card.getType().equals("xT"))
                       if(i==0){
                           player1T=true;
                       } else{
                           player2T=true;
                       }
                    
                }
            }
            if(player1T == true && player2T == false)
                this.status=roundPlayers.get(0).getName();
            else if(player1T==false && player2T==true)
                this.status=roundPlayers.get(1).getName();
            else 
                this.status = "draw";

        } else if (scores[0] > scores[1] && scores[0] <= 20 || scores[1] > 20 && scores[0] <= 20)
        {
            //     this.winner = roundPlayers.get(0);
            this.status = roundPlayers.get(0).getName();

        } else
        {
            //      this.winner = roundPlayers.get(1);
            this.status = roundPlayers.get(1).getName();
        }
    }
    
    public int [] calculateGameBoardScores() {
       
        int[] scoresList = new int[2];
        scoresList[0] = gameBoardList.get(0).calculateGameBoardScore();
        scoresList[1] = gameBoardList.get(1).calculateGameBoardScore();
        return scoresList;
    /*    for (int i = 0; i < gameBoard.length; i++)
        {
            for (int scorePlayer : gameBoard[i])
            {
                scores[i] += scorePlayer;
            }
        }*/
       
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String[] getLastCardPlayed() {
        String[] card = new String[2];
        Card cardObject = gameBoardList.get(currentTurnPlayerIndex).getGameBoardCards().get(gameBoardList.get(currentTurnPlayerIndex).getGameBoardCards().size()-1);
        card[0] = cardObject.getType();
        card[1] = String.valueOf(cardObject.getValue());
        return card;
    }

}
