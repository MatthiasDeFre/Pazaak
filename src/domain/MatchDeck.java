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
    
    //Constructors
    
    public MatchDeck(Match match, List<Card> selectedCards) {
        this.match = match;
        this.matchDeck = selectedCards;
    }
    
    //Methods
   
    public List<Card> getCards() {
        return matchDeck;
    }
}
