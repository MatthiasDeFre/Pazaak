package domain;
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
}
