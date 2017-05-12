/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.image.ImageView;

/**
 *
 * @author Matthias
 */
public class CardGUI extends ImageView{
    
    private String type;
    private int value;
    private int price;
    private int [] indeces;
    private boolean interactable;
    private String url;
   
    /**
     * SideDeck constructor because of the given indeces the Card can be returned to his original position in a gridpane
     * @param imageUrl
     * @param value
     * @param type
     * @param columnIndex
     * @param rowIndex 
     */
    public CardGUI(String imageUrl, String type, int value, int columnIndex, int rowIndex)
    {
        super(imageUrl);
        this.url = imageUrl;
        this.type = type;
        this.value = value;
        this.indeces = new int[2];
        this.indeces[0] = columnIndex;
        this.indeces[1] = rowIndex;
        this.interactable = true;
    }

    /**
     * Shop Constructor
     * @param imageUrl
     * @param value
     * @param type
     * @param price 
     */
    public CardGUI(String imageUrl, String type, int value,  int price)
    {
        super(imageUrl);
        this.url = imageUrl;
        this.type = type;
        this.value = value;
        this.interactable = true;
    }
    
     /**
     * SideDeck constructor selectedCards
     * @param imageUrl
     * @param value
     * @param type
     * @param price 
     */
    public CardGUI(String imageUrl, String type, int value)
    {
        super(imageUrl);
        this.url = imageUrl;
        this.type = type;
        this.value = value;
        this.interactable = true;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int[] getIndeces()
    {
        return indeces;
    }

    public void setIndeces(int[] indeces)
    {
        this.indeces = indeces;
    }

    public boolean isInteractable()
    {
        return interactable;
    }

    public void setInteractable(boolean interactable)
    {
        this.interactable = interactable;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
    
    
    
  
    
    
}
