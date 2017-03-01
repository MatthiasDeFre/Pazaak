
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import domain.DomainController;
import java.util.Arrays;
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
    private DomainController dc;
    public cui(DomainController dc)
    {
        this.dc = dc;
    }
    
    public void startRegistration(){
          Scanner s = new Scanner(System.in);

        
   //  ResourceBundle wc = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
   // System.out.println(String.format(wc.getString("selectLanguage")));
        Locale currentLocale = Locale.getDefault();
        ResourceBundle rs; 
        System.out.println("For English press 1 \nVoor Nederlands druk op 2 \nPour Français appuyez 3.");
        switch(s.nextInt()){
             case 1:
                //ENGLISH
                 currentLocale = new Locale("en_US");
                break;
            case 2:
                //NEDERLANDS
                 currentLocale = new Locale("nl_BE");
                break;
           
             case 3:
                //FRENCH
                  currentLocale = new Locale("fr_FR");
                break;
             default:
                    System.out.println("error");
                    break;
                    
        }
        Locale.setDefault(currentLocale);
        rs  = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
       
        System.out.println(String.format(rs.getString("welcome")));
    
        String name;
        int date;
    switch(s.nextInt()){
        //Test
            case 1:
                //methode voor registreer
            
                System.out.println(String.format(rs.getString("inputRegister")));
                System.out.print(String.format(rs.getString("name")));
                name = s.next();
                System.out.print(String.format(rs.getString("date")));
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
    
    System.out.println(rs.getString("yourCards"));
    System.out.println(String.format(giveCards()));
    }
        public String giveCards(){
            String back = "";
            String[][] arr;
            arr = dc.getPlayerData();
            for (int i = 1; i <= arr.length -1; i++)
            {
                 back += arr[i][0];
                 back += arr[i][1];
                 back += " ";
            }
            return back;            
        }    
}
    
    
      

