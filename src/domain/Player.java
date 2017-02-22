package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import exceptions.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Player {
	private LocalDateTime dateOfBirth;
	private String name;
	private int credits;
	private ArrayList<Card> deck = new ArrayList<>();

	public Player(LocalDateTime dateOfBirth, String name) {
		throw new UnsupportedOperationException();
	}

	public void makeStartdeck() {
		final String[] type = {"+" , "+", "+", "+","+/-","+/-","-","-","-","-"};
                final int [] value = {2,4,5,6,1,3,1,2,3,5};
                Card newCard;
                for (int i = 0; i < 10; i++) {
                newCard = new Card(type[i], value[i]);
                deck.add(newCard);
                }
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDateOfBirth(LocalDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDateTime getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getCredits() {
		return this.credits;
	}
        public void checkName(String name) {
            if(name == null || name.isEmpty()) {
              ResourceBundle rs = ResourceBundle.getBundle("resources/Lang", Locale.getDefault());
                throw new noCorrectNameException(rs.getString("noCorrectName"));
            }
        }
        public void checkDateOfBirth(LocalDateTime dateOfBirth) {
            
        }
          
 /*   {
        ResourceBundle rs = ResourceBundle.getBundle("resources/Lang", Locale.GERMANY);
        System.out.println(rs.getString("s1"));
             ResourceBundle rs1 = ResourceBundle.getBundle("resources/Lang", Locale.US);
        System.out.println(rs1.getString("s1"));
    }*/
                   
        
}