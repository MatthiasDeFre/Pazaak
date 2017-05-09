package gui;

import java.net.URL;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class Main extends Application {
    
    
    //alle scenes
    public static String screen1ID = "MainMenu";
    public static String screen1File = "scenes/fxml/MainMenu.fxml";
    public static String screen2ID = "Language";
    public static String screen2File = "scenes/fxml/Language.fxml";
    public static String screen3ID = "Settings";
    public static String screen3File = "scenes/fxml/Settings.fxml";
    public static String screen4ID = "Register";
    public static String screen4File = "scenes/fxml/Register.fxml";
    public static String screen5ID = "Credits";
    public static String screen5File = "scenes/fxml/Credits.fxml";
    public static String screen6ID = "SideDeck";
    public static String screen6File = "scenes/fxml/SideDeck.fxml";
    public static String screen7ID = "Game";
    public static String screen7File = "scenes/fxml/Game.fxml";
    

    
    @Override
    public void start(Stage primaryStage) {
        
        
        
        
        //uncomment loader om te testn
        
        SceneController mainContainer = new SceneController();
        
        //testsxtsvtscs
        mainContainer.setLanguague(2);
        
        //mainContainer.loadScreen(Main.screen1ID, Main.screen1File);
        mainContainer.loadScreen(Main.screen2ID, Main.screen2File);
        //mainContainer.loadScreen(Main.screen3ID, Main.screen3File);
        //mainContainer.loadScreen(Main.screen4ID, Main.screen4File);
        //mainContainer.loadScreen(Main.screen5ID, Main.screen5File);
        
        //beginscherm:
        mainContainer.setScreen(Main.screen2ID);
        
        

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //icon instellen
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("assets/img/menu/fav4.png")));
        primaryStage.setTitle("Pazaak Indev v34.93939");
        
        //geen maximize/resize
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
       
    }


    public static void main(String[] args) {
        launch(args);
        
    }
}
