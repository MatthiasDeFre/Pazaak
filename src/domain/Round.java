/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Matthias
 */
public class Round {
    private Player winner;
    private Player loser;

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
    
    
}
