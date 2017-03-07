package domain;
import domain.Player;
import java.util.ArrayList;
import java.util.List;
import persistence.CardMapper;
import persistence.PlayerMapper;

public class PlayerRepository {
	private List<Player> players = new ArrayList<>();
        private persistence.CardMapper cardMapper;
        private persistence.PlayerMapper playerMapper;
	public void register(Player player, int [] ids) {
            //check if user Already exists
            
		playerMapper.addPlayer(player);
            int playerID =  playerMapper.givePlayerID(player.getName());
                for (int id : ids)
                {
                cardMapper.addCard(id, playerID);
                }
            
         
	}

	public PlayerRepository() {
		cardMapper = new CardMapper();
                playerMapper = new PlayerMapper();
              //  players = playerMapper.givePlayers();
                
	}
        public void userExists(String name) {
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
}
