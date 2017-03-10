/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;
import persistence.CardMapper;

/**
 *
 * @author Matthias
 */
public class CardRepository {
    
    //Attributes
    private List<Card> startDeck = new ArrayList<>();
    private CardMapper cardMapper;

    //Constructors
    public CardRepository()
    {
        this.cardMapper = new CardMapper();
    }

    /**
     * <pre>
     * Method to retrieve the start deck from the database
     * @return List of cards
     * </pre>
     */

    //Make a new user methods
    public List<Card> giveStartDeck()
    {

        return cardMapper.giveStartDeck();
    }
   //Versie 1 [not needed at this momenent]
    /* public int[] giveIDS() {
       return cardMapper.getIDS();
   }*/
  
   
}
