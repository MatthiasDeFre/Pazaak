
import java.time.LocalDateTime;
import persistence.*;
public class DomainController {
	private PlayerRepository players;
	private Player currentUser;

	public void register(String name, LocalDateTime dateOfBirth) {
		throw new UnsupportedOperationException();
	}

	public String[][] getPlayerDate() {
		String[][] dataAndDeck = new String[2][];
                String[] playerData = new String[3];
                String[][] playerDeck = new String[currentUser.getDeck().length][];
                playerData[0] = currentUser.getName();
                playerData[1] = currentUser.getDateOfBirth().toString();
                playerData[2] = String.valueOf(currentUser.getCredits());
                dataAndDeck[0] = playerData;
                for (int i = 0; i < currentUser.getDeck().length - 1; i++)
              {      
             playerDeck[i][0] =   currentUser.getDeck()[i].getType();
             playerDeck[i][1] =   String.valueOf(currentUser.getDeck()[i].getValue());        
            }
                dataAndDeck[1] = playerDeck;
                return dataAndDeck;
	}

	public DomainController() {
		CardMapper cardMapper = new CardMapper();
                PlayerMapper playerMapper = new PlayerMapper();
	}
}