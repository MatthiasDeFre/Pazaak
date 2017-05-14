

package gui.scenes.controllers;

import gui.SceneController;
import gui.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.scene.text.Font;




public class SettingsController implements Initializable, _Scene {
    
    //scene controller
    SceneController controller;
     private ResourceBundle rs;
    
    
    
   // Main Menu music
    URL mainMenuMusicURL = getClass().getResource("../../assets/sfx/music/Menu.mp3");
    Media mainMenuMusicMedia = new Media(mainMenuMusicURL.toString());
    MediaPlayer mainMenuMusic = new MediaPlayer(mainMenuMusicMedia);
    
    //Hover sound
    final AudioClip hoverAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Hover.mp3").toExternalForm());
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());
    
    
   
    
    
    @FXML private Button btnLanguage;
    @FXML private Button btnSound;
    @FXML private Button btnMain;
    @FXML private Label lblSettings;
     
        
    
    
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
        rs = rb;
        System.out.println(Font.loadFont(getClass().getResourceAsStream("../../assets/css/upheavtt.ttf"), 14).getName());
        
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);
        
        //assert btnLanguage != null;
        btnLanguage.setText(rs.getString("language")); 
        
        //assert btnSound != null;
        btnSound.setText(rs.getString("muteSound"));
        
        btnMain.setText(rs.getString("backToMain"));
        
        //assert btnMain != null;
        
        assert lblSettings != null;
        lblSettings.setText(rs.getString("settings"));
        
        

    }
    
    
    
    
    @Override
    public void setScreenParent(SceneController screenParent){
        controller = screenParent;
        controller.unloadScreen(Main.screen1ID);
        
    };


    
//btnLanguage
    
    @FXML
    public void btnLanguageClick(){
       
       clickAudioClip.play();
       
       controller.setScreen(Main.screen2ID);
       
       
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

//btnSound
    
    @FXML
    public void btnSoundClick(){
     
        //clickAudioClip.play();
        
        controller.isMuted = true; 
        controller.stopMusic();
        System.out.println("Sound muted");//rs!!
        btnSound.setText("unmute sound");
       
    }
    
    @FXML
    public void btnSoundEnter(){
        
       hoverAudioClip.play();
       btnSound.setScaleX(1.1);
       btnSound.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnSoundExit(){
       
       btnSound.setScaleX(1);
       btnSound.setScaleY(1);
       
        
    }
    
    //btnMain
    
    @FXML
    public void btnMainClick(){
     
        clickAudioClip.play();
        
        controller.loadScreen(Main.screen1ID, Main.screen1File);
        controller.setScreen(Main.screen1ID);
       
    }
    
    @FXML
    public void btnMainEnter(){
        
       hoverAudioClip.play();
       btnMain.setScaleX(1.1);
       btnMain.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnMainExit(){
       
       btnMain.setScaleX(1);
       btnMain.setScaleY(1);
       
        
    }
}
