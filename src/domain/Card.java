package domain;

import java.util.Objects;

/**
 * <pre>
 * Class to make instances of Cards
 * Properties:
 * String type
 * int value
 * 
 * Methods:
 * setType {@link Card.setType(String type}
 * getype {@link Card.getType}
 * setValue {@link Card.setValue(int value}
 * getValue {@link Card.getValue(}
 * @author Team-07 </pre>
 */
public class Card {
    //Attributes
    private String type;
    private int value;

    //Constructors
    public Card(String type, int value)
    {
        this.type = type;
        this.value = value;
    }
    //Attribute methods
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return this.type;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }

    public void changeSign() {
        this.value *= -1;
    }
    
    @Override
    public boolean equals(Object other)
    {
          if (!(other instanceof Card)) {
        return false;
    }
    Card that = (Card) other;
    return this.type.equals(that.type) && this.value == that.value; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.type);
        hash = 53 * hash + this.value;
        return hash;
    }
    
    
}
