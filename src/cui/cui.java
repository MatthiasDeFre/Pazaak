/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

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
        Scanner s = new Scanner(System.in);
        ResourceBundle wc= ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
        System.out.println(String.format(wc.getString("selectLanguage")));
        
        switch(s.nextInt()){
            case 1:
                Locale.setDefault(Locale.US);
                break;
            case 2:
                Locale.setDefault(Locale.);
                break;
             case 3:
                Locale.setDefault(Locale.FRANCE);
                break;
             default:
                    System.out.println(wc.getString("errorNumber"));
                    break;
                    
        }
        ResourceBundle rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
        System.out.println(rs.getString("welcome"));
    }
}
