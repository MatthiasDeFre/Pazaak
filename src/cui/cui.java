
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import domain.DomainController;
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
        Locale currentLocale = Locale.getDefault();
        domain.DomainController dc = new DomainController();
     
        switch(s.nextInt()){
            case 1:
                //NEDERLANDS
                 currentLocale = new Locale("nl_BE");
                break;
            case 2:
                //ENGLISH
                 currentLocale = new Locale("en_US");
                break;
             case 3:
                //FRENCH
                  currentLocale = new Locale("fr_FR");
                break;
             default:
                    System.out.println(wc.getString("errorNumber"));
                    break;
                    
        }
        Locale.setDefault(currentLocale);
        ResourceBundle rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault()); 
        System.out.println(String.format(rs.getString("welcome")));
        
    switch(s.nextInt()){
        
            case 1:
                //methode voor registreer
               String name;
               int date;
                System.out.println(String.format("inputRegistreer"));
                System.out.print(String.format("name"));
                name = s.next();
                System.out.print(String.format("date"));
                date = s.nextInt();
                dc.register(name, date);
                break;
            case 2:
                //methode voor login
                System.out.printf("[WIP]");
                break;
             default:
                    System.out.println(rs.getString("errorNumber"));
                    break;
                    
        }
        
        
    }
}
