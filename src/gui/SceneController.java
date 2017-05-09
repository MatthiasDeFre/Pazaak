

package gui;

import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import gui.scenes.controllers._Scene;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.StageStyle;
import domain.DomainController;
import java.util.Locale;
import java.util.ResourceBundle;

//scherm toevoegen --> 

public class SceneController  extends StackPane {
    
    //language
private ResourceBundle rs;
 Locale currentLocale = Locale.getDefault();


    private DomainController dc = new DomainController();
    private HashMap<String, Node> screens = new HashMap<>();
    
    
    //mainmuic voor methode
    URL mainMenuMusicURL = getClass().getResource("../../assets/sfx/music/Menu.mp3");
    //Media mainMenuMusicMedia = new Media(mainMenuMusicURL.toString());
    //MediaPlayer mainMenuMusic = new MediaPlayer(mainMenuMusicMedia);
    
    
    
    
    public SceneController() {
        super();//mario
        
    }

    //scherm toevoegrn aan lijst
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    //
    public Node getScreen(String name) {
        return screens.get(name);
    }

    //scherm laden in cache
    public boolean loadScreen(String name, String resource) {
        
        try {
            
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            myLoader.setResources(rs);
            Parent loadScreen = (Parent) myLoader.load();
            _Scene myScreenControler = ((_Scene) myLoader.getController());
            myScreenControler.setScreenParent(this);
            addScreen(name, loadScreen);
            
            return true;
            
        } catch (Exception e) {
            
            System.out.println("dfefe");
            System.out.println(e.getMessage());
            
            return false;
        }
    }

    //Methode om scene te veranderen (met fade)

    public boolean setScreen(final String name) {       
        
         
        
        if (screens.get(name) != null) {   //screen loaded
            
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {
                
                Timeline fade = new Timeline(
                        
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        
                        //fade bij klik
                        new KeyFrame(new Duration(500), new EventHandler<ActionEvent>() {   //500
                            
                    @Override
                    
                    public void handle(ActionEvent t) {
                        
                        getChildren().remove(0);                    
                        getChildren().add(0, screens.get(name));     //toevoeg
                        
                        Timeline fadeIn = new Timeline(
                                new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                
                                //fade na klik
                                new KeyFrame(new Duration(500), new KeyValue(opacity, 1.0)));   //500
                        fadeIn.play();
                    }
                }, new KeyValue(opacity, 0.0)));
                
                fade.play();

            } else {
                
                setOpacity(0.0);
                getChildren().add(screens.get(name));
                
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        
                        //bij start fade dur
                        new KeyFrame(new Duration(750), new KeyValue(opacity, 1.0))); //1000v
                
                fadeIn.play();
            }
            
            return true;
            
        } else {
            
            System.out.println("test");
            return false;
        }

    }

    //unload scherm/scéne
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            //System.out.println("oei");
            return false;
        } else {
            return true;
        }
        
    }
    
    public DomainController getDC(){
        return dc;
    }
    
    public ResourceBundle getRS() {
    rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
    return rs;
}
    
    public void setLanguague(int index){
        switch (index) {
                    case 1:
                        //ENGLISH
                        currentLocale = new Locale("en_US");
                        break;
                    case 2:
                        //NEDERLANDS
                        currentLocale = new Locale("nl_BE"); 
                        break;
                    case 3:
                        //FRENCH
                        currentLocale = new Locale("fr_FR");   
                        break;
                    default:
                        System.out.println("Wrong number");
                        break;
                }
        Locale.setDefault(currentLocale);
         rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
         System.out.println(rs.getString("welcome"));
    }
    
  /*  public void startMusic() {
    // Main Menu music
    //word voorlopig niet gebruikt
    
    mainMenuMusic.setCycleCount(mainMenuMusic.INDEFINITE);
        mainMenuMusic.setVolume(0.25);
        mainMenuMusic.play();
        
    }
    
    public void stopMusic() {
    
   
        mainMenuMusic.stop();
        
    }*/

    
}

