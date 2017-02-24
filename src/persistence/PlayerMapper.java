
package persistence;
import domain.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerMapper {

    public void addPlayer(Player player) {
        try (Connection conn = DriverManager.getConnection(Connection.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO" );
            query.setString(1, player.getName());   
            query.setInt(2, player.getbirthYear());
            query.setInt(3, player.getCredits());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public List<Player> givePlayers() {
        List<Player> players = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connection.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM ");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    int date = rs.getInt("date");       
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

        try (Connection conn = DriverManager.getConnection(Connection.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM  WHERE name = ?");
            query.setString(1, name);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                   String name = rs.getString("name");
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
        try (Connection conn = DriverManager.getConnection(Connection.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("UPDATE  SET credi = ? WHERE name = ?");
            query.setInt(1, player.getCredits());
            query.setString(2, player.getName());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

}
