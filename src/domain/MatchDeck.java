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
public class MatchDeck {
    private List<Card> matchDeck = new ArrayList<>();
    private Match match;
    private Player player;
    
    
    //Constructors
    
    public MatchDeck(Player player, Match match) {
        this.player = player;
        this.match = match;
    }
    
    //Methods
    
    public void addCards(List<Card> cards) {
        matchDeck = cards;
    }
    
    public List<Card> getCards() {
        return matchDeck;
    }
    
}
