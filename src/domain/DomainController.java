package domain;
import domain.Player;
import exceptions.noCorrectNameException;
import java.util.Locale;
import java.util.ResourceBundle;
import persistence.*;
public class DomainController {
	private PlayerRepository players;
	private Player currentUser;
        private CardRepository cardRepository;
	public void register(String name, int birthYear) {
             //Check if user exists
/*            if(players.userExists(name)) {
                ResourceBundle rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
                throw new noCorrectNameException(rs.getString("userExists"));
            }    */
            players.userExists(name);
            currentUser = new Player(birthYear, name, cardRepository.giveStartDeck());        
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
        playerData[1] = Integer.toString(currentUser.getbirthYear());
        playerData[2] = Integer.toString(currentUser.getCredit());
        dataAndDeck[0] = playerData;
        
        for (int i = 0; i < currentUser.getDeck().size(); i++)
        {
            dataAndDeck[i+1] = new String[2];
            
dataAndDeck[i + 1][0] = currentUser.getDeck().get(i).getType();
dataAndDeck[i + 1][1] = Integer.toString(currentUser.getDeck().get(i).getValue());
            
        }

        return dataAndDeck;
	}

	public DomainController() {
		players = new PlayerRepository();
                cardRepository = new CardRepository();
              
	}
}
