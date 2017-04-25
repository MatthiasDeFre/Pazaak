
package persistence;
import domain.Card;
import domain.Player;
import exceptions.invalidPlayerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import exceptions.userExistsException;
public class PlayerMapper {
    private CardMapper cardMapper = new CardMapper();
    /**
     * <pre>Method to add a player object to the database</pre>
     * @param player Instance of the player class 
     */
    public void addPlayer(Player player) {
        try (Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g07.Player (playerName,credit, birthYear) VALUES (?,?,?)" );
            query.setString(1, player.getName());   
            query.setInt(2, player.getCredit());
            query.setInt(3, player.getbirthYear());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * <pre>Method to give the playerID for the provided player name</pre>
     * @param name Name of the player
     * @return The id of the provided player name
     */
    public int givePlayerID(String name) {
            int id=0;
            try (Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT P_ID FROM ID222177_g07.Player WHERE playerName = ?");
            query.setString(1, name);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                   id = rs.getInt("P_ID");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
            return id;
    }
    
    /**
     * Method to get an instance of player from the database with the provided name (String)
     * @param name Name of the requested player
     * @return An instance of {@link Player}
     * 
     */
    public Player selectPlayer(String name) {
        
        Player selectedPlayer;
        String playerName="";
        int credit=0;
        List<Card> collection;
        int birthYear = 0;
        boolean userExists = false;
         try (Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT playerName, credit, P_ID, birthYear FROM ID222177_g07.Player WHERE playerName = ?");
            query.setString(1, name);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    userExists = true;
                   playerName = rs.getString("playerName");
                   credit = rs.getInt("credit");
                   birthYear = rs.getInt("birthYear");
                   
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        if(!userExists ) {
             ResourceBundle rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
             throw new invalidPlayerException(rs.getString("userDoesntExist"));
        }
        collection = cardMapper.giveCards(name);
        selectedPlayer = new Player(credit, collection, playerName, birthYear);
        return selectedPlayer;
    }
/*    public List<Player> givePlayers() {
        List<Player> players = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g07.Player");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("playerName");
                    int date = rs.getInt("birthYear");       
                    int credit = rs.getInt("credit");

                    players.add(new Player(date, name));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return players;
    }*/
 /*   public Player givePlayer(String name) {
        Player player = null;

        try (Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g07.Player WHERE playerName = ?");
            query.setString(1, name);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                   String nameDB = rs.getString("playerName");
                    int date = rs.getInt("date");      
                    int credit = rs.getInt("credit");
                    
                    player = new Player(date, name);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return player;
    }*/

    public void saveCredit(Player player) {
        try (Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g07.Player SET credit = ? WHERE playerName = ?");
            query.setInt(1, player.getCredit());
            query.setString(2, player.getName());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * <pre>Method to check if the user already exists in the database using the provided username
     * Throws an userExistsException when the user already exists</pre>
     * @param name Name of the user
     */
    public void userExists(String name) {
           try (Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT playerName FROM ID222177_g07.Player WHERE playerName = ?");
            query.setString(1, name);
             try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                   String nameDB = rs.getString("playerName");
                   if(name.equals(nameDB)) {
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
                       throw new userExistsException(resourceBundle.getString("userExists"));
                   }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Method to return all the player names from the database
     * @return List&lt;String&gt; containing the player names
     */
    public List<String> getPlayerNames() {
       List<String> names = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT playerName FROM ID222177_g07.Player");
             try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                   names.add(rs.getString("playerName"));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
       }  
       return names; 
    }
    
    public int givePlayerID(String name, java.sql.Connection conn)
    {
        int id = 0;
        try
        {
            PreparedStatement query = conn.prepareStatement("SELECT P_ID FROM ID222177_g07.Player WHERE playerName = ?");
            query.setString(1, name);
            try (ResultSet rs = query.executeQuery())
            {
                if (rs.next())
                {
                    id = rs.getInt("P_ID");
                }
            }
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        return id;
    }
    
    public Player getPlayer(int playerID, java.sql.Connection conn) {
        Player selectedPlayer;
        String playerName = "";
        int credit = 0;
        int birthYear = 0;
        try
        {
            PreparedStatement query = conn.prepareStatement("SELECT playerName, credit, birthYear FROM ID222177_g07.Player WHERE playerID = ?");
            query.setInt(1, playerID);
            try (ResultSet rs = query.executeQuery())
            {
                if (rs.next())
                {
                    playerName = rs.getString("playerName");
                    credit = rs.getInt("credit");
                    birthYear = rs.getInt("birthYear");

                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        selectedPlayer = new Player(credit, playerName, birthYear);
        return selectedPlayer;
    }
}


