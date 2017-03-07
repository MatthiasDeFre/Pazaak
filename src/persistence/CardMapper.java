package persistence;
import domain.Card;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardMapper
{
    public void addCard(Card card, String playerName)
    {
        try (java.sql.Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL))
        {
            PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g07.Card (playerName,value, type) VALUES (?,?,?)");
            query.setString(3, card.getType());
            query.setString(1, playerName);
            query.setInt(2, card.getValue());
            query.executeUpdate();
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
    }
    
    public List<Card> giveStartDeck()
    {
         List<Card> cards = new ArrayList<>();
         try (java.sql.Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL))
        {
            PreparedStatement query = conn.prepareStatement("SELECT type, value FROM ID222177_g07.CardType");
            try (ResultSet rs = query.executeQuery())
            {
                while (rs.next())
                {
                    String type = rs.getString("type");
                    int value = rs.getInt("value");
                    cards.add(new Card(type, value));
                }
            }
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }

        return cards;
    }

    public List<Card> giveCards()
    {
        List<Card> cards = new ArrayList<>();

        try (java.sql.Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL))
        {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM ");
            try (ResultSet rs = query.executeQuery())
            {
                while (rs.next())
                {
                    String type = rs.getString("type");
                    int value = rs.getInt("value");

                    cards.add(new Card(type, value));
                }
            }
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }

        return cards;
    }

    public Card giveCard(String type)
    {
        Card card = null;

        try (java.sql.Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL))
        {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM  WHERE type = ?");
            query.setString(1, type);
            try (ResultSet rs = query.executeQuery())
            {
                if (rs.next())
                {
                    String typeDB = rs.getString("type");
                    int value = rs.getInt("value");

                    card = new Card(type, value);
                }
            }
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }

        return card;
    }
}
