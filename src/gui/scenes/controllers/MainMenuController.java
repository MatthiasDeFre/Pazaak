

package gui.scenes.controllers;

import gui.SceneController;
import gui.Main;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;



public class MainMenuController implements Initializable, _Scene {
    
    //Languageshizzle
  
    
    
    //scene controller
    SceneController controller;
     private ResourceBundle rs;
    


    
   
    
    //Hover sound
    final AudioClip hoverAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Hover.mp3").toExternalForm());
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());
    
    //exit sound
    final AudioClip ExitAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Error.mp3").toExternalForm());

    
//    final AudioClip[] randomExit = {ExitAudioClip, Exit2AudioClip, Exit3AudioClip};
//    Random random1 = new Random();
//    int index1 = random1.nextInt(randomExit.length);
    
    ImageView imgExit = new ImageView(new Image(getClass().getResourceAsStream("../../assets/img/menu/exit.png"), 42,34, true, true));
    ImageView imgSettings = new ImageView(new Image(getClass().getResourceAsStream("../../assets/img/menu/settings.png"), 42,32, true, true));
    ImageView imgStartGame = new ImageView(new Image(getClass().getResourceAsStream("../../assets/img/menu/game.png"), 42,32, true, true));
    ImageView imgRegister = new ImageView(new Image(getClass().getResourceAsStream("../../assets/img/menu/register.png"), 42,34, true, true));
    ImageView imgCredits = new ImageView(new Image(getClass().getResourceAsStream("../../assets/img/menu/credits.png"), 42,34, true, true));
   
    //random zinnen hier:
    final String[] randomTop = {"Made in Düsseldorf", "Deze zin is bijzaak", "Dé polyvalente kaartsimulator", "Sponsored by Danio Danone"};
    Random random = new Random();
    int index = random.nextInt(randomTop.length-1);
    Glow selectGlow = new Glow(1.7f);
    
    @FXML private Button btnStartGame;
    @FXML private Button btnRegister;
    @FXML private Button btnSettings;
    @FXML private Button btnCredits;
    @FXML private Button btnExit;
    @FXML private Label lblTop;
@FXML private ImageView img;

   @FXML private MediaView media;
    final Media m = new Media(getClass().getResource("../../assets/sfx/media/loop.mp4").toExternalForm());
            final MediaPlayer mp = new MediaPlayer(m);
    
     final Timeline timelineRot = new Timeline();
        
    
    
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
        
        
        
        
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);
        
        media.setMediaPlayer(mp);
            final DoubleProperty width = media.fitWidthProperty();
            final DoubleProperty height = media.fitHeightProperty();
//
           width.bind(Bindings.selectDouble(media.sceneProperty(), "width"));
              height.bind(Bindings.selectDouble(media.sceneProperty(), "height"));
//
       media.setPreserveRatio(true);
       mp.setCycleCount(MediaPlayer.INDEFINITE);
 
//            mp.setVolume(0.85);
//            
//             
//            
            mp.play();
        
    
        
        
        
           final Glow glow = new Glow();
    glow.setLevel(0.0);
    img.setEffect(glow);

    
    
    final Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.setAutoReverse(true);
    final KeyValue kv = new KeyValue(glow.levelProperty(), 1); //0.3
    final KeyFrame kf = new KeyFrame(Duration.millis(1500), kv); //700
    timeline.getKeyFrames().add(kf);
    timeline.play();        
       
        
      
    }
    
    
    
    
    @Override
    public void setScreenParent(SceneController screenParent){
        controller = screenParent;
        
        
    };

    
    
    
//btnStartGame
    
    @FXML
    public void btnStartGameClick(){
       
       clickAudioClip.play();
       
       controller.message = rs.getString("newOrLoad");//rs plz
       controller.button1 = rs.getString("newGame");
       controller.button2 = rs.getString("loadGame");
       controller.messageId = "1";
       
       controller.loadScreen(Main.screen10ID, Main.screen10File);
       controller.setScreen(Main.screen10ID);
       
       
       
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
     
        
        controller.stopMusic();
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
        //mainMenuMusic.stop();
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
        
//       // lblTop.setText("Closing");
//        //clickAudioClip.play();
//        
//        
//        //mainMenuMusic.stop();
//        
//        randomExit[index1].play();
//        
//        switch(index1) {
//            
//            //win xp
//            case 0: Thread.sleep(2000);
//                    Platform.exit();
//                    break;
//            
//            //win vista
//            case 1: Thread.sleep(2200);
//                    Platform.exit();
//                    break;
//               
//            //win 7
//            case 2: Thread.sleep(4200);
//                    Platform.exit();
//                    break;
//            
//            
        ExitAudioClip.play();
        controller.message = "I'm sorry Dave, I'm afraid I can't do that";//rs plz
       controller.button1 = rs.getString("keepPlaying");
       controller.button2 = rs.getString("exit");
       controller.messageId = "6";  //init6 lel
       
       controller.loadScreen(Main.screen10ID, Main.screen10File);
       controller.setScreen(Main.screen10ID);

        
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
