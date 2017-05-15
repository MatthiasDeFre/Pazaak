/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import exceptions.matchNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
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

        Match loadedMatch =  matchMapper.loadMatchNoBlob(matchName);
        if(loadedMatch == null) {
            ResourceBundle rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
            throw new matchNotFoundException(rs.getString("errorLoad"));
        }
        return loadedMatch;
    }
    
    public List<String> getSavegameNames() {
        return matchMapper.getSavegameNames();
    }
    
    public void saveMatch(String matchName, Match match) {
        if(getSavegameNames().contains(matchName)) {
            ResourceBundle rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
            throw new matchNotFoundException(rs.getString("matchNameAlready"));
        }
        matchMapper.saveMatchNoBlob(matchName, match);
    }
}
