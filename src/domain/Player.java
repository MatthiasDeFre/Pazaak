package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import exceptions.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Player {
	private LocalDateTime dateOfBirth;
	private String name;
	private int credits;
	private List<Card> deck = new ArrayList<>();

	public Player(LocalDateTime dateOfBirth, String name) {
		checkName(name);
                checkDateOfBirth(dateOfBirth);
                this.dateOfBirth=dateOfBirth;
                this.name=name;
                
                
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

	public List<Card> getDeck() {
		return deck;
	}

	public void setDateOfBirth(LocalDateTime dateOfBirth) {
                checkDateOfBirth(dateOfBirth);
		this.dateOfBirth = dateOfBirth;
                
	}

	public LocalDateTime getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setName(String name) {
                checkName(name);
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
        private void checkName(String name) {
             ResourceBundle rs = ResourceBundle.getBundle("resources/Lang", Locale.getDefault());
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
            p=Pattern.compile("[0-9 ]");
            m=p.matcher(name.substring(0,0));
            boolean c=m.find();
            if(c)
            {
                throw new noCorrectNameException(rs.getString("noNumberAsFirstCharacter"));
            }
           
        }
        private void checkDateOfBirth(LocalDateTime dateOfBirth) {
            
        }
          
 /*   {
        ResourceBundle rs = ResourceBundle.getBundle("resources/Lang", Locale.GERMANY);
        System.out.println(rs.getString("s1"));
             ResourceBundle rs1 = ResourceBundle.getBundle("resources/Lang", Locale.US);
        System.out.println(rs1.getString("s1"));
    }*/
                   
        
}