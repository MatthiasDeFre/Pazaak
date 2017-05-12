

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




public class MessageController implements Initializable, _Scene {
    //lagnuage
    private ResourceBundle rs;
    //scene controller
    SceneController controller;
    
    
    
    
   
    
    //Hover sound
    final AudioClip hoverAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Hover.mp3").toExternalForm());
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());
    

    
    
    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btnMain;
    @FXML private Label lblMessage;
    
    
    
     
        
    
    
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(Font.loadFont(getClass().getResourceAsStream("../../assets/css/upheavtt.ttf"), 14).getName());
        rs = rb;
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);
        

        
        //assert btnMain != null;
        
        //assert lblMessage != null;
        
        //inhoud message/knoppen hier
        
        
      
        

    }
    
    
    
    
    @Override
    public void setScreenParent(SceneController screenParent){
        
        controller = screenParent;
        lblMessage.setText(controller.message);
        btn1.setText(controller.button1);
        btn2.setText(controller.button2);
        
        
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
    
//btn1
    
    @FXML
    public void btn1Click(){
       
       clickAudioClip.play();
     //  controller.setScreen(Main.screen1ID);
      controller.loadScreen(Main.screen6ID, Main.screen6File);
       controller.setScreen(Main.screen6ID);
       
    }
    
    @FXML
    public void btn1Enter(){
        
       hoverAudioClip.play();
       btn1.setScaleX(1.1);
       btn1.setScaleY(1.1);
        
    }
    
    @FXML
    public void btn1Exit(){
       
       btn1.setScaleX(1);
       btn1.setScaleY(1);
   
    }

//btn2
    
    @FXML
    public void btn2Click(){
     
        clickAudioClip.play();
        controller.setScreen(Main.screen2ID);
        System.out.println("s");
       
    }
    
    @FXML
    public void btn2Enter(){
        
       hoverAudioClip.play();
       btn2.setScaleX(1.1);
       btn2.setScaleY(1.1);
        
    }
    
    @FXML
    public void btn2Exit(){
       
       btn2.setScaleX(1);
       btn2.setScaleY(1);
       
        
    }
    
    //btnMain
    
    @FXML
    public void btnMainClick(){
     
        clickAudioClip.play();
        controller.setScreen(Main.screen2ID);
       
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
