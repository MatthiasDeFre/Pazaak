package domain;
import domain.Player;
import exceptions.noCorrectNameException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import persistence.*;
public class DomainController {
	private PlayerRepository players;
	private Player currentUser;

	public void register(String name, LocalDateTime dateOfBirth) {
             //Check if user exists
            if(players.userExists(name)) {
                ResourceBundle rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
                throw new noCorrectNameException(rs.getString("userExists"));
            }    
            currentUser = new Player(dateOfBirth, name);
            players.register(currentUser);
	}

	public String[][] getPlayerData()
    {
        //Methode for retrieving the currentPlayer data and his deck, return value 2 dimensional array
        //Index [0][] will ALWAYS contain the currentPlayer his personal data: name, date of birth
        //Index higher than 0 will result in getting the data for a card
        //[x>0][0] returns the type
        //[x>0][1] returns the value
        String[][] dataAndDeck = new String[currentUser.getDeck().size() + 1][];
        String[] playerData = new String[3];
        String[] cardData = new String[2];
        playerData[0] = currentUser.getName();
        playerData[1] = currentUser.getDateOfBirth().toString();
        playerData[2] = String.valueOf(currentUser.getCredits());
        dataAndDeck[0] = playerData;
        for (int i = 0; i < currentUser.getDeck().size() - 1; i++)
        {
            cardData[0] = currentUser.getDeck().get(i).getType();
            cardData[1] = String.valueOf(currentUser.getDeck().get(i).getValue());
            dataAndDeck[i + 1] = cardData;
        }

        return dataAndDeck;
	}

	public DomainController() {
		CardMapper cardMapper = new CardMapper();
                PlayerMapper playerMapper = new PlayerMapper();
              
	}
}
