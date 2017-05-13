

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




public class ShopController implements Initializable, _Scene {
    //lagnuage
    private ResourceBundle rs;
    //scene controller
    SceneController controller;
    
    
    
    
   
    
    //Hover sound
    final AudioClip hoverAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Hover.mp3").toExternalForm());
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());
    
    
   
    
    

    @FXML private Button btnBuy;
    @FXML private GridPane buyableCards1; //rij 1 van buyable kaarten (8 x 1)
    @FXML private GridPane buyableCards2; //rij 2 (5 x 1)
    @FXML private ImageView imgSelectedCard; //@matthias, wnr je over een kaart hovert word(t) die hier in het groot getoont
    @FXML private Label lblCredits;
    @FXML private Label lblCost;
    @FXML private Label lblExplanation;
    
    
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(Font.loadFont(getClass().getResourceAsStream("../../assets/css/upheavtt.ttf"), 14).getName());
        rs = rb;
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);
        

        

        
        //assert btnMain != null;
        


    }
    
    
    
    
    @Override
    public void setScreenParent(SceneController screenParent){
        controller = screenParent;
        
        
    };


    

    
    //btnMain
    
    @FXML
    public void btnBuyClick(){
     
        clickAudioClip.play();
        
       
    }
    
    @FXML
    public void btnBuyEnter(){
        
       hoverAudioClip.play();
       btnBuy.setScaleX(1.1);
       btnBuy.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnBuyExit(){
       
       btnBuy.setScaleX(1);
       btnBuy.setScaleY(1);
       
        
    }
}
