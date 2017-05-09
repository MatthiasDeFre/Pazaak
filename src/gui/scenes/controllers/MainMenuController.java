

package gui.scenes.controllers;

import gui.SceneController;
import gui.Main;
import java.net.URL;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import sun.security.rsa.RSACore;



public class MainMenuController implements Initializable, _Scene {
    
    //Languageshizzle
  
    
    
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
    
    //exit sound
    final AudioClip ExitAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Exit.mp3").toExternalForm());
    
    ImageView imgExit = new ImageView(new Image(getClass().getResourceAsStream("../../assets/img/menu/exit.png"), 42,34, true, true));
    ImageView imgSettings = new ImageView(new Image(getClass().getResourceAsStream("../../assets/img/menu/settings.png"), 42,32, true, true));
    ImageView imgStartGame = new ImageView(new Image(getClass().getResourceAsStream("../../assets/img/menu/game.png"), 42,32, true, true));
    ImageView imgRegister = new ImageView(new Image(getClass().getResourceAsStream("../../assets/img/menu/register.png"), 42,34, true, true));
    ImageView imgCredits = new ImageView(new Image(getClass().getResourceAsStream("../../assets/img/menu/credits.png"), 42,34, true, true));
    
    //random zinnen hier:
    final String[] randomTop = {"Made in Düsseldorf", "Deze zin is bijzaak", "Dé polyvalente kaartsimulator", "Sponsored by Danio Danone"};
    Random random = new Random();
    int index = random.nextInt(randomTop.length);

    
    @FXML private Button btnStartGame;
    @FXML private Button btnRegister;
    @FXML private Button btnSettings;
    @FXML private Button btnCredits;
    @FXML private Button btnExit;
    @FXML private Label lblTop;

   
    
     
        
    
    
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
        rs = rb;
        
        //System.out.println(Font.loadFont(getClass().getResourceAsStream("../../assets/css/upheavtt.ttf"), 14).getName());
        
        lblTop.setText(randomTop[index]);
        
        //assert btnStartGame != null;
        btnStartGame.setText(rs.getString("startGame")); 
        
        btnStartGame.setGraphic(imgStartGame);
        
        //assert btnRegister != null;
        btnRegister.setText(rs.getString("register"));
        btnRegister.setGraphic(imgRegister);
        
        btnCredits.setText(rs.getString("credits"));
        btnCredits.setGraphic(imgCredits);
        
        //assert btnSettings != null;
        btnSettings.setText(rs.getString("settings"));
        btnSettings.setGraphic(imgSettings);
        
        btnExit.setText(rs.getString("exit"));
        btnExit.setGraphic(imgExit);
        
        
       
        
        //main menu muzioek
        mainMenuMusic.setCycleCount(mainMenuMusic.INDEFINITE);
        mainMenuMusic.setVolume(0.25);
        mainMenuMusic.play();
        
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);
        
        
    }
    
    
    
    
    @Override
    public void setScreenParent(SceneController screenParent){
        controller = screenParent;
        
        
    };

    
    
    
//btnStartGame
    
    @FXML
    public void btnStartGameClick(){
       
       clickAudioClip.play();
       controller.setScreen(Main.screen2ID);
       
    }
    
    @FXML
    public void btnStartGameEnter(){
        
       hoverAudioClip.play();
       btnStartGame.setScaleX(1.1);
       btnStartGame.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnStartGameExit(){
       
       btnStartGame.setScaleX(1);
       btnStartGame.setScaleY(1);
   
    }

//btnRegister
    
    @FXML
    public void btnRegisterClick(){
     
        clickAudioClip.play();
        controller.loadScreen(Main.screen4ID, Main.screen4File);
        controller.setScreen(Main.screen4ID);
       
    }
    
    @FXML
    public void btnRegisterEnter(){
        
       hoverAudioClip.play();
       btnRegister.setScaleX(1.1);
       btnRegister.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnRegisterExit(){
       
       btnRegister.setScaleX(1);
       btnRegister.setScaleY(1);
       
        
    }
    
    
    //btnCredits
    
    @FXML
    public void btnCreditsClick(){
     
        clickAudioClip.play();
        mainMenuMusic.stop();
        controller.loadScreen(Main.screen5ID, Main.screen5File);
        controller.setScreen(Main.screen5ID);
        
       
    }
    
    @FXML
    public void btnCreditsEnter(){
        
       hoverAudioClip.play();
       btnCredits.setScaleX(1.1);
       btnCredits.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnCreditsExit(){
       
       btnCredits.setScaleX(1);
       btnCredits.setScaleY(1);
       
        
    }
    
    //btnSettings
    
    @FXML
    public void btnSettingsClick(){
     
        clickAudioClip.play();
        mainMenuMusic.stop();
        controller.loadScreen(Main.screen3ID, Main.screen3File);
        controller.setScreen(Main.screen3ID);
       
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
    
    //btnExit
    
    @FXML
    public void btnExitClick() throws InterruptedException{
     
        //clickAudioClip.play();
        mainMenuMusic.stop();
        ExitAudioClip.play();
        Thread.sleep(2000);
        Platform.exit();
       
    }
    
    @FXML
    public void btnExitEnter(){
        
       hoverAudioClip.play();
       btnExit.setScaleX(1.1);
       btnExit.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnExitExit(){
       
       btnExit.setScaleX(1);
       btnExit.setScaleY(1);
       
       
    }
}