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

    /**
     * <pre>Method to add a card to the database using an instance of Card</pre>
     * @param card Card object (with value and type)
     * @param playerID PlayerID needed for FK in the database
     */
    public void addCard(Card card, int playerID)
    {   
        int CardID = getCardID(card);
        try (java.sql.Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL))
        {
            PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g07.Card (P_ID, Card_ID) VALUES (?,?)");
            query.setInt(1, playerID); 
            query.setInt(2, CardID);
            query.executeUpdate();
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    /**
     * <pre>Method to return the startDeck from the database</pre>
     * @return List of Cards
     */
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
    
    /**
     * <pre>Method to return the cardID from a given instance of Card, using the value and type from the card object</pre>
     * @param card Card object (with value and type)
     * @return The id of the given card object
     */
    public int getCardID(Card card) {
        int cardID=0;
        try (java.sql.Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL))
        {
            PreparedStatement query = conn.prepareStatement("SELECT Card_ID FROM ID222177_g07.CardType WHERE value = ? AND type = ?");
            query.setInt(1, card.getValue());
            query.setString(2, card.getType());
            try (ResultSet rs = query.executeQuery())
            {
                while (rs.next())
                {
                    cardID = rs.getInt("Card_ID");
                }
            }
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        return cardID;
    }

    //Versie 1 [not needed atm]
   /* public int[] getIDS()
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
    }*/
 /**
  * Method to get the card of the provided player name
  * @param name The name of the player
  * @return List of the player's cards
  */
    public List<Card> giveCards(String name)
    {
        List<Card> cards = new ArrayList<>();

        try (java.sql.Connection conn = DriverManager.getConnection(persistence.Connection.JDBC_URL))
        {
            PreparedStatement query = conn.prepareStatement("SELECT value, type FROM Card INNER JOIN CardType ON Card.Card_ID = CardType.Card_ID INNER JOIN Player ON Player.P_ID = Card.P_ID WHERE playerName = ?");
            query.setString(1, name);
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
