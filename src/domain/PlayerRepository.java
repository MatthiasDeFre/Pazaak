package domain;
import domain.Player;
import java.util.ArrayList;
import java.util.List;
import persistence.CardMapper;
import persistence.PlayerMapper;

public class PlayerRepository {
	private List<Player> players = new ArrayList<Player>();
        private persistence.CardMapper cardMapper;
        private persistence.PlayerMapper playerMapper;
	public void register(Player player) {
            //check if user Already exists
            if(!userExists(player.getName()))
            {
		playerMapper.addPlayer(player);
                for (Card card : player.getDeck())
            {
                cardMapper.addCard(card,player.getName());
            }
            }
            else
                throw new IllegalArgumentException("Speler bestaat al");
	}

	public PlayerRepository() {
		cardMapper = new CardMapper();
                playerMapper = new PlayerMapper();
                players = playerMapper.givePlayers();
                
	}
           private boolean userExists(String name) {
            boolean exists = false;
            for (Player player : players)
            {
                if(player.getName().equals(name)) {
                    exists = true;
                }
            }
            return exists;
        }
}
