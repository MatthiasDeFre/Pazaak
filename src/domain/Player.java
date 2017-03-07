package domain;

import java.util.ArrayList;
import exceptions.*;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Player {
	private int birthYear;
	private String name;
	private int credit;
	private List<Card> deck = new ArrayList<>();

	public Player(int birthYear, String name, List<Card> startDeck) {
		checkName(name);
                checkDateOfBirth(birthYear);
                this.birthYear=birthYear;
                this.name=name;
                this.credit =0;
                deck = startDeck;
          
                
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

	public List<Card> getDeck() {
		return deck;
	}

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
        
        //Method to check if the name satisfies the Domain rules
        //First: check if the name isn't empty or null
        //Second: check if name length is more than 3
        //Third: check if name contains special chars
        //Fourth: check if name doesn't have number as first char
        private void checkName(String name) {
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
        //Method to check yearOfBirth
        //Checks if yearOfBirth satisfies the Domain Rules
        private void checkDateOfBirth(int yearOfBirth) {
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
                   
        
}