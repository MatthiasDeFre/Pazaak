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

    /**
     * Method to play a Card and add this to the gameboard (gameBoardCards)
     *
     * @param card Instance of Card that needs to be added
     */
    public void playCard(Card card)
    {
        String cardType = card.getType();
        if(cardType.equals("D"))
        {
                if (gameBoardCards.size() < 0)
                {
                    card.setValue(gameBoardCards.get(gameBoardCards.size() - 1).getTrueValue());
                }
        } else if(cardType.contains("&")){
            String[] cardValues = card.getType().split("/&");
            for (Card gameBoardCard : gameBoardCards)
            {
                if(!gameBoardCard.getType().equals("setDeckCard") && gameBoardCard.getValue() == Integer.parseInt(cardValues[0]) || gameBoardCard.getValue() == Integer.parseInt(cardValues[1]))
                {
                    switch(gameBoardCard.getType()){
                        case "+":gameBoardCard.setType("-");
                        break;
                        case "-":gameBoardCard.setType("+");
                        break;                                     
                    }
                    gameBoardCard.changeSign();
                }
                    
                    
            }
        }else if(cardType.contains("xT")){
            frozen=true;
        }
        
        gameBoardCards.add(card);
    }

    /**
     * Method to freeze the board
     */
    public void freezeBoard()
    {
        this.frozen = true;
    }

    public boolean getFrozen()
    {
        return this.frozen;
    }

    public List<Card> getGameBoardCards()
    {
        return this.gameBoardCards;
    }


    /**
     * <pre> Method to calculate the score of the gameboard and return it
     * {@link domain.Card#getTrueValue() }is used instead of {@link domain.Card#getValue()}
     * because for calculations the signed representation should be used</pre>
     *
     * @return The score of the gameboard
     */
    public int calculateGameBoardScore()
    {

        int score = 0;
        for (Card gameBoardCard : gameBoardCards)
        {
            score += gameBoardCard.getTrueValue();
        }
        return score;
    }

}
