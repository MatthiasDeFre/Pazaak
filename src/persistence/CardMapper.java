package persistence;

import domain.Card;
import java.lang.reflect.Array;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardMapper
{

    public void addCard(int Cardid, int playerID)
    {
        try (java.sql.Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL))
        {
            PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g07.Card (P_ID, Card_ID) VALUES (?,?)");
            query.setInt(1, playerID); 
            query.setInt(2, Cardid);
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

    public int[] getIDS()
    {
       int amount=0;
       int[] ids;
      try (java.sql.Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL))
        {
          PreparedStatement query = conn.prepareStatement("SELECT count(Card_ID) as amount FROM ID222177_g07.CardType");
         try (ResultSet rs = query.executeQuery())
            { while(rs.next()) {
                
          
                amount = rs.getInt("amount");
                  }
            }
           query = conn.prepareStatement("SELECT Card_ID FROM ID222177_g07.CardType");
           ids = new int[amount];
          try (ResultSet rs = query.executeQuery())
            {
                int counter=0;
                while(rs.next()) {
                    ids[counter] = rs.getInt("Card_ID");
                    counter++;
                }
            }
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        return ids;
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
