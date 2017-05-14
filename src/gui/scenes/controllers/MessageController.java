

package gui.scenes.controllers;

import gui.SceneController;
import gui.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.media.AudioClip;

import javafx.scene.text.Font;
import javafx.util.Duration;




public class MessageController implements Initializable, _Scene {
    //lagnuage
    private ResourceBundle rs;
    //scene controller
    SceneController controller;
    
    
    
    
   
    
    //Hover sound
    final AudioClip hoverAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Hover.mp3").toExternalForm());
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());
    
     ImageView imgMain = new ImageView(new Image(getClass().getResourceAsStream("../../assets/img/menu/main.png"), 41,41, true, true));
    
    
    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btnMain;
    @FXML private Label lblMessage;
    @FXML private ImageView img;
    
    private Image image = new Image(getClass().getResourceAsStream("../../assets/img/menu/hal.png"), 80,80, true,true);
    private Image loadsave = new Image(getClass().getResourceAsStream("../../assets/img/menu/loadsave.png"));
    private Image playerai = new Image(getClass().getResourceAsStream("../../assets/img/menu/playerai.png"));

        
    
    
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(Font.loadFont(getClass().getResourceAsStream("../../assets/css/upheavtt.ttf"), 14).getName());
        rs = rb;
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);
        
        btnMain.setGraphic(imgMain);
        
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
        
        if (controller.messageId == "6") { //exit
            final Glow glow = new Glow();
    glow.setLevel(0.0);
    
    btn1.setEffect(glow);
    final Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.setAutoReverse(true);
    final KeyValue kv = new KeyValue(glow.levelProperty(), 0.6);
    final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
    timeline.getKeyFrames().add(kf);
    timeline.play();  
            
            btn1.setStyle("-fx-background-color: green; -fx-text-fill: white;");
            btn2.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            img.setFitHeight(80);
            img.setImage(image);
        }
        else if (controller.messageId == "1") {
            img.setFitHeight(80);
            img.setImage(loadsave);
            
            final Glow glow = new Glow();
    
            glow.setLevel(0.0);
    
    img.setEffect(glow);
    final Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.setAutoReverse(true);
    final KeyValue kv = new KeyValue(glow.levelProperty(), 0.6);
    final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
    timeline.getKeyFrames().add(kf);
    timeline.play();  
    
        }
        else if (controller.messageId == "2") {
            img.setFitHeight(80);
            img.setImage(playerai);
            
            final Glow glow = new Glow();
    glow.setLevel(0.0);
    
    img.setEffect(glow);
    final Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.setAutoReverse(true);
    final KeyValue kv = new KeyValue(glow.levelProperty(), 0.6);
    final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
    timeline.getKeyFrames().add(kf);
    timeline.play();  
            
        }
        
        
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
       
       //New or load game
       if (controller.messageId == "1") {
            controller.message = "Play vs player or AI";//rs plz
       controller.button1 = "Quick Play";
       controller.button2 = "Play vs AI";
       
       controller.messageId = "2";
       controller.loadScreen(Main.screen10ID, Main.screen10File);
       controller.setScreen(Main.screen10ID);
       
        }
       else if (controller.messageId == "2") { //player vs player
            
       controller.loadScreen(Main.screen11ID, Main.screen11File);
       controller.setScreen(Main.screen11ID);
        }
       else if (controller.messageId == "6") { //exit
            
       controller.loadScreen(Main.screen1ID, Main.screen1File);
       controller.setScreen(Main.screen1ID);
        }
       
       
       
       
       
       
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
     
        
        //New or load game
       if (controller.messageId == "1") {
           clickAudioClip.play();
            System.out.println("Load game WIP");
        }
       else if (controller.messageId == "6") {
            System.out.println("Exiting");
            Platform.exit();
        }
       
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
