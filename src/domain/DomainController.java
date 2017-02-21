package domain;
import domain.Player;
import java.time.LocalDateTime;
import java.util.Locale;
import persistence.*;
public class DomainController {
	private PlayerRepository players;
	private Player currentUser;

	public void register(String name, LocalDateTime dateOfBirth) {
		throw new UnsupportedOperationException();
	}

	public String[][] getPlayerDate()
    {
        String[][] dataAndDeck = new String[currentUser.getDeck().length + 1][];
        String[] playerData = new String[3];
        String[] cardData = new String[2];
        playerData[0] = currentUser.getName();
        playerData[1] = currentUser.getDateOfBirth().toString();
        playerData[2] = String.valueOf(currentUser.getCredits());
        dataAndDeck[0] = playerData;
        for (int i = 0; i < currentUser.getDeck().length - 1; i++)
        {
            cardData[0] = currentUser.getDeck()[i].getType();
            cardData[1] = String.valueOf(currentUser.getDeck()[i].getValue());
            dataAndDeck[i + 1] = cardData;
        }

        return dataAndDeck;
	}

	public DomainController(Locale currentLocale) {
		CardMapper cardMapper = new CardMapper();
                PlayerMapper playerMapper = new PlayerMapper();
                Locale.setDefault(currentLocale);
	}
}