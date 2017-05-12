package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.scene.paint.Color;

import javafx.stage.Stage;



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
    public static String screen8ID = "Intro";
    public static String screen8File = "scenes/fxml/Intro.fxml";
    public static String screen9ID = "Shop";
    public static String screen9File = "scenes/fxml/Shop.fxml";
    public static String screen10ID = "Message";
    public static String screen10File = "scenes/fxml/Message.fxml";
    public static String screen11ID = "SelectPlayer";
    public static String screen11File = "scenes/fxml/SelectPlayer.fxml";
    
    @Override
    public void start(Stage primaryStage) {
        
        
        
        
        //uncomment loader om te testn
        
        SceneController mainContainer = new SceneController();
        
        
        mainContainer.setLanguague(2);
        
        //mainContainer.loadScreen(Main.screen1ID, Main.screen1File);
        mainContainer.loadScreen(Main.screen2ID, Main.screen2File);
        //mainContainer.loadScreen(Main.screen3ID, Main.screen3File);
        //mainContainer.loadScreen(Main.screen4ID, Main.screen4File);
        //mainContainer.loadScreen(Main.screen5ID, Main.screen5File);
        mainContainer.loadScreen(Main.screen8ID, Main.screen8File); //intro
        //mainContainer.loadScreen(Main.screen9ID, Main.screen9File);
        //mainContainer.loadScreen(Main.screen11ID, Main.screen11File);
        
        
        //beginscherm:
        mainContainer.setScreen(Main.screen8ID); //9
        
        

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        
        
        //fade kleur (was wit (met dash haha (ok genoeg haakjes (haakception (nog eentje omt af te leren))))
        scene.setFill(Color.web("#081F29"));
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //icon instellen
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("assets/img/menu/fav4.png")));
        primaryStage.setTitle("Pazaak  v1.253 Pre-release");
        
        //geen maximize/resize
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
       
    }


    public static void main(String[] args) {
        launch(args);
        
    }
}
