package domain;

import java.util.List;

public class DomainController {
    
    //Attributes
	private PlayerRepository players;
	private Player currentUser;
        private CardRepository cardRepository;
        private Match newMatch;
        
        
    //Constructors
        public DomainController() {
		players = new PlayerRepository();
                cardRepository = new CardRepository();
              
	}
    //Make a new user methods
    public void register(String name, int birthYear)
    {
     
        players.userExists(name);
        currentUser = new Player(birthYear, name, cardRepository.giveStartDeck());
        players.register(currentUser);

    }

    /**
     * <pre>
     * Method for retrieving the currentPlayer data and his deck, return value 2 dimensional array
     * Index [0][] will ALWAYS contain the currentPlayer his personal data: name, date of birth
     * Index higher than 0 will result in getting the data for a card
     * [x>0][0] returns the type
     * [x>0][1] returns the value
     * </pre>
     *
     * @return Array containing the player data en deck
     */
    public String[][] getPlayerData()
    {

        String[][] dataAndDeck = new String[currentUser.getDeck().size() + 1][];
        String[] playerData = new String[3];
        String[] cardData = new String[2];

        playerData[0] = currentUser.getName();
        playerData[1] = Integer.toString(currentUser.getbirthYear());
        playerData[2] = Integer.toString(currentUser.getCredit());
        dataAndDeck[0] = playerData;

        for (int i = 0; i < currentUser.getDeck().size(); i++)
        {
            dataAndDeck[i + 1] = new String[2];

            dataAndDeck[i + 1][0] = currentUser.getDeck().get(i).getType();
            dataAndDeck[i + 1][1] = Integer.toString(currentUser.getDeck().get(i).getValue());

        }

        return dataAndDeck;
    }
        
        
        
    //Match methods
    public void makeMatch()
    {
        newMatch = new Match();
    }

    public void selectPlayer(String name)
    {
        newMatch.addPlayer(players.selectPlayer(name));
    }
    
    public int getAmountPlayersStillNeeded() {
        return newMatch.getAmountPlayersStillNeeded();
    }
 
    public String[] getPlayerNames()
    {
        List<String> names = players.getPlayerNames();
        String[] domainNames = new String[names.size()];
        for (int i = 0; i < names.size(); i++)
        {
            domainNames[i] = names.get(i);
        }
        return domainNames;
    }
    
    public String[] getPlayersWithoutMatchDeck() {
        
        List<String> playerNamesList = newMatch.getPlayersWithoutMatchDeck();
        String[] playerNames = new String[playerNamesList.size()];
        for (int i = 0; i < playerNamesList.size(); i++)
        {
            playerNames[i] = playerNamesList.get(i);
        }
        return playerNames;
    }
    
    public void selectPlayerWithoutMatchDeck(String name) {
        currentUser = players.selectPlayer(name);
    }
    
    public String[][] getPlayerCards() {
        List<Card> cardList = currentUser.getDeck();
        String[][] cardArray = new String[cardList.size()][2];
        for (int i = 0; i < cardList.size(); i++)
        {
            cardArray[i][0] = cardList.get(i).getType();
            cardArray[i][1] = Integer.toString(cardList.get(i).getValue());
        }
        return cardArray;
    }
    
    public void cancelMatch() {
        newMatch = null;
    }
    
    

        
        
	
}
