

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.scene.text.Font;





public class SelectPlayerController implements Initializable, _Scene {
    
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
    
    
   
    
    
    @FXML private Button btnConfirm;

    @FXML private Button btnMain;
    @FXML private Label lblSettings;
    @FXML private Label lblError;
    @FXML private ListView list1;
    @FXML private ListView list2;
    @FXML private ImageView img;
    
    private Image image = new Image(getClass().getResourceAsStream("../../assets/img/menu/loading.gif"));
    
     ObservableList<String> playerList;
     ObservableList<String> playerList2;
     
     boolean list1Clicked = false;
     boolean list2Clicked = false;

    
    
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
        rs = rb;
        System.out.println(Font.loadFont(getClass().getResourceAsStream("../../assets/css/upheavtt.ttf"), 14).getName());
        
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);
        
        //assert btnConfirm != null;
        btnConfirm.setText(rs.getString("startGame")); 
        

        
        btnMain.setText(rs.getString("backToMain"));
        
        //assert btnMain != null;
        
        assert lblSettings != null;
        lblSettings.setText(rs.getString("select2Players"));
        
         
        

    }
    
    
    
    
    @Override
    public void setScreenParent(SceneController screenParent){
        
        controller = screenParent;
       
        controller.unloadScreen(Main.screen1ID);
        playerList = FXCollections.observableArrayList(controller.getDC().getPlayerNames());
        playerList2 = FXCollections.observableArrayList(controller.getDC().getPlayerNames());
        
        Collections.reverse(playerList2);
        
        
        list1.setItems(playerList);
          list2.setItems(playerList2);
          
          
    };


//btnConfirm
    
    @FXML
    public void btnConfirmClick(){
       
       
        
        
        
        
       clickAudioClip.play();
        
      String name1 =  list1.getSelectionModel().getSelectedItem().toString();
      String name2 = list2.getSelectionModel().getSelectedItem().toString();
        try
        {
           
            controller.getDC().makeMatch();
            controller.getDC().selectPlayer(name1);
            controller.getDC().selectPlayer(name2);
            
            controller.loadScreen(Main.screen13ID, Main.screen13File);
            controller.setScreen(Main.screen13ID);
      } catch (Exception e)
        {
            lblError.setText(e.getMessage());
        }
        
           
        
       
       
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
    
    
    @FXML
    public void list1Click(){
       list1Clicked = true;
      
        if (list1Clicked && list2Clicked) {
            btnConfirm.setDisable(false);
        }
       
        
    }
    
    @FXML
    public void list2Click(){
       list2Clicked = true;
      
       if (list1Clicked && list2Clicked) {
            btnConfirm.setDisable(false);
        }
        
    }
}
