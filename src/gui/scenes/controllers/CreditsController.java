

package gui.scenes.controllers;

import gui.SceneController;
import gui.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javafx.scene.text.Font;




public class CreditsController implements Initializable, _Scene {
    
    //scene controller
    SceneController controller;
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());

    
    @FXML private MediaView credits;
    final Media m = new Media(getClass().getResource("../../assets/sfx/media/credits.mp4").toExternalForm());
            final MediaPlayer mp = new MediaPlayer(m);
            
     
        
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        clickAudioClip.setVolume(0.5);
 
                    //video afspelen <----
            
            
            
            credits.setMediaPlayer(mp);
            final DoubleProperty width = credits.fitWidthProperty();
            final DoubleProperty height = credits.fitHeightProperty();
//
           width.bind(Bindings.selectDouble(credits.sceneProperty(), "width"));
              height.bind(Bindings.selectDouble(credits.sceneProperty(), "height"));
//
       credits.setPreserveRatio(true);
 
//            mp.setVolume(0.85);
//            
//             
//            
            mp.play();
//            
            
            //----> video afspelen
    
            
            //stopt video als er op een toets wordt gedrukt
            

            
            mp.setOnEndOfMedia(() -> {
                controller.playMusic();
                //terug naar menu
                controller.loadScreen(Main.screen1ID, Main.screen1File);
                controller.setScreen(Main.screen1ID);


            });

            mp.setOnStopped(() -> {
                controller.playMusic();
                controller.loadScreen(Main.screen1ID, Main.screen1File);
                controller.setScreen(Main.screen1ID);

            });
            

            credits.setOnMouseClicked(Event->{
                controller.playMusic(); 
            System.out.println("s");
            clickAudioClip.play();
            mp.stop();
            });
            
            
            
    
    
    



    }
    
    
    
    
    @Override
    public void setScreenParent(SceneController screenParent){
        controller = screenParent;
            controller.unloadScreen(Main.screen1ID);

        
    };

    

}
