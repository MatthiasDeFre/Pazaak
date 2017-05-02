package gui3.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class MessageBox {
    
   
    
    public static void display(String title, String message, ImageView img) {
        Stage stage = new Stage();
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(310);
        stage.setMinHeight(180);
        
        
        
        Label lblMessage = new Label(message);
        Button btnClose = new Button("Sluit");
        
        
        
        btnClose.setOnAction(e -> stage.close());
        
        VBox layout = new VBox(10);
        
        layout.getChildren().addAll(img, lblMessage,btnClose);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("guiBackup/style.css");
        layout.getStyleClass().add("back");
        stage.setScene(scene);
        stage.showAndWait();
        
    }
    
}
