

package gui.scenes.controllers;

import gui.SceneController;
import gui.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;





public class IntroController implements Initializable, _Scene {
    
    //scene controller
    SceneController controller;
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());

    
    @FXML private MediaView credits;
    //@FXML private Label lbl;
    
    final Media m = new Media(getClass().getResource("../../assets/sfx/media/intro.mp4").toExternalForm());
            final MediaPlayer mp = new MediaPlayer(m);
            
     
        
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        clickAudioClip.setVolume(0.5);
 
                    //video afspelen <----
                        
            
            credits.setMediaPlayer(mp);
            
            
            //   !!!uncommenten als placeholder weggaat!!!
            //final DoubleProperty width = credits.fitWidthProperty();
           // final DoubleProperty height = credits.fitHeightProperty();

           //width.bind(Bindings.selectDouble(credits.sceneProperty(), "width"));
              //height.bind(Bindings.selectDouble(credits.sceneProperty(), "height"));
//
       credits.setPreserveRatio(false);
 
//            mp.setVolume(0.85);
//            
//             
//            
            mp.play();
//            
            
            //----> video afspelen
    
            
            //stopt video als er op een toets wordt gedrukt
            
                       
            
        
            
                
               

            
            mp.setOnEndOfMedia(() -> {

                //terug naar menu
                
                controller.loadScreen(Main.screen2ID, Main.screen2File);
                controller.setScreen(Main.screen2ID);
                


            });

            mp.setOnStopped(() -> {

                controller.loadScreen(Main.screen2ID, Main.screen2File);
                controller.setScreen(Main.screen2ID);

            });
            

            credits.setOnMouseClicked(Event->{
            System.out.println("s");
            //clickAudioClip.play();
            mp.stop();
            });
            
            credits.setOnKeyPressed((event) -> {
                System.out.println("dqudjwqjqwfdhjqiodbhnpqiudhnquidpqwhu");
            });
            
    }
    
   @FXML private void keyPressed(){
       System.out.println("ssddss");
    }
    
    
    @Override
    public void setScreenParent(SceneController screenParent){
        controller = screenParent;
            controller.unloadScreen(Main.screen1ID);

        
    };

    

}
