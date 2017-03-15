package gui;





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
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        
        
//scLanguage scene

       ImageView image = new ImageView(new Image(getClass().getResourceAsStream("logo2.png")));
       ImageView imageLogin = new ImageView(new Image(getClass().getResourceAsStream("login.png"), 100, 100, true, true));
       ImageView imageReg = new ImageView(new Image(getClass().getResourceAsStream("reg.png"), 100, 100, true, true));
       ImageView imageMsg = new ImageView(new Image(getClass().getResourceAsStream("error.png"), 60, 60, true, true));
       ImageView imageLoading = new ImageView(new Image(getClass().getResourceAsStream("laad.gif"), 270,270, true, true));
        
        Label lblLanguage = new Label("Please choose your language:");
        Button btnEN = new Button("English");
        Button btnNL = new Button("Nederlands");
        Button btnFR = new Button("Français");
        Button btnFullscreen = new Button("Fullscreen");
        
        btnEN.setMinWidth(120);
        btnNL.setMinWidth(120);
        btnFR.setMinWidth(120);
        btnFullscreen.setMinWidth(90);
      
        
        //taalkeuze
        
        btnEN.setOnAction(e -> {
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
        
        btnFullscreen.setOnAction(e -> {
            //maximaliseren
            //stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setFullScreenExitHint("lol");
            
stage.initStyle(StageStyle.UNDECORATED);
        
        });
        
        
        
        
        Locale.setDefault(currentLocale);
        
        ResourceBundle rs; 
       
        rs  = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
        
        
        
        VBox layout = new VBox(25);
        layout.setPadding(new Insets(40, 0,0, 0));
        HBox pane = new HBox(1000);
        pane.setPadding(new Insets(0,0,0,39));
        
        pane.getChildren().add(btnFullscreen);
        layout.getChildren().addAll(image,lblLanguage, btnEN, btnNL, btnFR, imageLoading, pane);
        
        scLanguage = new Scene(layout, 1100,700);
        
        
        
        scLanguage.getStylesheets().add("gui/style.css");
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
