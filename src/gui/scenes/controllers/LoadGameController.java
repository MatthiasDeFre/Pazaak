

package gui.scenes.controllers;

import gui.SceneController;
import gui.Main;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.scene.text.Font;





public class LoadGameController implements Initializable, _Scene {
    
    //scene controller
    SceneController controller;
     private ResourceBundle rs;
    
    
   
    
    //Hover sound
    final AudioClip hoverAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Hover.mp3").toExternalForm());
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());
    
    
   
    
    
    @FXML private Button btnConfirm;

    @FXML private Button btnMain;
    @FXML private Label lblSettings;

    @FXML private ListView list1;

     ObservableList<String> playerList;
     

    
    
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
        rs = rb;
        System.out.println(Font.loadFont(getClass().getResourceAsStream("../../assets/css/upheavtt.ttf"), 14).getName());
        
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);
        
        //assert btnConfirm != null;
        btnConfirm.setText(rs.getString("loadGame")); 
        

        
        btnMain.setText(rs.getString("backToMain"));
        
        //assert btnMain != null;
        
        assert lblSettings != null;
        lblSettings.setText(rs.getString("loadGame"));
        
         
        

    }
    
    
    
    
    @Override
    public void setScreenParent(SceneController screenParent){
        
        controller = screenParent;
       
        controller.unloadScreen(Main.screen1ID);
        playerList = FXCollections.observableArrayList(controller.getDC().getSavegameNames());

        

        
        
        list1.setItems(playerList);
          
          
          
    };


    
//btnConfirm
    
    @FXML
    public void btnConfirmClick(){
       
       clickAudioClip.play();
       
       //laad geselecteerd spel
        
       controller.getDC().loadMatch(list1.getSelectionModel().getSelectedItem().toString());
       controller.loadScreen(Main.screen7ID, Main.screen7File);
       controller.setScreen(Main.screen7ID);
       
       
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
