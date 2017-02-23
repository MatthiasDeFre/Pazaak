package domain;
import domain.Player;
import java.util.ArrayList;

public class PlayerRepository {
	private ArrayList<Player> players = new ArrayList<Player>();

	public void register(Player player) {
		throw new UnsupportedOperationException();
	}

	public PlayerRepository() {
		throw new UnsupportedOperationException();
	}
               public boolean userExists(String name) {
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
