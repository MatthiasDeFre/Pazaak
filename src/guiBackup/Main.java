package guiBackup;





import javafx.scene.image.ImageView;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.net.URL;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;

public class Main extends Application {
    
    Stage stage;
    Scene scLanguage, scLogin, scRegister, scGame;
    
    Locale currentLocale;
    
    
    public static void main(String[] args) {
        launch(args);
        
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        stage = primaryStage;
        
        currentLocale = Locale.getDefault();
        Font.loadFont(Main.class.getResource("dwarffat.otf").toExternalForm(), 10);
        
//scLanguage scene

        final URL resource = getClass().getResource("Menu.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.25);
        mediaPlayer.play();
          
       final URL resourceq = getClass().getResource("Click.mp3");
        final Media mediaq = new Media(resourceq.toString());
        final MediaPlayer mediaPlayerq = new MediaPlayer(mediaq);

        
        
        final AudioClip mediaPlayerh = new AudioClip(getClass().getResource("Hover.mp3").toExternalForm());
       


       ImageView image = new ImageView(new Image(getClass().getResourceAsStream("logo2.png")));
       ImageView imageLogin = new ImageView(new Image(getClass().getResourceAsStream("login.png"), 100, 100, true, true));
       ImageView imageReg = new ImageView(new Image(getClass().getResourceAsStream("reg.png"), 100, 100, true, true));
       ImageView imageMsg = new ImageView(new Image(getClass().getResourceAsStream("error.png"), 60, 60, true, true));
       ImageView imageLoading = new ImageView(new Image(getClass().getResourceAsStream("laad.gif"), 270,270, true, true));
       ImageView imageEN = new ImageView(new Image(getClass().getResourceAsStream("img/EN.png"), 300,300, true, true));
       ImageView imageNL = new ImageView(new Image(getClass().getResourceAsStream("img/NL.png"), 300,300, true, true));
       ImageView imageFR = new ImageView(new Image(getClass().getResourceAsStream("img/FR.png"), 300,300, true, true));
        
        Label lblLanguage = new Label("Please choose your language:");
        lblLanguage.setStyle("-fx-font-family: dwarffat; -fx-font-size: 23;");
        Button btnEN = new Button("", imageEN);
        btnEN.setStyle("-fx-font-family: dwarffat; -fx-font-size: 23;");
        Button btnNL = new Button("", imageNL);
        Button btnFR = new Button("", imageFR);
        Button btnFullscreen = new Button("Fullscreen");
        
        btnEN.setMinWidth(120);
        btnNL.setMinWidth(120);
        btnFR.setMinWidth(120);
        btnFullscreen.setMinWidth(90);
        stage.setMaximized(true);
      
        
        //taalkeuze
        
        btnEN.setOnAction(e -> {
            mediaPlayerq.play();
            
            
            mediaPlayer.stop();
            currentLocale = new Locale("en_US");
            stage.setScene(scLogin);
                });
        
        btnNL.setOnAction(e -> {
             currentLocale = new Locale("nl_BE");
             stage.setScene(scLogin);
                });

        
        btnFR.setOnAction(e -> {
             currentLocale = new Locale("fr_FR");
             stage.setScene(scLogin);
             
                });
        
        
        
        
        
        btnEN.setOnMouseEntered((MouseEvent t) ->
        {
            mediaPlayerh.play();
            
            btnEN.setScaleX(1.1);
            btnEN.setScaleY(1.1);
        });
        
        btnEN.setOnMouseExited((MouseEvent t) ->
        {
            mediaPlayerh.play();
            btnEN.setScaleX(1);
            btnEN.setScaleY(1);
        });
        
        
        
        
        
        
        btnNL.setOnMouseEntered((MouseEvent t) ->
        {
            mediaPlayerh.play();
            
            btnNL.setScaleX(1.1);
            btnNL.setScaleY(1.1);
            
        });
        
        btnNL.setOnMouseExited((MouseEvent t) ->
        {
            
            
            btnNL.setScaleX(1);
            btnNL.setScaleY(1);
            
        });
        
        
        
        
          btnFR.setOnMouseEntered((MouseEvent t) ->
        {
            
            mediaPlayerh.play();
            
            btnFR.setScaleX(1.1);
            btnFR.setScaleY(1.1);
        });
          
          btnFR.setOnMouseExited((MouseEvent t) ->
        {
            
           
            
            btnFR.setScaleX(1);
            btnFR.setScaleY(1);
        });
          
        
        btnFullscreen.setOnAction(e -> {
            //maximaliseren
            
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setFullScreenExitHint("lol");
        stage.setFullScreen(true);
       
            
stage.initStyle(StageStyle.UNDECORATED);
        
        });
        
        
        
        
        Locale.setDefault(currentLocale);
        
        ResourceBundle rs; 
       
        rs  = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
        
        
        
        VBox layout = new VBox(25);
        layout.setPadding(new Insets(40, 0,0, 0));
        HBox pane = new HBox(1000);
        pane.setPadding(new Insets(0,0,20,15));
        
        pane.getChildren().add(btnFullscreen);
        layout.getChildren().addAll(image,lblLanguage, btnEN, btnNL, btnFR, imageLoading, pane);
        
        scLanguage = new Scene(layout, 1100,700);
        
        
        
        scLanguage.getStylesheets().add("guiBackup/style.css");
        layout.getStyleClass().add("back"); 
        layout.setAlignment(Pos.CENTER);
        
        stage.setScene(scLanguage);
        stage.setTitle("Pazaak");
        stage.show();
        
//scLogin scene
        
        
        Label lblLogin = new Label(rs.getString("loginOrRegister"));
        Button btnLogin = new Button(rs.getString("login"));
        Button btnRegister = new Button(rs.getString("register"));
        Button btnBack = new Button("Go back");
        
        
        btnLogin.setOnAction(e -> MessageBox.display("Bazaar", "Unknown error", imageMsg));
        
        btnLogin.setMinWidth(85);
        btnRegister.setMinWidth(85);
        btnBack.setMinWidth(85);

        
        
        btnBack.setOnAction(e -> stage.setScene(scLanguage));
        
        TextField txtUsername = new TextField();
        TextField txtPassword = new TextField();
        txtUsername.setPromptText("username");
        txtPassword.setPromptText("password");
        txtUsername.setMaxWidth(180);
        txtPassword.setMaxWidth(180);
        
        VBox loginLayout = new VBox(15);
        loginLayout.setCursor(Cursor.WAIT);
        HBox buttons = new HBox(10);
        
        
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(btnLogin, btnRegister);
        loginLayout.getChildren().addAll(imageLogin, lblLogin, txtUsername, txtPassword, buttons, btnBack);
        
        scLogin = new Scene(loginLayout, 600,350);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.requestFocus();
        
        btnRegister.setOnAction(e -> stage.setScene(scRegister));
        
//scRegister scene
        
        Label lblRegister = new Label(rs.getString("inputRegister"));
        Button btnRegister2 = new Button(rs.getString("register"));
        Button btnBack2 = new Button("Go back");
        
        btnBack2.setOnAction(e -> stage.setScene(scLogin));
        
        TextField txtName = new TextField();
        TextField txtDate = new TextField();
        txtName.setPromptText(rs.getString("name"));
        txtDate.setPromptText(rs.getString("date"));
        txtName.setMaxWidth(180);
        txtDate.setMaxWidth(180);
        btnRegister2.setMinWidth(85);
        
        VBox registerLayout = new VBox(15);
        registerLayout.getChildren().addAll(imageReg, lblRegister, txtName, txtDate, btnRegister2, btnBack2);
        
        scRegister = new Scene(registerLayout, 600,350);
        registerLayout.setAlignment(Pos.CENTER);
        registerLayout.requestFocus();
        
        btnRegister2.setOnAction(e -> MessageBox.display("Bezwaar", "Unknown error", imageMsg));
                
        
        
    }

    
    
}
