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
public class Card{
    //Attributes
    private String type;
    private int value;
    private int price;

    public Card(String type, int value, int price)
    {
        this(type, value);
        this.price = price;
    }
    
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

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public String getType()
    {
        return this.type;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    /**
     * Method to give the the signed representation of the Card value incase the Card is a +/- else it returns the unsigned representation of it
     * @return The value of the Card 
     */
    public int getValue()
    {
        if(getType().contains("+/-")) {
            return getTrueValue();
        }
        return Math.abs(this.value);
    }
    
    /**
     * Method to give the the signed representation of the Card value
     * @return The value of the Card 
     */
    public int getTrueValue() {
        return this.value;
    }

    /**
     * Method to change the sign of the Card (Should only be used on +/- Cards)
     */
    public void changeSign() {
        this.value *= -1;
    }
    
    /**
     * Method to change the value of the Card (Should only be used on cards with multiple values)
     */
    public void changeValue() {
        String[] values = this.type.split("/+///-");
        if(this.value == Integer.parseInt(values[0])) {
           this.value = Integer.parseInt(values[1]);
        } else {
          this.value = Integer.parseInt(values[0]);
        }
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
