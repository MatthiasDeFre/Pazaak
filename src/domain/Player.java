package domain;

import java.util.ArrayList;
import exceptions.*;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * Properties:
 * int birthYear
 * String name
 * int credit
 * List &lt;{@link Card}&gt; deck
 * {@link Matchdeck} matchDeck
 * 
 * Methods:
 * getDeck {@link getDeck()}
 * setDateOfBirth {@link setDateOfBirth(int birthYear)}
 * 
 * </pre>
 * @author Matthias
 */
public class Player{
    
	private int birthYear;
	private String name;
	private int credit;
	private List<Card> deck = new ArrayList<>();
        private MatchDeck matchDeck;

        
        /** 
         * Constructor used to make a Player who isn't in the database
         * @param birthYear
         * @param name
         * @param startDeck 
         */
	public Player(int birthYear, String name, List<Card> startDeck) {
		checkName(name);
                checkDateOfBirth(birthYear);
                this.birthYear=birthYear;
                this.name=name;
                this.credit =0;
                deck = startDeck;
          
                
	}
        public Player(int credit, List<Card> collection, String name, int birthYear) {
          /*  this.credit = credit;
            deck = collection;
            this.name = name;
            this.birthYear = birthYear;*/
          this(credit, name, birthYear);
          deck = collection;
        }
        
        public Player(int credit, String name, int birthYear) {
                this.birthYear=birthYear;
                this.name=name;
                this.credit =0;
        }
        
        //Method to generate the player's start deck / collection
/*	public void makeStartdeck() {
		final String[] type = {"+" , "+", "+", "+","+/-","+/-","-","-","-","-"};
                final int [] value = {2,4,5,6,1,3,1,2,3,5};
                Card newCard;
                for (int i = 0; i < 10; i++) {
                newCard = new Card(type[i], value[i]);
                deck.add(newCard);
                }
	}*/

        /**
         * Method to get player's current deck
         * @return List of cards
         */
	public List<Card> getDeck() {
		return deck;
	}

        /**
         * Method to set the Player's year of birth
         * @param birthYear Full year of birth, 4 number
         */
	public void setDateOfBirth(int birthYear) {
                checkDateOfBirth(birthYear);
		this.birthYear = birthYear;
                
	}

	public int getbirthYear() {
		return this.birthYear;
	}

	public void setName(String name) {
                checkName(name);
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getCredit() {
		return this.credit;
	}
        
        public MatchDeck getMatchDeck() {
            return matchDeck;
        }
        
      
     /**
     * <pre>Method to check if the name satisfies the Domain rules
     * First: check if the name isn't empty or null 
     * Second: check if name length is more than 3
     * Third: check if name contains special chars
     * Fourth: check if name doesn't have number as first char
     * </pre>
     * @param name Should satisfy the Domain Rules
     * @throws noCorrectNameException if name doesn't satisfy the domain rules
     */
        private void checkName(String name) throws noCorrectNameException{
             ResourceBundle rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
            if(name == null || name.isEmpty()) {
             
                throw new noCorrectNameException(rs.getString("noCorrectName"));
            }
            if(name.length()<3)
            {
                
                throw new noCorrectNameException(rs.getString("lengthLessThanThree"));
            }
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(name);
            boolean b = m.find();

            if (b)
            {
               throw new noCorrectNameException(rs.getString("containSpecialCharacters"));
            }
            p=Pattern.compile("[0-9 ]", Pattern.CASE_INSENSITIVE);
            m=p.matcher(name.substring(0,1));
            boolean c=m.find();
            if(c)
            {
                throw new noCorrectNameException(rs.getString("noNumberAsFirstCharacter"));
            }
           
        }
     
        /**
         *    Method to check yearOfBirth
         *    Checks if yearOfBirth satisfies the Domain Rules
         * @param yearOfBirth Should be a number only (not a full date)
         * @throws noCorrectBirthyearException if yearOfBirth doesn't satisfies the Domain Rules
         */
        private void checkDateOfBirth(int yearOfBirth) throws noCorrectBirthyearException{
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            if(!(currentYear - yearOfBirth <= 99 && currentYear - yearOfBirth >= 3)) {
                throw new noCorrectBirthyearException("noCorrectBirthyear");
            }
            
        }
          
 /*   {
        ResourceBundle rs = ResourceBundle.getBundle("resources/Lang", Locale.GERMANY);
        System.out.println(rs.getString("s1"));
             ResourceBundle rs1 = ResourceBundle.getBundle("resources/Lang", Locale.US);
        System.out.println(rs1.getString("s1"));
    }*/
        
      //MatchDeck methods
    /**
     * <pre>Method to add a matchdeck with the provided String[][] of selectedCards
     * The currentUser gets attributed a matchdeck</pre>
     * @param selectedCards The cards that the player want to be part of his
     * @param match To make sure the matchdeck is only valid for the current match
         */
       public void addMatchDeck(Match match, String[][] selectedCards) {
           List<Card> selectedCardsList = new ArrayList<>();
           
           for (int i = 0; i < selectedCards.length; i++)
           {
               Card newCard = new Card(selectedCards[i][0], Integer.parseInt(selectedCards[i][1]));
               selectedCardsList.add(newCard);
           }
           long seed = System.nanoTime();
           Collections.shuffle(selectedCardsList, new Random(seed));
           Collections.shuffle(selectedCardsList, new Random(seed));
           selectedCardsList.remove(selectedCardsList.size() - 1);
           selectedCardsList.remove(selectedCardsList.size() - 1);
           this.matchDeck = new MatchDeck(match, selectedCardsList);
       } 
       
       public void addMatchDeck(Match match, List<Card> cards) {
           this.matchDeck = new MatchDeck(match, cards);
       }
       
       public List<Card> getMatchDeck(Match match) {
           return matchDeck.getCards();
       }
                       
       public String[][] showAvailableCards(String[][] selectedCards)
       {
           String cards[][]=new String[deck.size() -selectedCards.length][];
           List <Card> deckCopy= new ArrayList<>(deck);                    
           for (int i = 0; i < selectedCards.length; i++) {
               //kaart word toegevoegd in de methode als hij niet in het deck zit
               if(deckCopy.contains(new Card(selectedCards[i][0],Integer.parseInt(selectedCards[i][1]))))
               {
                   deckCopy.remove(new Card(selectedCards[i][0],Integer.parseInt(selectedCards[i][1])));
               }                                                
           }
           for (int i = 0; i < deckCopy.size(); i++) 
           {
               cards[i] = new String[2];
               cards[i][0]=deckCopy.get(i).getType();
               cards[i][1]=Integer.toString(deckCopy.get(i).getValue());
           }
           
           return cards;
       }

    @Override
    public boolean equals(Object other)
    {
         if (!(other instanceof Player)) {
        return false;
    }
    Player that = (Player) other;
    return this.name.equals(that.name);
       
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + this.birthYear;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + this.credit;
        hash = 29 * hash + Objects.hashCode(this.deck);
        return hash;
    }

  
       
        
}