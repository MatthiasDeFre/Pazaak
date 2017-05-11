

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




public class ShopController implements Initializable, _Scene {
    //lagnuage
    private ResourceBundle rs;
    //scene controller
    SceneController controller;
    
    
    
    
   
    
    //Hover sound
    final AudioClip hoverAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Hover.mp3").toExternalForm());
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());
    
    
   
    
    
    @FXML private Button btnLanguage;
    @FXML private Button btnMultiplayer;
    @FXML private Button btnSettings;
    @FXML private Label lblSettings;
     
        
    
    
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(Font.loadFont(getClass().getResourceAsStream("../../assets/css/upheavtt.ttf"), 14).getName());
        rs = rb;
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);
        
        //assert btnLanguage != null;
        btnLanguage.setText("Language"); 
        
        //assert btnMultiplayer != null;
        btnMultiplayer.setText("Mute Sound");
        
        //assert btnSettings != null;
        
        assert lblSettings != null;
        lblSettings.setText("Back to main menu");
        

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
    
//btnLanguage
    
    @FXML
    public void btnLanguageClick(){
       
       clickAudioClip.play();
       controller.setScreen(Main.screen1ID);
       
    }
    
    @FXML
    public void btnLanguageEnter(){
        
       hoverAudioClip.play();
       btnLanguage.setScaleX(1.1);
       btnLanguage.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnLanguageExit(){
       
       btnLanguage.setScaleX(1);
       btnLanguage.setScaleY(1);
   
    }

//btnMultiplayer
    
    @FXML
    public void btnMultiplayerClick(){
     
        clickAudioClip.play();
        controller.setScreen(Main.screen2ID);
        System.out.println("s");
       
    }
    
    @FXML
    public void btnMultiplayerEnter(){
        
       hoverAudioClip.play();
       btnMultiplayer.setScaleX(1.1);
       btnMultiplayer.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnMultiplayerExit(){
       
       btnMultiplayer.setScaleX(1);
       btnMultiplayer.setScaleY(1);
       
        
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
