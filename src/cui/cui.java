/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Jenz
 */

// VOORWOORD
// 
// Alle methoden die nog niet in het domain zitten zullen worden aangeduid in comment
//

public class cui
{
    public static void main(String[] args)
    {
        
        ResourceBundle rs = ResourceBundle.getBundle("resources/Lang", Locale.getDefault());
        System.out.println(rs.getString("selectLanguage"));
    }
}
