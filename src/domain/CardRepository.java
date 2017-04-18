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
 * <pre>Class to get cards from the database and make them into instances of {@link Card}
 * Properties:
 * List<
 * 
 * @author Team-07</pre>
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
     * Calls the cardMapper method {@link persistence.CardMapper#giveStartDeck()}
     * </pre>
     * @return List of cards
     * 
     */
    public List<Card> giveStartDeck()
    {
        return cardMapper.giveStartDeck();
    }
   
}
