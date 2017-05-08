/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Matthias
 */
public class SpecialCard extends Card{
    
    private final int[] values;
    private int selectedValueIndex;
    
    public SpecialCard(String type, int value, int price, int[] values)
    {
        super(type, value, price);
        selectedValueIndex = 0;
        this.values = values;
    }
    
    public int[] getValues() {
        return this.values;
    }
    
    public void switchValue() {
        setValue((selectedValueIndex + 1) % values.length);
    }
}
