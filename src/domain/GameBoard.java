/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matthias
 */
public class GameBoard {
    private List<Card> gameBoardCards = new ArrayList<>();
    private boolean frozen;
    
      public void playCard(Card card) {    
       gameBoardCards.add(card);
    }
    public void freezeBoard() {
        this.frozen = true;
    }
    public boolean getFrozen() {
        return this.frozen;
    }
    public List<Card> getGameBoardCards() {
        return this.gameBoardCards;
    }
    
    public int calculateGameBoardScore() {
       
        int score=0;
        for (Card gameBoardCard : gameBoardCards)
        {
            score += gameBoardCard.getTrueValue();
        }
       return score;
    }
    
}
