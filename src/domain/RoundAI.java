/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Matthias Test
 */
public class RoundAI extends Round {

    private Player AI;
    private boolean AIwantsNextTurn;
    private Match match;
    
    public RoundAI(int startPlayerIndex, Player AI)
    {
        super(startPlayerIndex);
        this.AI = AI;
    }

    @Override
    public void nextTurn()
    {
        if (getCurrentPlayerIndex() == 0 && getGameBoardList().get(1).getFrozen() == false)
        {
            AIwantsNextTurn = true;
            setCurrentPlayerIndex(1);
        } else if (getCurrentPlayerIndex() == 1 && getGameBoardList().get(0).getFrozen() == false)
        {
            AIwantsNextTurn = false;
            setCurrentPlayerIndex(0);
        }
        if (getGameBoardList().get(getCurrentPlayerIndex()).getFrozen() == false)
        {
            playCard(new Card("setDeckCard", getSetDeck().get(getSetDeck().size() - 1)));
            getSetDeck().remove(getSetDeck().size() - 1);
        }
        if (!(this.AI == null) && getCurrentPlayerIndex() == 1 && getGameBoardList().get(1).getFrozen() == false)
        {
            this.makeAIMove();
        }
        if (getGameBoardList().get(0).getFrozen() == true && getGameBoardList().get(1).getFrozen() == true)
        {
            AIwantsNextTurn = true;
        }

    }

    private void makeAIMove()
    {
        //Win condition
        if (getGameBoardList().get(1).calculateGameBoardScore() == 20 || getGameBoardList().get(0).getFrozen() && getGameBoardList().get(1).calculateGameBoardScore() > getGameBoardList().get(0).calculateGameBoardScore())
        {
            this.freezeBoard();
        } else
        {
            this.calculateMove(this.calculateMoveWinRiskPercentage());
        }
    }

    private double[][] calculateMoveWinRiskPercentage()
    {
        double notLosePercentage = 0;
        int cardAmountEqualToLose = 0;
        int currentBoardScore = getGameBoardList().get(1).calculateGameBoardScore();
        int minusMargin = this.calculateMinusMargin();

        for (int i = 0; i < getSetDeck().size(); i++)
        {
            if (getSetDeck().get(i) + getGameBoardList().get(1).calculateGameBoardScore() + minusMargin > 20)
            {
                cardAmountEqualToLose++;
            }
        }
        notLosePercentage = 1.0 - (((double) cardAmountEqualToLose) / getSetDeck().size());
        double[][] winRisk = new double[1][1];
        winRisk[0][0] = notLosePercentage;
        return winRisk;
    }

    private int calculateMinusMargin()
    {
        int minusMargin = 0;
        List<Card> minusCardList = new ArrayList<>();
     /*   for (int i = 0; i < AI.getMatchDeck().getCards().size(); i++)
        {
            Card cardToCheck = AI.getMatchDeck().getCards().get(i);
            if (cardToCheck.getType().equals("-"))
            {
                minusMargin = AI.getMatchDeck().getCards().get(i).getValue();
                for (int j = 0; j < AI.getMatchDeck().getCards().size(); j++)
                {
                    Card cardToCheckWith = AI.getMatchDeck().getCards().get(j);
                    if (cardToCheckWith.getType().equals("-") && !(i == j) && !(i == j) && cardToCheck.getValue() + cardToCheckWith.getValue() > minusMargin)
                    {
                        minusMargin = cardToCheck.getValue() + cardToCheckWith.getValue();
                    }
                }
            }
        }*/
        for (int i = 0; i < AI.getMatchDeck().getCards().size(); i++)
        {
             Card cardToCheck = AI.getMatchDeck().getCards().get(i);
             if(cardToCheck.getType().equals("-")) {
                 minusCardList.add(cardToCheck);
            }
            Collections.sort(minusCardList, new Comparator<Card>() {
                @Override
                public int compare(final Card object1, final Card object2)
                {
                    return String.valueOf(object1.getTrueValue()).compareTo(String.valueOf(object2.getTrueValue()));
                }
            });
        }
        if(minusCardList.size() > 0) {
             minusMargin = minusCardList.get(minusCardList.size()-1).getValue();
        }
        return minusMargin;
    }
        
    private void calculateMove(double [][] differentMoveWithRiskPercentage) {
        if(differentMoveWithRiskPercentage[0][0] > 0.60 && getGameBoardList().get(1).calculateGameBoardScore() < 20) {
           // this.nextTurn();
           AIwantsNextTurn = true;
        } else {
            if(!aiTurn().getType().equals("noCardFound")) {
                playCard(aiTurn());
                AI.getMatchDeck().getCards().remove(aiTurn());
                makeAIMove();
            } else {
                this.freezeBoard();
            }
            
         //   gameBoardList.get(1).freezeBoard();
        }
    }
    
    private Card aiTurn() {
     List<Card> aiCardList = AI.getMatchDeck().getCards();
     Card cardToBePlayed = new Card("noCardFound", 0);
        for (Card card : aiCardList)
        {
            if (getGameBoardList().get(1).calculateGameBoardScore() + card.getTrueValue() == 20
                    || getGameBoardList().get(0).calculateGameBoardScore() <= getGameBoardList().get(1).calculateGameBoardScore() + card.getTrueValue() && getGameBoardList().get(1).calculateGameBoardScore() + card.getTrueValue() <= 20)
            {
               // if( getGameBoardList().get(1).calculateGameBoardScore() + card.getTrueValue() > getGameBoardList().get(1).calculateGameBoardScore() + cardToBePlayed.getTrueValue()) {
                       cardToBePlayed = card;
               // }
             
            }
        }
        return cardToBePlayed;
    }

    public boolean getAIwantsNextTurn() {
        return AIwantsNextTurn;
    }
    
}
