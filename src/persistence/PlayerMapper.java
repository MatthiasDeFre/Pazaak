
package persistence;
import domain.Player;
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
    public List<Player> givePlayers() {
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
    }
    public Player givePlayer(String name) {
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
    }

    public void saveCredit(Player player) {
        try (Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("UPDATE  SET credit = ? WHERE name = ?");
            query.setInt(1, player.getCredit());
            query.setString(2, player.getName());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void userExists(String name) {
           try (Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT playerName FROM ID222177_g07.Player");
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
}


