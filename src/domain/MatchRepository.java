/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.MatchMapper;

/**
 *
 * @author Matthias
 */
public class MatchRepository {
    private MatchMapper matchMapper;

    public MatchRepository()
    {
        matchMapper = new MatchMapper();
    }
    
    
    public Match loadMatch(String matchName) {
        Match newMatch= null;
        try
        {
            newMatch = matchMapper.loadMatch(matchName);
        } catch (IOException | ClassNotFoundException ex)
        {
            Logger.getLogger(MatchRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newMatch;
    }
}
