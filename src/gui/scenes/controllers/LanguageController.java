

package gui.scenes.controllers;

import gui.Main;
import gui.SceneController;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;

public class LanguageController implements Initializable , _Scene {
 private ResourceBundle rs;
    SceneController controller;
    Locale currentLocale = Locale.getDefault();
    
    @FXML private ImageView imgNL;
    @FXML private ImageView imgEN;
    @FXML private ImageView imgFR;
    @FXML private Label lblLang;
    
    
    Image cursor1 = new Image(getClass().getResourceAsStream("../../assets/img/menu/cursor.png"));
    Image cursor2 = new Image(getClass().getResourceAsStream("../../assets/img/menu/cursor2.png"));
    
    
    
    //hover
    final AudioClip hoverAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Hover.mp3").toExternalForm());
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblLang.setText("");
        Font.loadFont(getClass().getResourceAsStream("../../assets/css/upheavtt.ttf"), 14).getName();
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);
        
    //System.out.println(ImageCursor.getBestSize(0, 0));
        
    }
    
    @Override
    public void setScreenParent(SceneController screenParent){
        controller = screenParent;
        controller.unloadScreen(Main.screen1ID);
        controller.setCursor(new ImageCursor(cursor1));
    }

    @FXML
    private void goToScreen1(ActionEvent event){
       
       
    }
    
    @FXML
    private void goToScreen3(ActionEvent event){
       controller.setScreen(Main.screen3ID);
    }
    
    
    //NL
    @FXML
    private void clickNL(){
       //hier handmatig laden anders start muziek al
       
       controller.playMusic();
       
       clickAudioClip.play();
       controller.setLanguague(2);
       controller.unloadScreen(Main.screen1ID);
       controller.loadScreen(Main.screen1ID, Main.screen1File);
       controller.setScreen(Main.screen1ID);
       
       
    }
    
    @FXML
    private void enterNL(){
       imgNL.setScaleX(1.1);
       imgNL.setScaleY(1.1);
       lblLang.setText("NEDERLANDS");
       
       hoverAudioClip.play();
    }
    
    @FXML
    private void exitNL(){
       imgNL.setScaleX(1);
       imgNL.setScaleY(1);
       lblLang.setText("");
       
    }
    
    
    //EN
    @FXML
    private void clickEN(){
       //hier handmatig laden ander start muziek al
       controller.playMusic();
       
       clickAudioClip.play();
        controller.setLanguague(1);
       controller.loadScreen(Main.screen1ID, Main.screen1File);
       controller.setScreen(Main.screen1ID);
      
       
    }
    
    @FXML
    private void enterEN(){
       imgEN.setScaleX(1.1);
       imgEN.setScaleY(1.1);
       lblLang.setText("ENGLISH");
       hoverAudioClip.play();
    }
    
    @FXML
    private void exitEN(){
       imgEN.setScaleX(1);
       imgEN.setScaleY(1);
       lblLang.setText("");
    }
    
    
    //FR
    @FXML
    private void clickFR(){
        
       //hier handmatig laden ander start muziek al
       
       controller.playMusic();
       
       clickAudioClip.play();
     controller.setLanguague(3);
       controller.loadScreen(Main.screen1ID, Main.screen1File);
       controller.setScreen(Main.screen1ID);
       
       
    }
    
    @FXML
    private void enterFR(){
       imgFR.setScaleX(1.1);
       imgFR.setScaleY(1.1);
       lblLang.setText("Français");
       hoverAudioClip.play();
    }
    
    @FXML
    private void exitFR(){
       imgFR.setScaleX(1);
       imgFR.setScaleY(1);
       lblLang.setText("");
    }
    
    
    
}
