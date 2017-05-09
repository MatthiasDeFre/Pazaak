/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Card;
import domain.Match;
import domain.MatchDeck;
import domain.Player;
import domain.Round;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Matthias
 */
public class MatchMapper {

    private PlayerMapper playerMapper = new PlayerMapper();
    private CardMapper cardMapper = new CardMapper();
    
    public void addMatch(String matchName, FileInputStream fileInputStream)
    {
        try (java.sql.Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g07.Match (name,matchFile) VALUES (?,?)" );
            query.setString(1, matchName);   
            query.setBlob(2, fileInputStream);
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public Match loadMatch(String matchName) throws IOException, ClassNotFoundException {
        Match match = null;
        try (java.sql.Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL))
        {
            PreparedStatement query = conn.prepareStatement("SELECT matchFile FROM ID222177_g07.Match WHERE name = ?");
            query.setString(1, matchName);
            try (ResultSet rs = query.executeQuery())
            {
                if (rs.next())
                {
                    Blob blob = rs.getBlob(1);
                    InputStream in = blob.getBinaryStream();
                    try (OutputStream out = new FileOutputStream("loadFile.data"))
                    {
                        int len = 0;
                        byte[] buff = new byte[4096];
                        while ((len = in.read(buff)) != -1)
                        {
                            out.write(buff, 0, len);
                        }
                        ObjectInputStream d;
                        try (FileInputStream in1 = new FileInputStream("loadFile.data"))
                        {
                            d = new ObjectInputStream(in1);
                            match = (Match) d.readObject();
                        }
                        d.close();
                    }
                    //       FileInputStream in = (FileInputStream)rs.getBlob(1);
                    //  ObjectInputStream d = new ObjectInputStream(in);
                }
            }
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        
        return match;
    }
    
    public void saveMatchNoBlob(String matchName, Match match) {
        int[] playerID = new int[2];
        try (java.sql.Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL)) {
            
            //Match
            PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g07.Match (matchName, player1ID, player2ID, aiMatch) VALUES (?,?,?,?)");
            for (int i = 0; i < match.getChoosenPlayers().length; i++)
            {
                playerID[i] = playerMapper.givePlayerID(match.getChoosenPlayers()[i], conn);
            }
            query.setString(1, matchName);
            query.setInt(2, playerID[0]);
            query.setInt(3, playerID[1]);
            query.setBoolean(4, match.isAIMatch());
            query.executeUpdate();
  
            
            int matchID = getMatchID(matchName, conn);
            
            //MatchDeck
            for (int i = 0; i < match.getMatchPlayers().size(); i++)
            {
                Player player = match.getMatchPlayers().get(i);
                List<Integer> cardID = cardMapper.getCardIDs(player.getMatchDeck(match), conn);
                for (Integer card : cardID)
                {
                    query = conn.prepareStatement("INSERT INTO ID222177_g07.MatchDeck (matchID, playerID, cardID) VALUES (?,?,?)");
                    query.setInt(1, matchID);
                    query.setInt(2, playerID[i]);
                    query.setInt(3, card);
                    query.executeUpdate();
                }
            }

            //Round
            for (int i = 0; i < match.getMatchRounds().size(); i++)
            {
                Round matchRound = match.getMatchRounds().get(i);
                query = conn.prepareStatement("INSERT INTO ID222177_g07.Round (matchID, roundNumber, playerID) VALUES (?,?,?)");
                query.setInt(1, matchID);
                query.setInt(2, i);
                String status = matchRound.getStatus();
                if(!(status.equals("draw"))) {
                 //   query.setInt(3, playerID[status]);
                    if(match.getMatchPlayers().get(0).getName().equals(status)) {
                        query.setInt(3, playerID[0]);
                    } else {
                        query.setInt(3, playerID[1]);
                    }
                } else {
                    query.setNull(3, java.sql.Types.INTEGER);
                }
                query.executeUpdate();
            }
           

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<String> getSavegameNames()
    {
        List<String> savegameNames = new ArrayList<>();
         try (java.sql.Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL))
        {
            PreparedStatement query = conn.prepareStatement("SELECT matchName FROM ID222177_g07.Match");
            try (ResultSet rs = query.executeQuery())
            {
                while (rs.next())
                {
                  savegameNames.add(rs.getString("matchName"));
                         
                }
            }
            
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
       return savegameNames;
    }
    
    private int getMatchID(String matchName, java.sql.Connection conn) {
        int matchID = 0;
        try
        {
            PreparedStatement query = conn.prepareStatement("SELECT matchID FROM ID222177_g07.Match WHERE matchName = ?");
            query.setString(1, matchName);
            try (ResultSet rs = query.executeQuery())
            {
                if (rs.next())
                {
                    matchID = rs.getInt(1);
                }
            }
        } catch (SQLException ex)
        {

            throw new RuntimeException(ex);
        }
        return matchID;
    }
    
    public Match loadMatchNoBlob(String name) {
        int matchID= 0;
        int[] playerID = new int[2];
        Match match = new Match();
        boolean matchFound = false;
        try (java.sql.Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL))
        {
            PreparedStatement query = conn.prepareStatement("SELECT matchID, player1ID, player2ID FROM ID222177_g07.Match WHERE matchName = ?");
            query.setString(1, name);
          
            try (ResultSet rs = query.executeQuery())
            {
                while (rs.next())
                {
                    matchFound = true;
                    matchID = rs.getInt("matchID");
                    playerID[0] = rs.getInt("player1ID");
                    playerID[1] = rs.getInt("player2ID");
                    Player player1 = playerMapper.getPlayer(playerID[0], conn);
                    Player player2 = playerMapper.getPlayer(playerID[1], conn);
                    player1.addMatchDeck(match, getMatchDeckCards(matchID, playerID[0], conn));
                    player2.addMatchDeck(match, getMatchDeckCards(matchID, playerID[1], conn));
                    match.addPlayer(player1);
                    match.addPlayer(player2);
                    for (Round round : getRounds(match, matchID, conn, playerID))
                    {
                        match.addRound(round);
                    }
                         
                }
            }
            
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        if(matchFound == false) {
            match = null;
        }
        return match;
    }
    
    private List<Card> getMatchDeckCards(int matchID, int playerID, java.sql.Connection conn) {
        List<Card> matchDeckCards = new ArrayList<>();
        try
        {
            PreparedStatement query = conn.prepareStatement("SELECT cardID  FROM ID222177_g07.MatchDeck WHERE matchID = ? AND playerID = ?");
            query.setInt(1, matchID);
            query.setInt(2, playerID);
            try (ResultSet rs = query.executeQuery())
            {
                while (rs.next())
                {
                 matchDeckCards.add(cardMapper.getCard(rs.getInt(1), conn));
                }
            }

        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        return matchDeckCards;
    }
    
    private List<Round> getRounds(Match match,int matchID, java.sql.Connection conn, int[] playerID) {
        List<Round> matchRounds = new ArrayList<>();
          try
        {
            PreparedStatement query = conn.prepareStatement("SELECT playerID FROM ID222177_g07.MatchDeck WHERE matchID = ? ");
            query.setInt(1, matchID);
            try (ResultSet rs = query.executeQuery())
            {
                while (rs.next())
                {
                  Round round = new Round(match.determineStartPlayer());
                  int status = rs.getInt(1);
                  if(status == 0) {
                  status = -1;
                  } else  {
                     if(status == playerID[0]) {
                         status = 0;
                     } else {
                         status = 1;
                     }
                  }
                  round.setStatus(status);
                }
            }

        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        return matchRounds;
    }
}
