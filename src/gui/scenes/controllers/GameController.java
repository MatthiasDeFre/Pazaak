

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
import javafx.scene.layout.GridPane;

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
    
    
   
    
    

    @FXML private Button btnEndTurn;
    @FXML private Button btnStand;
    @FXML private GridPane player1Deck;
    @FXML private GridPane player2Deck;
    @FXML private GridPane player1SideDeck;
    @FXML private GridPane player2SideDeck; 
    @FXML private Label lblScore; 
    @FXML private Label lblPlayer1; 
    @FXML private Label lblPlayer2; 
    @FXML private Label lblPlayer1Score; 
    @FXML private Label lblPlayer2Score; 
    
    
    
    
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


    

    
    //btnEndTurn
    
    @FXML
    public void btnEndTurnClick(){
     
        clickAudioClip.play();
        //
       
    }
    
    @FXML
    public void btnEndTurnEnter(){
        
       hoverAudioClip.play();
       btnEndTurn.setScaleX(1.1);
       btnEndTurn.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnEndTurnExit(){
       
       btnEndTurn.setScaleX(1);
       btnEndTurn.setScaleY(1);
       
        
    }
    
    
    //btnStand
    
    @FXML
    public void btnStandClick(){
     
        clickAudioClip.play();
        //
       
    }
    
    @FXML
    public void btnStandEnter(){
        
       hoverAudioClip.play();
       btnStand.setScaleX(1.1);
       btnStand.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnStandExit(){
       
       btnStand.setScaleX(1);
       btnStand.setScaleY(1);
       
        
    }
}
