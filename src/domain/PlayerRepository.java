package domain;
import domain.Player;
import exceptions.notEnoughPlayersException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import persistence.CardMapper;
import persistence.PlayerMapper;

public class PlayerRepository {
    
    //Attributes
    private List<Player> players = new ArrayList<>();
    private persistence.CardMapper cardMapper;
    private persistence.PlayerMapper playerMapper;

    //Constructors
    public PlayerRepository()
    {
        cardMapper = new CardMapper();
        playerMapper = new PlayerMapper();
        //  players = playerMapper.givePlayers();

    }

    //Make a new user methods
    
    /**
     * Method to add a new user to the database using the provided instance of {@link Player}
     * @param player Instance of player, this player will be added to the database
     */
    public void register(Player player)
    {
        //check if user Already exists

        playerMapper.addPlayer(player);
        int playerID = playerMapper.givePlayerID(player.getName());
      /*  for (Card card : player.getDeck())
        {
            cardMapper.addCard(card, playerID);
        }*/
       cardMapper.addCards(player.getDeck(), playerID);

    }

    /**
     * <pre>Method to check if the user already exists in the database
     * This method should be called before making an instance of {@link Player}</pre>
     * @param name Name of the user that needs to be checked
     */
    public void userExists(String name)
    {
        playerMapper.userExists(name);
    }

    /*      private boolean userExists(String name) {
            boolean exists = false;
            for (Player player : players)
            {
                if(player.getName().equals(name)) {
                    exists = true;
                }
            }
            return exists;
        }*/

    //Make a new match methods
    /**
     * Method to return an instance of {@link Player} with the provided name
     * @param name The name of the selected player
     * @return An instance of {@link Player}
     */
    public Player selectPlayer(String name)
    {
        return playerMapper.selectPlayer(name);
    }
    
    /**
     * <pre>Method to get all the names of the players that are in the database
     * If there are less than 2 players in the database then the method will not return the names and will instead throw a {@link notEnoughPlayersException}</pre>
     * @return List&lt;String&gt; containing the player names
     */
    public List<String> getPlayerNames() {
        List<String> names = playerMapper.getPlayerNames();
       if(names.size() < 2) {
           ResourceBundle rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
           throw new notEnoughPlayersException(rs.getString("notEnoughPlayers"));
       }
       return names;
    }
    
    /**
     * <pre>Method to set the player his credit to a new value</pre>
     * @param name Name of the player
     */
    public void setPlayerCredit(Player player) {
        playerMapper.saveCredit(player);
    }
}
    