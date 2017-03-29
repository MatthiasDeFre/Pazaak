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
    public Round()
    {
        generateSetDeck();
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
    
    
}
