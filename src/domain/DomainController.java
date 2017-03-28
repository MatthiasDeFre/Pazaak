package domain;

import java.util.ArrayList;
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
        
     /**
      * <pre>Method to register a user in the database
      *  User will only be added if the user doesn't already exist in the database</pre>
      * @param name The name of the player
      * @param birthYear The year of birth of the Player
      */
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
    /**
     * Method to make a new Match and give the value to newMatch property
     */
    public void makeMatch()
    {
        newMatch = new Match();
    }

    /**
     * <pre>Method to add a player to a match using the PlayerRepository to get the Player object</pre>
     * @param name Name of the player that needs to be added to the match 
     */
    public void selectPlayer(String name)
    {
        newMatch.addPlayer(players.selectPlayer(name));
    }
    
    /**
     * Method to get the amount of players still needed to start the match
     * @return int value of the amount of players needed
     */
    public int getAmountPlayersStillNeeded() {
        return newMatch.getAmountPlayersStillNeeded();
    }
 
    /**
     * <pre>
     * ############# Herbekijk deze methode ############
     * Method to get the names of the players who are in the database
     * </pre>
     * @return String[] containing the names of the players (database)
     */
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
    
    /**
     * Method to get the names of the players that were chosen for the current match
     * @return String[] containing the names of the players (newMatch)
     */
    public String[] getChosenPlayerNames(){
        String[] chosenPlayers = newMatch.getChoosenPlayers();
        return chosenPlayers;
    }
    
    /**
     * Method to get the names of the players who don't have a matchdeck yet for the currentMatch
     * @return String[] containt the names of the players without a matchdeck (newMatch)
     */
    public String[] getPlayersWithoutMatchDeck() {
        
        List<String> playerNamesList = newMatch.getPlayersWithoutMatchDeck();
        String[] playerNames = new String[playerNamesList.size()];
        for (int i = 0; i < playerNamesList.size(); i++)
        {
            playerNames[i] = playerNamesList.get(i);
        }
        return playerNames;
    }
    
    /**
     * <pre>Method to select a player without a matchdeck
     * The value of currentUser is set to a the selected player
     * The domaincontroller can now use currentUser to give the player a matchdeck and retrieve the players personal information</pre>
     * @param name Name of the player that doesn't have a matchdeck
     */
    public void selectPlayerWithoutMatchDeck(String name) {
     //   currentUser = players.selectPlayer(name);
         currentUser = newMatch.selectPlayer(name);
       
    }
    
    //Gebruiken we dit?
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
    
    /**
     * Method to cancel the match and destroy the newMatch instance
     */
    public void cancelMatch() {
        newMatch = null;
    }
    
    
    //make matchdeck Method
    
    /**
     * <pre>Method to make a matchdeck with the provided String[][] of selectedCards
     * The currentUser gets attributed a matchdeck</pre>
     * @param selectedCards The cards that the player want to be part of his matchdeck
     */
     public void makeMatchDeck(String[][] selectedCards)
    {
       // newMatch.addMatchDeckToPlayer(currentUser, selectedCards);
        currentUser.addMatchDeck(newMatch, selectedCards);
    }
    
     /**
      * Method to get the cards that are available to the currentUser
      * @param selectedCards The cards that the player already has selected
      * @return String[][] containing the information of the cards, for more information on array data {@link getPlayerData()}
      */
    public String[][] showAvailableCards(String[][] selectedCards)
    {
        return currentUser.showAvailableCards(selectedCards);
    }
    
    
    //Match started methods
    public boolean matchEnded() {
        return newMatch.matchEnded();   
    }
    
    /**
     * Method get the winnerData of the game
     * @return String[],index0=playerName,index1=credit
     */
    public String[] getWinnerData()
    {
        String[] winnerData=new String[2];
        
        currentUser= newMatch.whoWon();
        winnerData[0]=currentUser.getName();
        winnerData[1]=Integer.toString(currentUser.getCredit());
        
        return winnerData;
    }
        
}
