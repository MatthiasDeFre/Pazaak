

package gui.scenes.controllers;

import gui.SceneController;
import gui.Main;
import java.net.URL;
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




public class SideDeckController implements Initializable, _Scene {
    
    //scene controller
    SceneController controller;
     private ResourceBundle rs;
    
    
    
   
    
    //Hover sound
    final AudioClip hoverAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Hover.mp3").toExternalForm());
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());
    
    
   
    
    
    @FXML private Button btnConfirm;
    @FXML private Button btnCancel;
    //@FXML private Button btnSettings;
    @FXML private Label lblSideDeck;
     
        
    
    
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
        rs = rb;
        System.out.println(Font.loadFont(getClass().getResourceAsStream("../../assets/css/upheavtt.ttf"), 14).getName());
        
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);
        
        //assert btnConfirm != null;
        btnConfirm.setText("Language"); 
        
        //assert btnCancel != null;
        btnCancel.setText("Mute Sound");
        
        //assert btnSettings != null;
        
        assert lblSideDeck != null;
        lblSideDeck.setText("Back to main menu");
        

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
    
//btnConfirm
    
    @FXML
    public void btnConfirmClick(){
       
       clickAudioClip.play();
       controller.setScreen(Main.screen1ID);
       
    }
    
    @FXML
    public void btnConfirmEnter(){
        
       hoverAudioClip.play();
       btnConfirm.setScaleX(1.1);
       btnConfirm.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnConfirmExit(){
       
       btnConfirm.setScaleX(1);
       btnConfirm.setScaleY(1);
   
    }

//btnCancel
    
    @FXML
    public void btnCancelClick(){
     
        clickAudioClip.play();
        controller.setScreen(Main.screen2ID);
        System.out.println("s");
       
    }
    
    @FXML
    public void btnCancelEnter(){
        
       hoverAudioClip.play();
       btnCancel.setScaleX(1.1);
       btnCancel.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnCancelExit(){
       
       btnCancel.setScaleX(1);
       btnCancel.setScaleY(1);
       
        
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
       //btnSettings.setScaleX(1.1);
      // btnSettings.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnSettingsExit(){
       
      // btnSettings.setScaleX(1);
      // btnSettings.setScaleY(1);
       
        
    }
}
