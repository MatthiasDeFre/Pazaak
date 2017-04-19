/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startUp;

import domain.DomainController;
import gui.scenes.LanguageSelection;
import gui.scenes.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;


public class StartUpGUI
{

    public static void main(String[] args)
    {
        DomainController dc = new DomainController();
        Scene scene = new Scene(new LanguageSelection(dc), 420, 69);
        
        
//        if (gebruiker nog geen taal heeft geselcteerd)
//        {
//            Scene scene = new Scene(new LanguageSelection(dc), 420, 69);
//        }
//        else
//        {
//              Scene scene = new Scene(new MainMenu(dc), 420, 69);  
//        }        
        
        scene.getStylesheets().add("/gui/styles.css");
        stage.setScene(scene);
        stage.show();
    }

}
