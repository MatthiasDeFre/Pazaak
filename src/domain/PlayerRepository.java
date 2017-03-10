package domain;
import domain.Player;
import exceptions.notEnoughPlayers;
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
    public void register(Player player)
    {
        //check if user Already exists

        playerMapper.addPlayer(player);
        int playerID = playerMapper.givePlayerID(player.getName());
        for (Card card : player.getDeck())
        {
            cardMapper.addCard(card, playerID);
        }

    }

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
    public Player selectPlayer(String name)
    {
        return playerMapper.selectPlayer(name);
    }
    
    public List<String> getPlayerNames() {
        List<String> names = playerMapper.getPlayerNames();
       if(names.size() < 2) {
           ResourceBundle rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
           throw new notEnoughPlayers(rs.getString("notEnoughPlayers"));
       }
       return names;
    }
}
    