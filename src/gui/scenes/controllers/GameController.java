

package gui.scenes.controllers;

import gui.SceneController;
import gui.Main;
import java.net.URL;
import java.security.interfaces.RSAKey;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.media.AudioClip;

import javafx.scene.text.Font;




public class GameController implements Initializable, _Scene {
    //lagnuage
    private ResourceBundle rs;
    //scene controller
    SceneController controller;
    
    
    
    
   
    
    //Hover sound
    final AudioClip hoverAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Hover.mp3").toExternalForm());
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());
    
    
   
    
    

    @FXML private Button btnSettings;

     
        
    
    
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(Font.loadFont(getClass().getResourceAsStream("../../assets/css/upheavtt.ttf"), 14).getName());
        rs = rb;
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);
        
        //assert btnLanguage != null;

        
       
        

    }
    
    
    
    
    @Override
    public void setScreenParent(SceneController screenParent){
        controller = screenParent;
        
        
    };

    @FXML
    public void goToScreen2(ActionEvent event){
        
       controller.setScreen(Main.screen1ID);
       
       
    }
    
    @FXML
    public void goToScreen3(ActionEvent event){
       System.out.println("ssd?");
        System.out.println("s");
       
    }
    

    
    //btnSettings
    
    @FXML
    public void btnSettingsClick(){
     
        clickAudioClip.play();
        controller.setScreen(Main.screen2ID);
       
    }
    
    @FXML
    public void btnSettingsEnter(){
        
       hoverAudioClip.play();
       btnSettings.setScaleX(1.1);
       btnSettings.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnSettingsExit(){
       
       btnSettings.setScaleX(1);
       btnSettings.setScaleY(1);
       
        
    }
}
