package domain;

import java.util.List;
import exceptions.*;
import java.util.Locale;
import java.util.ResourceBundle;
import persistence.MatchMapper;
public class DomainController {
    
    //Attributes
	private PlayerRepository players;
	private Player currentUser;
        private CardRepository cardRepository;
        private Match newMatch;
        private MatchRepository matchRepository;
        private MatchMapper matchMapper;
        
        
    //Constructors
        public DomainController() {
		players = new PlayerRepository();
                cardRepository = new CardRepository();
                matchMapper = new MatchMapper();
                matchRepository = new MatchRepository();
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
    
    public Integer getPlayerCredits()
    {
       return currentUser.getCredit();
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
       List<String> names = players.getPlayerNames();
       if(!names.contains(name)) {
         ResourceBundle rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
         throw new invalidPlayerException(rs.getString("invalidPlayer"));
       }
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
        
    /**
     * Method to get the name of the player who won, and set his credit in the database
     * @return Name of the player who won
     */
    public String whoWon() {
        Player winner = newMatch.whoWon();
        players.setPlayerCredit(winner);
        return winner.getName();
    }
    
    /**
     * Method to start a new Round to the current instance of match (newMatch)
     */
    public void startNewRound() {
        newMatch.startNewRound();
    }
    
    public String[][] getRoundSituation() {
      return newMatch.getRoundSituation();
    }
    
    public void playCard(int cardIndex) {
        newMatch.playCard(cardIndex);
    }
    
    public void changeCardSign(int cardIndex) {
        currentUser.getMatchDeck(newMatch).get(cardIndex).changeSign();
    }
    
    public String[][] showPossibleChanges() {
        String[][] possibleChanges = new String[0][3];
        
       //for(Card card : currentUser.getMatchDeck(newMatch)) {      
        for (int j = 0; j < newMatch.getCurrentPlayer().getMatchDeck(newMatch).size(); j++)
        {
            Card card = newMatch.getCurrentPlayer().getMatchDeck(newMatch).get(j); 
            if (card.getType().equals("+/-"))
            {
                String[][] arrayCopy = possibleChanges;
                possibleChanges = new String[arrayCopy.length + 1][3];
                for (int i = 0; i < arrayCopy.length; i++)
                {
                    possibleChanges[i][0] = arrayCopy[i][0];
                    possibleChanges[i][1] = arrayCopy[i][1];
                    possibleChanges[i][2] = arrayCopy[i][2];
                }
                possibleChanges[arrayCopy.length + 1][0] = card.getType();
                possibleChanges[arrayCopy.length + 1][1] = String.valueOf(card.getValue());
                possibleChanges[arrayCopy.length + 1][2] = String.valueOf(j);
            }
       // }
          }
        return possibleChanges;
    }
    
    public void nextTurn() {
        newMatch.nextTurn();
    }
    
    public void freezeBoard() {
        newMatch.freezeBoard();
    }
    
    public boolean roundEnded()
    {
        return newMatch.roundEnded();
    }
    
    public boolean isAIMatch() {
        return newMatch.isAIMatch();
    }
    
    public void setAIMatch(boolean AI) {
        newMatch.setAI(AI);
    }
    
    public boolean getAIWantsNextTurn() {
        return newMatch.getAIWantsNextTurn();
    }
    
    public void buyCard(String[] availableCard)
    {
        //MAAK EEN KAART
        int cardValue = Integer.parseInt(availableCard[1]);
        
        if (availableCard[0].equals("-"))
        {
            cardValue*=-1;
        }
        
        Card card = new Card(availableCard[0],cardValue, Integer.parseInt(availableCard[2]));
        
        if(currentUser.getCredit() < card.getPrice() )
        {
            ResourceBundle rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
            throw new notEnoughCreditsException(rs.getString("notEnoughCredits"));
        }
        
        //GEEF KAART MEE AAN REPO
        cardRepository.buyCard(card,players.getPlayerId(currentUser));
        currentUser.setCredit(currentUser.getCredit()-card.getPrice());
        players.setPlayerCredit(currentUser);
        //VOEG KAART TOE AAN LOKAAL MATCHDECK OF VRAAG MATCHDECK OPNIEUW OP IN DE DATABASE
        currentUser.getDeck().add(card);
    }
    
    public void saveMatch(String matchName) { 
            matchMapper.saveMatchNoBlob(matchName, newMatch);
        
    }
    
    public void loadMatch(String matchName) {     
                newMatch = matchMapper.loadMatchNoBlob(matchName);
         
    }
    
    public String[] getSavegameNames(){
        List<String> saveGamesList = matchRepository.getSavegameNames();
        String[] saveGames = new String[saveGamesList.size()];
        
        for (int i =0; i < saveGamesList.size(); i++)
        {
            saveGames[i] = saveGamesList.get(i);
        }
        return saveGames;
    }
    
    public String[][] showBuyableCards(){
        List<Card> buyableCardList= cardRepository.showBuyableCards(currentUser.getName());
        String[][] buyableCardArray= new String[buyableCardList.size()][3];
        Card card;
        
        for (int i = 0; i < buyableCardList.size(); i++)
        {
            card=buyableCardList.get(i);
            buyableCardArray[i][0]=card.getType();
            buyableCardArray[i][1]=String.valueOf(card.getValue());
            buyableCardArray[i][2]=String.valueOf(card.getPrice());
                  
        }       
        return buyableCardArray;    
    }
    
}
